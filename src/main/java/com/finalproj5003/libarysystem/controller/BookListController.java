package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.service.BookService;
import com.finalproj5003.libarysystem.service.RecordService;
import com.finalproj5003.libarysystem.utils.AlertUtil;
import com.finalproj5003.libarysystem.utils.UserUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class BookListController implements Initializable {
    @Autowired
    BookService bookService;
    @Autowired
    RecordService recordService;
    @FXML
    private TextField tfKeyword;

    @FXML
    private TableColumn<Book, String> bookNameCol;

    @FXML
    private TableColumn<Book,Integer> idCol;
    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, Integer> stockCol;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> editionCol;

    @FXML
    private TableColumn<Book, String> descriptionCol;

    @FXML
    void onSearch(ActionEvent event)
    {
        String keyword=tfKeyword.getText();
        List<Book> bookList=bookService.findAll(keyword);
        ObservableList<Book> fxBookList=FXCollections.observableList(bookList);
        bookTable.setItems(fxBookList);
        bookTable.refresh();
    }

    @FXML
    void onRent(ActionEvent event)
    {
        Book book=bookTable.getSelectionModel().getSelectedItem();
        if(book!=null)
        {
            book=bookService.getBookById(book.getId());
            boolean result = AlertUtil.confirm("Are you sure want to rent this book?");
            if (result) {
                if(!bookService.hasStock(book))
                {
                    AlertUtil.erro("fail: please check the stock");
                }
                else if(recordService.isRenting(UserUtil.currentUser,book))
                {
                    AlertUtil.erro("fail: you have already rent this");
                }
                else
                {
                    bookService.rentBook(book);
                    recordService.addRecord(book,UserUtil.currentUser,"rent");
                    AlertUtil.info("rent success");
                }
                onSearch(event);
            }
        }else
        {
            AlertUtil.erro("please choose a book");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        List<Book> bookList=bookService.findAll();
        ObservableList<Book> fxBookList= FXCollections.observableList(bookList);
        bookTable.setItems(fxBookList);
    }
}
