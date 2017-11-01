/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import networking.FileServer;
import networking.MessageListener;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class SettingsController implements Initializable {
   @FXML
    private TextField serverMsgPort;

    @FXML
    private TextField serverFilePort;

    @FXML
    public static TextField clientMsgPort;

    @FXML
    public static TextField clientFilePort;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void StartServer() throws IOException{
         MessageListener listn;
       listn = new MessageListener(Integer.parseInt(serverMsgPort.getText()));
        listn.start();
        FileServer startFileServer;
        startFileServer = new FileServer(Integer.parseInt(serverFilePort.getText()));
        startFileServer.start();
        
    }
    
}
