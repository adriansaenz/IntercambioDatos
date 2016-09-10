/*
Esta clase actua como servidor para recibir y guardar los archivos recibidos. 
El servidor espera hasta recibir los archivos, los recibe y los guarda en el escritorio.
----------------------------------------------------------------------------------------
This class acts as server to receive and store the files received.
The server waits until receive the files, receive and store in the desktop.
*/
package intercambio;

import java.io.*;
import java.net.*;

public class Servidor extends Thread{
    Intercambio main;
    String ruta = "C:\\Users\\Adrian\\Desktop\\ArchivosRecibidos\\";
    public Servidor(Intercambio main) {
        this.main = main;

        this.start();
    }

    private void comprobarCarpeta(){
        File f = new File(ruta);
        if(!f.exists()) f.mkdir();
    }
    
    @Override
    public void run() {
        ServerSocket servidor;
        Socket conexion;
        DataOutputStream salida = null;
        DataInputStream entrada;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;
        String file;

        try {
            //Socket
            servidor = new ServerSocket(5005);
            main.cambiaIP("La IP del dispositivo es: " + InetAddress.getLocalHost().getHostAddress());
            while (true) {
                //Abrir conexión
                conexion = servidor.accept();
                receivedData = new byte[1024];
                bis = new BufferedInputStream(conexion.getInputStream());
                //E/S
                entrada = new DataInputStream(conexion.getInputStream());
                salida = new DataOutputStream(conexion.getOutputStream());

                //Mensaje de entrada
                file = entrada.readUTF();
                comprobarCarpeta();
                bos = new BufferedOutputStream(new FileOutputStream(ruta+file));
                main.cambiarTexto("Recibiendo archivos...");
                while ((in = bis.read(receivedData)) != -1) bos.write(receivedData,0,in);
                main.cambiarTexto("Archivos recibidos.");
                salida.writeUTF("Correcto");
                main.abrirExplorador(ruta);
                bos.close();
                entrada.close();
                salida.close();
                conexion.close();
            }
        } catch (IOException e) {        }
    }
}
