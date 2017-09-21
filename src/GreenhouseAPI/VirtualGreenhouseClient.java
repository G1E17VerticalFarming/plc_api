/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenhouseAPI;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class VirtualGreenhouseClient {
    private final String SERVERIP = "localhost";
    private final int COMPORT = 1025;
    private DatagramSocket clientSocket;
    
    public VirtualGreenhouseClient(){
        initClient();
    }
    
    private void initClient(){
        try {
            this.clientSocket = new DatagramSocket(this.COMPORT,InetAddress.getByName(this.SERVERIP));
        } catch (SocketException ex) {
            Logger.getLogger(VirtualGreenhouseClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(VirtualGreenhouseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        VirtualGreenhouseClient client = new VirtualGreenhouseClient();
        while(true){
            client.boot();
        }
    }

    /**
     * Firstly receive data
     * Print it to console
     * Create an empty byte array, length 110
     * Set DIRECTION to FROMPLC (see IMessage.java for values)
     * Send packet through DatagramPacket, with IP address and port from received datapacket
     */
    private void boot() {
        try {
            byte[] receiveData = new byte[110];
            DatagramPacket p = new DatagramPacket(receiveData,receiveData.length);
            this.clientSocket.receive(p);
            System.out.println(receiveData.toString());
            byte[] byt = new byte[110];
            byt[1] = 1;
            DatagramPacket pSend = new DatagramPacket(byt,0,byt.length,p.getAddress(),p.getPort());
            this.clientSocket.send(pSend);
        } catch (IOException ex) {
            Logger.getLogger(VirtualGreenhouseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
