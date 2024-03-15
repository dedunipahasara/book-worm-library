package  controller;
import bo.custom.AdminBo;
import bo.util.BoFactory;
import controller.AdminPrpfileController;
import dto.AdminDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.Routes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminLoginController {

    @FXML
    private Button btnLoginAdmin;

    @FXML
    private CheckBox checkboxPassword;

    @FXML
    private Label lblLgin;

    @FXML
    private Label lblpassword;

    @FXML
    private Label lblusername;

    @FXML
    private AnchorPane paneAdminLogin;

    @FXML
    private AnchorPane paneAdminLoginDetail;

    @FXML
    private TextField showPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private final AdminBo adminBo;

    // Constructor
    public AdminLoginController() {
        this.adminBo = (AdminBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.ADMIN);
    }

    @FXML
    void btnLoginAdminController(ActionEvent event) {
        login();
    }

    @FXML
    void checkBoxPasswordOnAction(ActionEvent event) {
        if (checkboxPassword.isSelected()) {
            showPassword.setText(txtPassword.getText());
            showPassword.setVisible(true);
            txtPassword.setVisible(false);
        } else {
            txtPassword.setText(showPassword.getText());
            txtPassword.setVisible(true);
            showPassword.setVisible(false);
        }
    }

    void login() {
        String userName = txtUserName.getText();
        String pswrd = txtPassword.getText();
        String pswrd1 = showPassword.getText();
        try {
            List<AdminDto> adminDtoList = adminBo.getAllAdmins();

            boolean loginSuccess = false;
            for (AdminDto dto : adminDtoList) {
                if (dto.getUserName().equals(userName) && (dto.getPassword().equals(pswrd) || dto.getPassword().equals(pswrd1))) {
                    try {
                        Navigation.navigate(Routes.ADMIN_DASHBOARD, paneAdminLogin);
                        fillProfileData();
                    } catch (IOException e) {
                        e.printStackTrace(); // Log the error or display an error message to the user
                    }
                    loginSuccess = true;
                    break;
                }
            }

            if (!loginSuccess) {
                new Alert(Alert.AlertType.ERROR, "Your login details are incorrect").show();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error or display an error message to the user
        }
    }

    private void fillProfileData() {
        AdminPrpfileController.setValue(txtUserName.getText());
    }
}


