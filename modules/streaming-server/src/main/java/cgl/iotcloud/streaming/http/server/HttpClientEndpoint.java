package cgl.iotcloud.streaming.http.server;

import io.netty.bootstrap.ClientBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;

public class HttpClientEndpoint extends HttpEndpoint {
    private static Logger log = LoggerFactory.getLogger(HttpClientEndpoint.class);
    private String host;
    
    private ServerConfiguration configuration;

    public HttpClientEndpoint(ServerConfiguration configuration, 
                              String path, String host, int port) {
        this(configuration, path, host, port, false);
    }

    public HttpClientEndpoint(ServerConfiguration configuration, String path, 
                              String host, int port, boolean SSL) {
        super(path, port, SSL);
        this.configuration = configuration;
        this.host = host;
    }

    public void connect(final MessageContext context) {
        ClientBootstrap cb = configuration.getClientBootStrap();

        ChannelFuture f = cb.connect(new InetSocketAddress(host, port));

        final Channel outboundChannel = f.getChannel();
        context.setOutChannel(outboundChannel);        
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    // Connection attempt succeeded:
                    // Begin to accept incoming traffic.
                    context.getInChannel().setReadable(true);

                    ChannelFuture writeFuture = context.getOutChannel().write(context.getRequest());
                    if (!context.getRequest().isChunked()) {
                        writeFuture.addListener(new ResponseWriter(context.getInChannel(), !isKeepAlive(context.getRequest()),
                                context.getRequest().isChunked(), outboundChannel));
                    }
                    // If outboundChannel is saturated, do not read until notified in
                    // OutboundHandler.channelInterestChanged().
                    if (!context.getOutChannel().isWritable()) {
                        // log.info("inbound readable false");
                        context.getInChannel().setReadable(false);
                    }
                    outboundChannel.setAttachment(context);
                } else {
                    // Close the connection if the connection attempt has failed.
                    context.getInChannel().close();
                }
            }
        });        
    }    
}
