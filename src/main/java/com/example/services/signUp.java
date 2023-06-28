package com.example.services;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class signUp {
    final int sucession = 201;
    public boolean cadastro(String name,String password) throws IOException, InterruptedException{
        Gson g = new Gson();
        InnersignUp user = new InnersignUp(name,password,InetAddress.getLocalHost().getHostAddress() );
        String body = g.toJson(user);
        System.out.println(body);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/security/singup"))
                .header("Content-type", "application/json")
                .POST(BodyPublishers.ofString(body))
                .build();
            HttpResponse response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
        if (response.statusCode() == sucession) {
            return true;
        } else {
            return false;
        }
    }
}

class InnersignUp {

    private String username;
    private String password;
    private String host;
    
    public InnersignUp(String name,String password,String host){
        this.username = name;
        this.password = password;
        this.host = host;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getName() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
     @Override
    public String toString() {
        return "InnersignUp{" +
                "name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
    
    
}
