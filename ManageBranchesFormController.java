package controller;

import Tm.BranchTm;
import bo.custom.AdminBo;
import bo.custom.BranchBo;
import bo.util.BoFactory;
import dto.AdminDto;
import dto.BranchDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

public class ManageBranchesFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<BranchTm, String> colAddress;

    @FXML
    private TableColumn<BranchTm, String> colAdmin;

    @FXML
    private TableColumn<BranchTm, String> colBranchName;

    @FXML
    private TableColumn<BranchTm, String> colContact;

    @FXML
    private AnchorPane paneBranch;

    @FXML
    private TableView<BranchTm> tblBranch;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    private int branchId;
    private int adminId;

    private BranchBo branchBo = (BranchBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.BRANCH);
    private AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.ADMIN);

    public void initialize() {
        setCellValueFactory();
    //   setTableValues();
        tableListener();
    }

    public void setTableValues() {
        ObservableList<BranchTm> observableList = FXCollections.observableArrayList();
        try {
            List<BranchDto> branchDtoList = branchBo.getAllBranches();
            for (BranchDto dto : branchDtoList) {
                AdminDto dto1 = adminBo.getAdminById(dto.getAdminId());
                observableList.add(new BranchTm(dto.getBranchName(), dto.getContact(), dto.getAddress(), dto1.getName()));
            }
            tblBranch.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }

    private void setCellValueFactory() {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void tableListener() {
        tblBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> setData(newValue));
    }

    private void setData(BranchTm newValue) {
        txtContact.setText(newValue.getContact());
        txtAdress.setText(newValue.getAddress());
        txtName.setText(newValue.getName());
        try {
            BranchDto branchDto = branchBo.getBranch(newValue.getName());
            branchId = branchDto.getBranchId();
            adminId = branchDto.getAdminId();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        var dto = new BranchDto(txtName.getText(), txtContact.getText(), txtAdress.getText(), AdminPrpfileController.id);

        try {
            branchBo.saveBranch(dto);
            new Alert(Alert.AlertType.CONFIRMATION, "New Branch Added").show();
         //   setTableValues();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Added Branch").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            branchBo.deleteBranch(branchId);
            new Alert(Alert.AlertType.CONFIRMATION, "Branch Deleted").show();
         //   setTableValues();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the branch").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            branchBo.updateBranch(new BranchDto(branchId, txtName.getText(), txtContact.getText(), txtAdress.getText(), adminId));
            new Alert(Alert.AlertType.CONFIRMATION, "Branch updated").show();
          //  setTableValues();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the branch").show();
        }
    }
}
