package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static AnchorPane pane; // Ensure proper initialization

    public static void initUI(String location) throws IOException {
        // Load the FXML file and retrieve the root node
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/" + location));
        Parent root = loader.load();

        // Add children of the root node to the pane
        Navigation.pane.getChildren().addAll(root.getChildrenUnmodifiable());
    }

    public static void navigate(Routes route, AnchorPane anchorPane) throws IOException {
        Navigation.pane = anchorPane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case ADMIN_LOGIN:
                window.setTitle("ADMIN Login Form");
                initUI("AminLoginController.fxml");
                break;
            case ADMIN_DASHBOARD:
                window.setTitle("ADMIN DASHBOARD");
                initUI("AddminDashboard.fxml");
                break;
            case TRANSACTION:
                window.setTitle("Transaction history");
                initUI("transaction.fxml");
                break;
            case MANAGE_BOOKS:
                window.setTitle("Manage Books");
                initUI("manage_book.fxml");
                break;
            case MANAGE_BRANCHES:
                window.setTitle("Manage Branches");
                initUI("manage_branches.fxml");
                break;
            case BACK:
                window.setTitle("Admin Login");
                initUI("AminLoginController.fxml");
                break;

            case BACK_ADMIN_DASHBOARD:
                window.setTitle("Admin Dashboard");
                initUI("AddminProfile.fxml");
                break;

            default:
                System.out.println("Mukuth nanne");

        }
    }
}

