package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginController {
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private ImageView imglibrary;

    @FXML
    private Label lblName;

    @FXML
    private AnchorPane paneUser;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblPasswordError;

    @FXML
    private Label lblUserNameError;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) paneUser.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }
}
