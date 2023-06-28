package com.example.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.dto.FriendDTO;
import com.example.dto.MessagerDTO;
import com.example.dto.Userdto;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class ConsumeUser  {
    List<String> list = new ArrayList<>(); 
    List<MessagerDTO> msg = new ArrayList<>();
    Gson gson = new Gson();
    HttpClient client = HttpClient.newHttpClient();
    
    public List<String> friendList(int id){
        HttpRequest request = HttpRequest.newBuilder(URI
        .create("http://localhost:8080/friend/list/"+id))
        .GET()
        .header("Content-type", "application/json")
        .build();
        try {
          FriendDTO[] friends = gson.fromJson(client.send(request, BodyHandlers.ofString())
            .body(),FriendDTO[].class);
           for (FriendDTO friendDTO : friends) {
           list.add(friendDTO.getUsername());
           }
            } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        return list;
    }
    // public List<MessagerDTO> FindMessager(String name,int id){
    //     HttpRequest request = HttpRequest
    //     .newBuilder(URI.create("http://localhost:8080/messager/"+id+"/"+name))
    //     .GET().header("Content-type", "application/json")
    //     .build();    
    //     MessagerDTO[] messager;
       
    //     //msg = null;
    //     try {
    //         messager = gson.fromJson(client.send(request, BodyHandlers.ofString()).body(), MessagerDTO[].class);
    //         for (MessagerDTO messagerDTO : messager) {
    //             msg.add(messagerDTO);
    //         }
    //     } catch ( IOException | InterruptedException | NullPointerException e) {
          
    //        Logger.getLogger("ERRO:").info("Nenhuma messagem encontrada");
    //     }
       
    //  return msg;
    // }
    public FriendDTO findFriend(String name){
        HttpRequest request = HttpRequest
        .newBuilder(URI.create("http://localhost:8080/friend/list/new/"+Userdto.getInstance().getName()+"/"+name))
        .GET()
        .header("Content-type", "application/json")
        .build();
        try {
            FriendDTO friend = gson.fromJson(client.send(request, BodyHandlers.ofString()).body(), FriendDTO.class);
            if (friend != null) {
                return friend;
            }
           
        } catch (JsonSyntaxException | IOException | InterruptedException e) {
            
        }
        return null;
    }
}
