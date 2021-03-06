package cgl.iotcloud.samples.arducopter.client;

import javax.swing.*;

public class CopterUI extends javax.swing.JPanel {
    private CopterUIDataModel dataModel;

    private KeyControlListener keyControlListener;

    /**
     * Creates new form CopterUI
     */
    public CopterUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        dataModel = new CopterUIDataModel();

        jTable1.setModel(dataModel);
        jScrollPane1.setViewportView(jTable1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(277, Short.MAX_VALUE))
        );

        jScrollPane1.addKeyListener(new KeyControlListener());
        jTable1.addKeyListener(new KeyControlListener());
        keyControlListener = new KeyControlListener();
        addKeyListener(keyControlListener);
    }// </editor-fold>
    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration


    public CopterUIDataModel getDataModel() {
        return dataModel;
    }

    public KeyControlListener getKeyControlListener() {
        return keyControlListener;
    }
}

