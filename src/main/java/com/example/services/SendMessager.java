package com.example.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.SocketException;

import com.example.dto.Userdto;

public class SendMessager {
    PrintStream output;
    BufferedReader in; 


    public void send(String msg){
        try {
            output = new PrintStream(Userdto.getInstance().getSocket().getOutputStream(), true);
            output.println(msg);
        } catch (IOException e) {
           
            e.printStackTrace();
        }

    }
    public String receive() throws SocketException {
        try {
            in = new BufferedReader(new InputStreamReader(Userdto.getInstance().getSocket().getInputStream()));
               return in.readLine();
         } catch (IOException e) {
            
         }
         return null;

       
    }
    public boolean verifyMessager(String msg){
       try {
        String[] verify = msg.split(";");
        if (verify.length == 2) {
            return true;
        }} catch (NullPointerException e) {
       
       }
        return false;
    }

}
