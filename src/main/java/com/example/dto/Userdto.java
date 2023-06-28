package com.example.dto;

import java.net.Socket;

/**
 * userdto
 */
public class Userdto {
 
    private  Socket socket;
    private  int id;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static volatile Userdto instance;

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

   

    public static Userdto getInstance(){
        if ( instance == null) {
            instance = new Userdto();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}