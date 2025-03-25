/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CHAT;

/**
 *
 * @author HUBT
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
public class CHAT_SEVER extends javax.swing.JFrame {
static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;
    public CHAT_SEVER() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFile = new javax.swing.JButton();
        jvoice = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        main1 = new javax.swing.JTextArea();
        bt1 = new javax.swing.JButton();
        tb1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFile1 = new javax.swing.JButton();
        jvoice1 = new javax.swing.JButton();
        janh = new javax.swing.JButton();

        jFile.setText("📂");

        jvoice.setText("🎤");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main1.setColumns(20);
        main1.setRows(5);
        jScrollPane1.setViewportView(main1);

        bt1.setText("Gửi");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Sever");

        jFile1.setText("📂");
        jFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFile1ActionPerformed(evt);
            }
        });

        jvoice1.setText("🎤");
        jvoice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvoice1ActionPerformed(evt);
            }
        });

        janh.setText("🖼️");
        janh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                janhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tb1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(janh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jvoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFile1)
                    .addComponent(jvoice1)
                    .addComponent(janh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt1)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
      
        try{
        String msg="";
       msg=tb1.getText();
       dout.writeUTF(msg);
       tb1.setText("");
        }catch(Exception e)
        {
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void jFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFile1ActionPerformed
        // TODO add your handling code here:
        // Code cho mục gửi file ở đây
         new Thread(() -> {
        try {
            // Nhận file từ client
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            // Lưu file vào thư mục server
            File file = new File("received_" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while (fileSize > 0 && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
                fos.write(buffer, 0, bytesRead);
                fileSize -= bytesRead;
            }

            fos.close();
            main1.append("\n📂 Đã nhận file: " + fileName);
            JOptionPane.showMessageDialog(null, "Đã nhận file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }).start();
    }//GEN-LAST:event_jFile1ActionPerformed

    private void janhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_janhActionPerformed
        // TODO add your handling code here:
        // code gửi ảnh
        new Thread(() -> {
        try {
            // Nhận ảnh từ client
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String fileName = dis.readUTF(); // Nhận tên file ảnh
            long fileSize = dis.readLong();  // Nhận kích thước file

            // Lưu file ảnh vào thư mục server
            File file = new File("received_" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while (fileSize > 0 && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
                fos.write(buffer, 0, bytesRead);
                fileSize -= bytesRead;
            }

            fos.close();
            main1.append("\n🖼️ Đã nhận ảnh: " + fileName);
            JOptionPane.showMessageDialog(null, "Ảnh đã nhận thành công: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }).start();
    }//GEN-LAST:event_janhActionPerformed

    private void jvoice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvoice1ActionPerformed
        // TODO add your handling code here:
        // code voice
         try {
        // Nhận kích thước file
        int length = dis.readInt();
        byte[] audioData = new byte[length];
        dis.readFully(audioData, 0, length);

        // Lưu file voice vào máy
        File file = new File("received_audio.wav");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(audioData);
        fos.close();

        System.out.println("Nhận voice thành công! Đã lưu vào: " + file.getAbsolutePath());

        // Phát âm thanh ngay lập tức
        playAudio(file.getAbsolutePath());

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Hàm phát file âm thanh
public static void playAudio(String filePath) {
    try {
        File file = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jvoice1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CHAT_SEVER().setVisible(true);
            }
        });
         try {
            String msgin = "";
            ss = new ServerSocket(1201);
            s = ss.accept();
            dis = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while (!msgin.equals("exit")) {
                msgin = dis.readUTF();
                main1.setText(main1.getText() + "\n CLIENT :" + msgin);
            }
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton jFile;
    private javax.swing.JButton jFile1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton janh;
    private javax.swing.JButton jvoice;
    private javax.swing.JButton jvoice1;
    private static javax.swing.JTextArea main1;
    private javax.swing.JTextField tb1;
    // End of variables declaration//GEN-END:variables
}
