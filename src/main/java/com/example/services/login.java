package com.example.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.example.dto.Userdto;

public class login {
    Socket socket = new Socket();
    public enum status{OK,ERROR};
    
    public boolean loginVerify(String username,String password) throws IOException{
        try {
            socket.connect(new InetSocketAddress("18.212.37.109", 3030));
            PrintStream output = new PrintStream(socket.getOutputStream(),true);
            output.println(username+";"+password);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if (in.readLine().equals(status.OK.name())) {
                Userdto.getInstance().setSocket(socket);
                Userdto.getInstance().setId(Integer.parseInt(in.readLine()));
                Userdto.getInstance().setName((in.readLine()));
                return true;
            } 
            } catch (ConnectException e) {
                return false;
            }
            return false;
    }
}
