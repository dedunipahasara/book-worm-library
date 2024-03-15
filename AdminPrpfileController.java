package controller;

import bo.custom.AdminBo;
import bo.util.BoFactory;
import dto.AdminDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminPrpfileController {
    public AnchorPane pane;


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane paneAddmin;

    @FXML
    private AnchorPane paneAdminProfile;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPAssword;

    @FXML
    private TextField txtUserNAme;
    public static int id;

    private static AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.ADMIN);

    public void initialize(){
        setFormValues();


    }
    private void setFormValues() {
        try {
            AdminDto dto = adminBo.getUserById(id);
            txtEmail.setText(dto.getEmail());
            txtPAssword.setText(dto.getPassword());
            txtName.setText(dto.getName());
            txtUserNAme.setText(dto.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        formType();
    }
    private void formType() {
        txtUserNAme.setEditable(false);
        txtPAssword.setEditable(false);
        txtEmail.setEditable(false);
        txtName.setEditable(false);
        btnAdd.setVisible(true);
        btnDelete.setVisible(true);
        btnLogout.setVisible(true);
        btnUpdate.setVisible(true);
        btnSaveChanges.setVisible(false);
    }
    @FXML
    void BtnLOgOutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to log out?");
        alert.setContentText("Choose your option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Admin_login_form.fxml")))); // Load and set the scene for login form
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Login form");
            stage.centerOnScreen();
        }

    }

    @FXML
    void btnAddAdminOnAction(ActionEvent event) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/Add_new_admin/.fxml"));
            pane.getChildren().removeAll();

            pane.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to log out?");
        alert.setContentText("Choose your option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            try {
                adminBo.deleteAdmin(id);
                new Alert(Alert.AlertType.INFORMATION,"Your Account is deleted");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AminLoginController.fxml")))); // Load and set the scene for login form
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Login form");
                stage.centerOnScreen();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }




    @FXML
    void btnSaveChangesOnAction(ActionEvent event) {
        try {

            var dto = new AdminDto(id,txtName.getText(),txtEmail.getText(),txtUserNAme.getText(),txtPAssword.getText());
            adminBo.updateAdmin(dto);


            new Alert(Alert.AlertType.CONFIRMATION,"Your data Updated Successfully").show();
            formType();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private static int getAdminId(String userName) throws SQLException {
        AdminDto dto = adminBo.getAdminByEmail(userName);
        return  dto.getAdminId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        txtUserNAme.setEditable(false);
        txtPAssword.setEditable(false);
        txtEmail.setEditable(false);
        txtName.setEditable(false);
        btnAdd.setVisible(true);
        btnDelete.setVisible(true);
        btnLogout.setVisible(true);
        btnUpdate.setVisible(true);
        btnSaveChanges.setVisible(false);

    }
    public static void setValue(String text){
        try {
            id = getAdminId(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}