/*
Esta clase actua como cliente. Permite escoger el archivo y mandarlo a la direccion IP escogida.
-------------------------------------------------------------------------------------------------
This class acts as client. It allows you choose a fole and send it to the IP addres chosen.
*/
package intercambio;

import java.awt.FileDialog;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;


public class Enviar extends javax.swing.JFrame {

    private InetAddress ip;
    private File elemento;
    
    public Enviar() {
        initComponents();
    }
    
    private String sendFile(){
        String response = "";
        if(elemento != null){
            DataInputStream input;
            BufferedInputStream bis;
            BufferedOutputStream bos;
            int in;
            byte[] byteArray;
            
            try{
                Socket cliente = new Socket(ip,5005);
                bis = new BufferedInputStream(new FileInputStream(elemento));
                bos = new BufferedOutputStream(cliente.getOutputStream());
                input = new DataInputStream(cliente.getInputStream());
                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                dos.writeUTF(elemento.getName());
                byteArray = new byte[8192];
                while ((in = bis.read(byteArray)) != -1) bos.write(byteArray,0,in);
                bis.close();
                bos.close();
                dos.close();
                response = input.readUTF();  
                cliente.close();
            }catch(Exception ex){}
        }
        else JOptionPane.showMessageDialog(null, "Seleccione primero el archivo que desea enviar", "Error de archivo", JOptionPane.ERROR_MESSAGE);
        return response;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeleccionBoton = new javax.swing.JButton();
        EnviaBoton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextoIP = new javax.swing.JTextField();
        Texto2 = new javax.swing.JLabel();
        Texto = new javax.swing.JLabel();
        Texto1 = new javax.swing.JLabel();
        TextoArchivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SeleccionBoton.setText("SELECCIONAR");
        SeleccionBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionBotonActionPerformed(evt);
            }
        });

        EnviaBoton.setText("ENVIAR");
        EnviaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviaBotonActionPerformed(evt);
            }
        });

        jLabel1.setText("Direccion IP:");

        Texto2.setText("Estado:");

        Texto.setText("Esperando respuesta...");

        Texto1.setText("Archivo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SeleccionBoton)
                        .addGap(81, 81, 81)
                        .addComponent(EnviaBoton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(Texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextoIP, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(Texto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextoArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextoIP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Texto2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(Texto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Texto1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(TextoArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SeleccionBoton)
                    .addComponent(EnviaBoton))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SeleccionBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionBotonActionPerformed
        FileDialog explorador = new FileDialog(this,"", FileDialog.LOAD);
        explorador.setDirectory("C:\\Users\\Adrian\\Desktop");
        explorador.setVisible(true);
        String f = explorador.getDirectory() + explorador.getFile();
        elemento = new File(f);
        TextoArchivo.setText(elemento.getName());
    }//GEN-LAST:event_SeleccionBotonActionPerformed

    private void EnviaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviaBotonActionPerformed
        String ipa = TextoIP.getText();
        ipa = ipa.replace(".", "-");
        String[] ips = ipa.split("-");
        try {
                byte[] bIp = new byte[4];

                bIp[0] = Byte.parseByte(ips[0]);
                bIp[1] = Byte.parseByte(ips[1]);
                bIp[2] = Byte.parseByte(ips[2]);
                bIp[3] = Byte.parseByte(ips[3]);

                if (bIp[0] == 0 && bIp[1] == 0 && bIp[2] == 0 && bIp[3] == 0) {
                    ip = InetAddress.getLocalHost();
                } else {
                    ip = InetAddress.getByAddress(bIp);
                }
                
                String response = sendFile();
                Texto.setText(response);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El formato de la dirección IP no es correcta.", "Error de IP", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_EnviaBotonActionPerformed

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
            java.util.logging.Logger.getLogger(Enviar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enviar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enviar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enviar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Enviar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnviaBoton;
    private javax.swing.JButton SeleccionBoton;
    private javax.swing.JLabel Texto;
    private javax.swing.JLabel Texto1;
    private javax.swing.JLabel Texto2;
    private javax.swing.JLabel TextoArchivo;
    private javax.swing.JTextField TextoIP;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
