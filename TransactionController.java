package controller;

import Tm.TransactionTm;
import bo.custom.TransactionBo;
import bo.util.BoFactory;
import dto.TransactionDto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static util.Navigation.navigate;

public class TransactionController {
    @FXML
    private AnchorPane panetransaction;

    @FXML
    private Button btnOverDueDate;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookNameId;

    @FXML
    private TableColumn<?, ?> colBranchId;

    @FXML
    private TableColumn<?, ?> colTransactionId;

    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn<?, ?> colduedate;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableView<TransactionTm> tblTransaction;

    @FXML
    private TextField txtsearch;

    @FXML
    private TableColumn<?, ?> userId;
    private TransactionBo transactionBo = (TransactionBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.TRANSACTION);
    private ObservableList<TransactionTm> observableList = FXCollections.observableArrayList();
    private ObservableList<TransactionTm> userFiltered = FXCollections.observableArrayList();
    private ObservableList<TransactionTm> typeFiltered = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
       // loadAllTransactions();
        setComboBoxValues();
    }

    private void setComboBoxValues() {
        ObservableList<String> list = FXCollections.observableArrayList("All","Not returned Yet");
        comboBox.setItems(list);

    }

    private void loadAllTransactions() {
        try {
            List<TransactionDto> dtos = transactionBo.getAll();
            for (TransactionDto dto : dtos){
                if (dto.getReturn().equals(true)) {
                    observableList.add(new TransactionTm(
                            "T00" + dto.getId(),
                            "B00" + dto.getBookname(),
                            dto.getBookId(),
                            dto.getUserName(),
                            dto.getBranchName(),
                            formatDateTime(dto.getBorrowed()),
                            formatDateTime(dto.getReturnedDate())));
                } else {
                    observableList.add(new TransactionTm(
                            "T00" + dto.getId(),
                            "B00" + dto.getBookname(),
                            dto.getBookId(),
                            dto.getUserName(),
                            dto.getBranchName(),
                            formatDateTime(dto.getBorrowed()),
                            "Not returned yet"));
                }

            }
            tblTransaction.getItems().clear();
            tblTransaction.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private void setCellValueFactory() {
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        colduedate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colType.setCellValueFactory(new PropertyValueFactory<>("borrow"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colduedate.setCellValueFactory(new PropertyValueFactory<>("bookName"));
    }
    @FXML
    void btnOverDueDateOnAction(ActionEvent event) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/UnreturnBooks.fxml"));
            if (fxml != null) {
                Platform.runLater(() -> {
                    panetransaction.getChildren().clear();
                    panetransaction.getChildren().add(fxml);
                });
            } else {
                System.err.println("FXML file not found or failed to load: UnreturnBooks.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file: UnreturnBooks.fxml", e);
        }
    }

    @FXML
    void cmboTypeOnAction(ActionEvent event) {
        typeFiltered.clear();
        String selected = comboBox.getValue();
        if (selected.equals("All")){
            typeFiltered.addAll(observableList);
        }else if (selected.equals("Not returned Yet")) {
            for (TransactionTm transactionTm : observableList) {
                if (transactionTm.getReturnDate().equals("Not returned yet")) {
                    typeFiltered.add(transactionTm); // Add only non-returned transactions
                }
            }
        }
        tblTransaction.getItems().setAll(typeFiltered);

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        userFiltered.clear();
        for (TransactionTm transactionTm : observableList) {
            if (transactionTm.getUserName().equals(txtsearch.getText())) {
                userFiltered.add(transactionTm); // Add matching transactions
            }
        }
        tblTransaction.getItems().setAll(userFiltered);
    }
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Customize format (e.g., "dd/MM/yyyy")
        return dateTime.format(formatter);
    }
    @FXML
    void btnBAckButtonOnaction(ActionEvent event) throws IOException {
        navigate(Routes.BACK_ADMIN_DASHBOARD,panetransaction);

    }

    }


