package controller;

import Tm.BookTm;
import bo.custom.BookBo;
import bo.custom.BranchBo;
import bo.util.BoFactory;
import dto.BookDto;
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

public class ManageBookFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnadd;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private ComboBox<String> comboBranch;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private AnchorPane paneAddBook;

    @FXML
    private TableView<BookTm> tblBookDetails;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtGenere;

    @FXML
    private TextField txtauthor;
    private  int id;
    private BookBo bookBo = (BookBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.BOOK);
    private BranchBo branchBo = (BranchBo) BoFactory.getBoFactory().getBoType(BoFactory.BoTypes.BRANCH);
    public void initialize(){
        setBranches();
        setStatus();
        setCellvalueFactory();
        tableListener();
        loadAllBooks();

    }


    private void setCellvalueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branch") );
    }
    private void tableListener() {
        tblBookDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData((BookTm) newValue);
        });
    }

    private void setData(BookTm newValue) {
        txtGenere.setText(newValue.getCategory());
        txtauthor.setText(newValue.getAuthor());
        txtBookTitle.setText(newValue.getTitle());
        comboStatus.setValue(newValue.getStatus());
        comboBranch.setValue(newValue.getBranch());
        try {
            id = bookBo.getBook(txtBookTitle.getText()).getBookId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void BtnAddOnaction(ActionEvent event) {
        try {
            BranchDto branchDto = branchBo.getBranch(String.valueOf(comboBranch.getValue()));
            bookBo.saveBook(new BookDto(txtBookTitle.getText(),txtauthor.getText(),txtGenere.getText(), (String) comboStatus.getValue(), branchDto.getBranchId()));
            new Alert(Alert.AlertType.CONFIRMATION,"New Book Added").show();
            loadAllBooks();
            tblBookDetails.refresh();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to added new book").show();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleetOnaction(ActionEvent event) {
        try {
            bookBo.deleteBook(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Book deleted").show();
            loadAllBooks();
            tblBookDetails.refresh();
        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR,"Failed to delete book").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            int branchId = branchBo.getBranch((String) comboBranch.getValue()).getBranchId();
            bookBo.updateBook(new BookDto(id,txtBookTitle.getText(),txtauthor.getText(),txtGenere.getText(),comboStatus.getValue(),branchId));
            loadAllBooks();
            tblBookDetails.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void comboAvailabilityOnaction(ActionEvent event) {

    }

    @FXML
    void comboBranchOnAction(ActionEvent event) {

    }
    public void setBranches(){
        ObservableList<String> branchNames = FXCollections.observableArrayList();
        try {
            List<BranchDto> branches = branchBo.getAllBranches();
            for (BranchDto dto : branches){
                branchNames.add(dto.getBranchName());
            }
            comboBranch.setItems(branchNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setStatus(){
        ObservableList<String> status = FXCollections.observableArrayList("Available", "Unavailable");
        comboStatus.setItems(status);


    }
    public void loadAllBooks(){
        ObservableList<BookTm> observableList = FXCollections.observableArrayList();
        try {
            List<BookDto> bookDtos = bookBo.getAllBook();
            for (BookDto bookDto :bookDtos){
                BranchDto dto = branchBo.getBranchById(bookDto.getBranchId());
                observableList.add(new BookTm(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getCategory(), bookDto.getAvailability(), dto.getBranchName()));
            }
            tblBookDetails.getItems().clear();
            tblBookDetails.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
