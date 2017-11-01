package main;

import static connect.Connect.pStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static jdbc.DBManager.loginUser;
import static jdbc.DBManager.registerUser;

/**
 * Created by tuski-Revolve on 28-Jun-17.
 */
public class LoginForm {

    @FXML
    private TextField logUserName;
//    String logUserName=loginUserName.getText().toString();
    @FXML
    private PasswordField logPassword;
    //   String logPassword=loginPassword.getText().toString();
    @FXML
    private TextField registerUserName;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerEmail;
    @FXML
    private AnchorPane mainLayout;
    @FXML
    private Button loginBtn;
    public static String userName;

    @FXML
    public void register() {
        String regUserName = registerUserName.getText().toString();
        String regPassword = registerPassword.getText().toString();
        String regEmail = registerEmail.getText().toString();
        
        try {
            registerUser(regUserName, regEmail, regPassword);
            System.out.print("Registered");

        } catch (SQLException e) {
            System.out.print("Use different username");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    public void login() {
        Stage thisStage = (Stage) loginBtn.getScene().getWindow();
        String loginUserName = logUserName.getText().toString();
        String loginPassword = logPassword.getText().toString();
            userName=loginUserName;
        try {
            boolean reply = loginUser(loginUserName, loginPassword);
            if (reply == true) {
                System.out.print("Login succesful!");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/WorkStation.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Connect+");
                stage.show();
              
                thisStage.close();
            } else {
                System.out.print("error");
            }
            Notifications nt = Notifications.create().title("Action").text("Login Successful")
                    .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
            nt.showConfirm();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    



    
}
