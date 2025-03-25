package CHAT;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.nio.file.Files;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CHAT_CLIENT extends javax.swing.JFrame {

    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;

    public CHAT_CLIENT() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        main1 = new javax.swing.JTextArea();
        tb1 = new javax.swing.JTextField();
        bt1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jFile = new javax.swing.JButton();
        janh = new javax.swing.JButton();
        jvoice = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        main1.setColumns(20);
        main1.setRows(5);
        jScrollPane1.setViewportView(main1);

        bt1.setText("Gửi");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Cliend");

        jFile.setText("📂");
        jFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileActionPerformed(evt);
            }
        });

        janh.setText("🖼️");
        janh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                janhActionPerformed(evt);
            }
        });

        jvoice.setText("🎤");
        jvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tb1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFile, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(janh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bt1)))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFile)
                    .addComponent(janh)
                    .addComponent(jvoice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(bt1)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        try {
            String msg = "";
            msg = tb1.getText();
            dout.writeUTF(msg);
            tb1.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void jFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileActionPerformed
        // TODO add your handling code here:
        // code gửi file cho client
        try {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();

            dout.writeUTF("FILE");
            dout.writeUTF(file.getName());
            dout.writeInt(buffer.length);
            dout.write(buffer);
            dout.flush();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jFileActionPerformed

    private void janhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_janhActionPerformed
        // TODO add your handling code here:
        // code gửi ảnh cho client
        try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif"));
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();

            dout.writeUTF("IMAGE");
            dout.writeUTF(file.getName());
            dout.writeInt(buffer.length);
            dout.write(buffer);
            dout.flush();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_janhActionPerformed

    private void jvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvoiceActionPerformed
        // TODO add your handling code here:
        // code gửi voice cho client
         try {
        // Ghi âm giọng nói trong 5 giây
        File recordedFile = new File("recorded_audio.wav");
        recordAudio(recordedFile, 5000); 

        // Đọc dữ liệu file
        byte[] audioData = Files.readAllBytes(recordedFile.toPath());

        // Gửi tín hiệu "VOICE"
        dout.writeUTF("VOICE");
        dout.flush();

        // Gửi kích thước file
        dout.writeInt(audioData.length);
        dout.flush();

        // Gửi dữ liệu file
        dout.write(audioData);
        dout.flush();
        
        System.out.println("Gửi voice thành công!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Hàm ghi âm giọng nói
public static void recordAudio(File file, int duration) {
    try {
        AudioFormat format = new AudioFormat(16000, 16, 2, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
        
        microphone.open(format);
        microphone.start();

        AudioInputStream audioStream = new AudioInputStream(microphone);
        System.out.println("Đang ghi âm...");
        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, file);
        Thread.sleep(duration);

        microphone.stop();
        microphone.close();
        System.out.println("Ghi âm xong!");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jvoiceActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CHAT_CLIENT().setVisible(true);
            }
        });
        try {
            String msgin = "";
            s = new Socket("127.0.0.1", 1201);
            dis = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msgin.equals("exit")) {
                msgin = dis.readUTF();
                main1.setText(main1.getText() + "\n SEVER :" + msgin);
            }
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton jFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton janh;
    private javax.swing.JButton jvoice;
    private static javax.swing.JTextArea main1;
    private javax.swing.JTextField tb1;
    // End of variables declaration//GEN-END:variables
}
