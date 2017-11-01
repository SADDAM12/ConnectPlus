package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import jdbc.DBManager;
import networking.FileClient;
import networking.MessageTransmitter;



/**
 * Created by tuski-Revolve on 26-Jul-17.
 */
public class ChatLayout {


 @FXML
    private JFXListView chatFriendList;

    @FXML
    public static JFXTextArea chatBox;

    @FXML
    private JFXTextField sendMessage;

    @FXML
    private JFXButton sendMsg;
     


    public void initialize() throws SQLException {
           
HashMap<String, String> userlist= DBManager.showFriendList();
 
          Set set = userlist.entrySet();
        Iterator iterator = set.iterator();
        //Map.Entry mentry = (Map.Entry)iterator.next();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            chatFriendList.getItems().add(mentry.getKey());
            System.out.println(mentry.getKey()+" ip= "+mentry.getValue());
           }
    }
    
    public void sendMessage(){
        
        String msg="("+LoginForm.userName+") "+sendMessage.getText();
        MessageTransmitter sendMsg=
                new MessageTransmitter(msg,"localhost",Integer.parseInt(SettingsController.clientMsgPort.getText()));

            sendMsg.start();
            
            chatBox.appendText(msg+System.lineSeparator());
            sendMessage.setText("");            
    }
    
    
 public void sendFile(){
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png","*.ico"));
        File selectedFile=fc.showOpenDialog(null);
        long filelength= selectedFile.length();
         FileClient sendFile= new FileClient("localhost", Integer.parseInt(SettingsController.clientFilePort.getText()), selectedFile.getAbsolutePath(),selectedFile.getName(),filelength);
           System.out.println(selectedFile.getAbsolutePath());
            chatBox.appendText("Sending File "+selectedFile.getName()+System.lineSeparator());
            sendMessage.setText("");
            
    }


}
