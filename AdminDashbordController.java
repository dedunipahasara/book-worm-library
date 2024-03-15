package controller;

import Tm.TransactionTm;
import bo.custom.BookBo;
import bo.custom.TransactionBo;
import bo.custom.UserBo;
import bo.util.BoFactory;
import dto.TransactionDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminDashbordController {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDashBoard;

    @FXML
    private Button btnManageBook;

    @FXML
    private Button btnManageBranches;

    @FXML
    private Button btnTransactionHistory;

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<?, ?> colBorrowername;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private Label lblTootalBokks;

    @FXML
    private Label lblTotalUsers;

    @FXML
    private AnchorPane paneAdminDashoard;

    @FXML
    private AnchorPane paneSidePane;

    @FXML
    private TableView<TransactionTm> tblDetailsBorrowings;

    @FXML
    private Text txtBooks;

    @FXML
    private Text txtUsers;
    private BookBo bookBo = (BookBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.BOOK);
    private UserBo userBo = (UserBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.USER);
    private TransactionBo transactionBo = (TransactionBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.TRANSACTION);
    private ObservableList<TransactionTm> observableList = FXCollections.observableArrayList();
    public void initialize(){
        //loadUserCount();
       // loadBookCount();
       // loadTodayBook();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBorrowername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

    }

    private void loadTodayBook() {
        try {
            List<TransactionDto> dtoList = transactionBo.getTodayCheckOuts();
            System.out.println(dtoList);
            for (TransactionDto dto : dtoList){

                observableList.add(new TransactionTm(dto.getBranchName(), dto.getBookId(), dto.getDueDate(),dto.getUserName()));
            }
            tblDetailsBorrowings.getItems().clear();
            tblDetailsBorrowings.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadBookCount() {
        try {
            long bookCount = bookBo.getBookCount();
            lblTootalBokks.setText(String.valueOf(bookCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /*private void loadUserCount() {
        try {
            long userCount = userBo.getUserCount();
            lblTotalUsers.setText(String.valueOf(userCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/




    @FXML
    void BtnTransactionHistoryOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.TRANSACTION,paneAdminDashoard);


    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ADMIN_DASHBOARD,paneAdminDashoard);

    }

    @FXML
    void btnManageBookOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGE_BOOKS,paneAdminDashoard);

    }

    @FXML
    void btnManageBranchesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGE_BRANCHES,paneAdminDashoard);

    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.BACK,paneAdminDashoard);


    }


}
