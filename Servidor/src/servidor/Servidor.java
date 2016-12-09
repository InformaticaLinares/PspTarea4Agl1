/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Diru
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        ServerSocket conexion = null; //Socket para aceptar conexiones
        Socket canal = null;   // Socket para establecer el canal
        PrintWriter salidaStream;
        int contador = 1;
        while (contador <= 100) {
            try {
                conexion = new ServerSocket(12500);
                //Pido al SO que abra el puerto 12500 para la escucha de la conexion
            } catch (IOException e) {
                System.out.print("No se puede abrir el puerto:  12500");
                System.out.println(e.toString());
                System.exit(-1);
            }

            try {
                System.out.println("Esperando cliente...");
                
                canal = conexion.accept();
                salidaStream = new PrintWriter(canal.getOutputStream());

                salidaStream.println(contador);
                contador++;
                //Mando el mensaje
                salidaStream.flush(); //Limpio el canal
                salidaStream.close(); //cierro el Stream del canal. No genera excepcio0
            } catch (Exception e) {
                System.out.print("Error de conexión o del canal:  ");
                System.out.println(e.toString());
            }

            try {
                canal.close(); //cierro canal, puede elevar IOException
                conexion.close(); //cierro el ServerSocket
            } catch (IOException e) {
                System.out.println("Error al cerrar el canal");
            }
        }
    }

}
