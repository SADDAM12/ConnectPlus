/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbc.DBManager;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class UserProfileController implements Initializable {

    @FXML
    private ImageView profilePicImgLabel;

    @FXML
    private Button uploadPic;

    @FXML
    private TextField nameField;

    @FXML
    private TextField address;
    private Image image;
    private InputStream is;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void setImage() throws FileNotFoundException{
         FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG file", "*.jpg"));
        if(selectedFile!=null)
        {
        image=new Image(selectedFile.toURI().toString(),100,150,true,true);
        profilePicImgLabel.setImage(image);
        is=new FileInputStream((selectedFile));
        }
        
    }
    
    
    public void updateInfo() throws Exception{
        
        DBManager.updateUser(LoginForm.userName, nameField.getText(), address.getText(), is);
        
    }
    
    
    
    
    
    
}
