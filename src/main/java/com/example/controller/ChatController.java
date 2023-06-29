package com.example.controller;

import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.example.dto.FriendDTO;
import com.example.dto.Userdto;
import com.example.services.ConsumeUser;
import com.example.services.SendMessager;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ChatController implements Initializable{

    private HashMap<String,ArrayList<String>> messagerhistory = 
    new HashMap<String,ArrayList<String>>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private Label username;
    @FXML
    private Button buttonSend;

    @FXML
    private TextArea messagerText;

    @FXML
    private ListView<String> viewMessager;
    @FXML
    private ListView<String> friendsList;
    @FXML
    private TextField findUser;
    @FXML
    void findFriend(MouseEvent event) {
        FriendDTO friend = new ConsumeUser().findFriend(findUser.getText());
        if (friend == null) {
            Alert erro = new Alert(AlertType.ERROR);
             erro.setTitle("Não encontrado");
                erro.setContentText("Usuario não encontrado");
                erro.setResizable(false);
                erro.showAndWait();
        }else{
            friendsList.getItems().add(friend.getUsername());
        }
    }

    @FXML
    void sendMessager(MouseEvent event) {
       new SendMessager()
       .send(friendsList.getSelectionModel().getSelectedItem()+";"+messagerText.getText()); 
       try {
        messagerhistory.get(friendsList.getSelectionModel()
     .getSelectedItem())
     .add(Userdto.getInstance().getName()+": "+messagerText.getText());
     } catch (NullPointerException e) {
        messagerhistory.put(friendsList.getSelectionModel().getSelectedItem(), new ArrayList<String>());
        messagerhistory.get(friendsList.getSelectionModel()
     .getSelectedItem())
     .add(Userdto.getInstance().getName()+": "+messagerText.getText());
     }
       viewMessager.getItems().add(messagerText.getText());
        
     }
     
    private ConsumeUser consumeuser = new ConsumeUser();
     
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       username.setText(Userdto.getInstance().getName());
        friendsList.setOnMouseClicked((event)->{
            String friend = friendsList.getSelectionModel().getSelectedItem();
            viewMessager.getItems().clear();
        //     consumeuser.FindMessager(friend,Userdto.getInstance().getId()).forEach((item)->{
        //     viewMessager.getItems().add(item.getSend()+":"+item.getMessager());
        //    });
       try {
           messagerhistory.get(friend).forEach(msg->{

                viewMessager.getItems().add(msg);
            });
            
       } catch (NullPointerException e) {
            Logger.getLogger("ALERT").info("Nenhuma mensagem");
       }
         
        });
      
        messagerText.addEventFilter(KeyEvent.KEY_PRESSED,key ->{
            if (key.getCode().equals(KeyCode.ENTER)) {
                sendMessager(null);
                messagerText.setText("");
            }
        });
        Thread receiveMessager = new Thread(()->{
           var messager = new SendMessager();   
           
            while (true) {
       
                Thread.currentThread().setName("MESSAGER");
                String msg;
                try {
                    msg = messager.receive();
                   try {
                    
                 
                    if (messager.verifyMessager(msg)) {
                        String[] mess = msg.split(";");  
                        if (messagerhistory.get(mess[0]) == null) {
                            messagerhistory.put(mess[0],new ArrayList<String>());
                            messagerhistory.get(mess[0]).add(mess[0]+": "+mess[1]);
                        }else{
                            messagerhistory.get(mess[0]).add(mess[0]+": "+mess[1]);
                        }
                        if (friendsList.getSelectionModel()
                        .getSelectedItem().equals(mess[0])) {
                            viewMessager.getItems().add(mess[0]+": "+mess[1]);
                        }
                  
                        //viewMessager.getItems().add(msg);
                    } } catch (NullPointerException e) {
                        
                   }
                      
                } catch (SocketException e) {
                    try {
                        Thread.sleep(5000);
                        
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                } 
              
            }
        });
        receiveMessager.start();

        Task<List<String>> testTask = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                return new ConsumeUser().friendList(Userdto.getInstance().getId());
            }
        };
        testTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
                friendsList.setItems(FXCollections.observableArrayList(testTask.getValue()));
            };
        });
        new Thread(testTask).start();
    }
    
  
    
}
