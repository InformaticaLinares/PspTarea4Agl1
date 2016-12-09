/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Diru
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket canal = null; //Socket para el canal de conexión con el escritor
        BufferedReader entrada = null; // Buffer para leer del stream
        String datosEntrada;  //para los valores que vamos leyendo del canal
        int clave = Integer.parseInt(args[0]);
        boolean conex = false, leido = false;
        int numero;
        String fichlog = "LogResultado.txt";
        String ficherr = "LogError.txt";
        PrintStream ps = null;
        PrintStream pe = null;
        try {
            ps = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File(fichlog), true)), true);
            pe = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File(ficherr), true)), true);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            System.setOut(ps);
            System.setErr(pe);
        }
        ps.flush();
        pe.flush();
        while (leido == false) {
            if (conex == false) {
                try {
                
                    canal = new Socket("localhost", 12500);
                    conex = true;
                } catch (Exception e) {
                    System.err.println("Cliente" +clave+ ". Error de conexion");
                    conex = false;
                    System.exit(-1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.toString()); 
                    }
                }
            } else {
                try {
                 
                    entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
                    
                    datosEntrada = entrada.readLine();

                    numero = Integer.parseInt(datosEntrada);
                    System.out.println("Cliente " +clave+ ". Numero Leido: "+numero);
                    leido = true;
                    entrada.close();

                } catch (IOException e) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.toString()); 
                    }
                    System.err.println("Cliente " +clave+ ". Error en el canal");
                    conex = false;
                    leido = false;
                    System.err.println(e.toString()); 
                }
                canal.close();
            }
        }
    }

}
