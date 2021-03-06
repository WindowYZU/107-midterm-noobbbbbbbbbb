/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.midterm_wp;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.AccessibleAttribute;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author lendle
 */
public class NewJFrame extends javax.swing.JFrame {

    static ProgressDialog progress = null;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "https://mbtskoudsalg.com/images/png-image-7.png", "http://pngimg.com/uploads/eagle/eagle_PNG1227.png", "https://upload.wikimedia.org/wikipedia/commons/4/4a/Photographer_Barnstar.png", "http://pluspng.com/img-png/bulb-hd-png-light-bulb-png-transparent-image-2048.png", " " }));

        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jLabel2);

        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 653, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            jButton1.setEnabled(false);
            //從 combobox 抓出被選到的項目，存到變數裡
             JFrame frame = new JFrame();
             frame.setSize(300, 300);
             frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             frame.setLayout(new FlowLayout());
            
           
            String selectedItem="";
            /////////////////////////////////////
            URL url = new URL(selectedItem);
            String fileName = url.getFile();
            final File tempFile = File.createTempFile("tmp", fileName.substring(fileName.lastIndexOf(".")));
            progress = new ProgressDialog(this);
            progress.setTitle("Downloading......");
            progress.setMessage(url.toString());
            FileDownloader.downloadFile(url,
                    tempFile, new FileDownloaderCallback() {
                @Override
                public void totalBytesDownloaded(long bytes, boolean finished, boolean failed) {
                    //implement this
                    if (finished) {
                        progress.setVisible(false);
                        jButton1.setEnabled(true);
                        //將下載好的項目加入到 jList 裡面
                        DefaultListModel model=(DefaultListModel) jList1.getModel();
                        model.addElement(jComboBox1.getSelectedItem());
                        jList1.updateUI();
                        ////////////////////////////
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    URL fileURL=tempFile.toURI().toURL();
                                    //利用 fileURL 將 image icon 加到 jLabel2
                                    JTextField textField = new JTextField(20);
                                    ImageIcon icon=new ImageIcon(
                                     new URL(""));
                                    Image img=icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                                    Icon icon2=new ImageIcon(img);
                                    jLabel2.setIcon(icon2);
            
                                    ////////////////////////////////////////
                                    frame.add(jLabel2);
                                    frame.add(textField);
                                    frame.setSize(800, 500);
                                    frame.setVisible(true);
                                  
                                 
                                    jList1.updateUI();
                                } catch (Exception ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        });
                    } else if (failed) {
                        progress.setVisible(false);
                        jButton1.setEnabled(true);
                    }
                    ////////////////
                }
            });
            progress.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
