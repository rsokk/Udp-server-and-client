/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;


public class Udpclient {

    public static String Random() {

        // Generate a random number
        double rand = Math.random() * 100.0;

        // Format to a string with one digit
        String random = String.format("%.1f%n", rand);

        return random;
    }

    public static void main(String args[]) throws Exception {
       
       
        InetAddress IPAddress = InetAddress.getByName(null);
        DatagramSocket clientSocket = new DatagramSocket();
        int Port = 9876;
        byte[] sendData = new byte[1024];

            while (true) {
            	try {
               
                sendData = Random().getBytes("UTF-8");
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);

                clientSocket.send(sendPacket);

                System.out.println("Send nr : " + new String(sendData));
            	
            	} catch (IOException e) { // Catch exception if connection to server is bad
    				System.out.println("Server error: " + e.getMessage());
    				clientSocket.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            	
                TimeUnit.SECONDS.sleep(1);
            }
         }
    }

