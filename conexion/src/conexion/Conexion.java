/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.IOException;

/**
 *
 * @author Diru
 */
public class Conexion {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws InterruptedException, IOException {
//   Ejecuta el Suministrador
        Runtime.getRuntime().exec("java -jar Servidor.jar");

        int contador = 1;
        while (contador <= 100) {
            Runtime.getRuntime().exec("java -jar Cliente.jar " + (contador));
            Thread.sleep(100);
            contador++;
        }
        
    }
}
