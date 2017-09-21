/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GreenhouseAPI;

import PLCCommunication.*;
import java.util.BitSet;

/**
 * API tester
 * @author sps
 */
public class TestGreenhouse 
{
    public static void main(String[] args) 
    {
        PLCConnection con = new UDPConnection(1025, "localhost");
        //PLCConnection con = new UDPConnection(5000, "192.168.0.40");
        //PLCConnection con = new SerialConnection("COM4");
        //SerialConnection.getPortList("COM1");
        
        IGreenhouse api = new Greenhouse(con);
//        while(true){
//            int i = 0;
//            for(;i <= 100;i++){
//                api.SetRedLight(i);
//                api.SetBlueLight(i);
//            }
//            for(;i >=0;i--){
//                api.SetRedLight(i);
//                api.SetBlueLight(i);
//            }
//        }
//        api.SetRedLight(50);
//        api.SetBlueLight(100);
//        api.SetTemperature(273 + 25);
//        api.SetFanSpeed(2);
        double outdoorTemperature; 
        while (true)
           outdoorTemperature = api.ReadTemp1();
        
        
       
        
                
        //System.exit(3);
    }
    
}
