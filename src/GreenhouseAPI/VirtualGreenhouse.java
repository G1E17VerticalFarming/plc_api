/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenhouseAPI;

import PLCCommunication.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class VirtualGreenhouse implements IGreenhouse {
    private final String SERVERIP = "localhost";
    private final int COMPORT = 1025;
    private DatagramSocket clientSocket;
    
    /* State variables for the virtual greenhouse */
    private double insideTemperature;
    private double outsideTemperature;
    private int insideTempSetPoint;
    private double waterLevel;
    private double moisture;
    private int redLightLevel;
    private int blueLightLevel;
    private int fanSpeed;
    private BitSet errors;
    private byte[] status;
    
    public VirtualGreenhouse(){
        initClient();
    }
    
    private void initClient(){
        try {
            this.clientSocket = new DatagramSocket(this.COMPORT,InetAddress.getByName(this.SERVERIP));
        } catch (SocketException ex) {
            Logger.getLogger(VirtualGreenhouse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(VirtualGreenhouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        VirtualGreenhouse client = new VirtualGreenhouse();
        while(true){
            client.boot();
        }
    }

    /**
     * Firstly receive data
     * Print it to console
     * Create an empty byte array, length 110
     * Set DIRECTION to FROMPLC (see IMessage.java for values)
     * Send DatagramPacket through DatagramSocket, with IP address and port from received datapacket
     */
    private void boot() {
        try {
            byte[] receiveData = new byte[110];
            DatagramPacket p = new DatagramPacket(receiveData,receiveData.length);
            this.clientSocket.receive(p);
            System.out.println(Arrays.toString(receiveData));
            byte[] byt = new byte[110];
            byt[1] = 1;
            DatagramPacket pSend = new DatagramPacket(byt,0,byt.length,p.getAddress(),p.getPort());
            this.clientSocket.send(pSend);
        } catch (IOException ex) {
            Logger.getLogger(VirtualGreenhouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean SetTemperature(int kelvin) {
        int celcius = kelvin - 273;
        System.out.println("Heating up greenhouse to " + celcius + " degrees celcius.");
        this.insideTempSetPoint = celcius;
        return true;
    }

    @Override
    public boolean SetMoisture(int moist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean SetRedLight(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean SetBlueLight(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AddWater(int sec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AddFertiliser(int sec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AddCO2(int sec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double ReadTemp1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double ReadTemp2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double ReadMoist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double ReadWaterLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double ReadPlantHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitSet ReadErrors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ResetError(int errorNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] GetStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean SetFanSpeed(int speed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
