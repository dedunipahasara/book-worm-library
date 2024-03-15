package controller;

import bo.custom.AdminBo;
import bo.util.BoFactory;
import dto.AdminDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.RegexPattern;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddNewAdminControler {

    @FXML
    private Button btnSaveChanges;

    @FXML
    private AnchorPane paneADDnew;

    @FXML
    private AnchorPane paneAddmin;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPAssword;

    @FXML
    private TextField txtUserNAme;
    public AnchorPane pane;
    private AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.ADMIN);

    @FXML
    void btnSaveChangesOnAction(ActionEvent event) throws IOException {
        //if (validation()) {

            try {
                adminBo.saveAdmin(new AdminDto(txtName.getText(), txtEmail.getText(), txtUserNAme.getText(), txtPAssword.getText()));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Admin Adeed Successfully").show();

        Navigation.navigate(Routes.ADMIN_LOGIN,paneADDnew);
        }



   /* public boolean validation() {

    }*/



}




