package com.example.controller;

import java.io.IOException;

import com.example.EntryPoint;
import com.example.services.login;
import com.example.services.signUp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginText;

    @FXML
    private TextField loginText1;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField passwordText1;

    @FXML
    private PasswordField passwordText11;

    @FXML
    void buttonActionCadastro(MouseEvent event) {
            try {
               if (!new signUp().cadastro(loginText1.getText(),passwordText1.getText())) {
                  Alert erro = new Alert(AlertType.ERROR);
                erro.setTitle("ERRO");
                erro.setContentText("Usuario já existe");
                erro.setResizable(false);
                erro.showAndWait();
      }
               
                
            } catch (IOException | InterruptedException e) {
                
            }
    }

    @FXML
    void buttonActionLogin(MouseEvent event) {
     
        if (!loginText.getText().isEmpty() || !passwordText.getText().isEmpty()) {
            try {
                if (new login().loginVerify(loginText.getText(), passwordText.getText())) {
                 new EntryPoint().showMainScreen();
            
            
                }else{
                Alert erro = new Alert(AlertType.ERROR);
                erro.setTitle("ERRO");
                erro.setContentText("Usuario e senha incorretos");
                erro.setResizable(false);
                erro.showAndWait();
      }
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    

        }else{
            Alert erro = new Alert(AlertType.ERROR);
            erro.setTitle("ERRO");
            erro.setContentText("Usuario e senha incorretos");
            erro.setResizable(false);
            erro.showAndWait();
  }
    }
}
