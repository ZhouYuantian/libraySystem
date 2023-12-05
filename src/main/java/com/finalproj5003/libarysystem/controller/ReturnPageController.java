package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.entity.Record;
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
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class ReturnPageController implements Initializable {
    @Autowired
    RecordService recordService;
    @Autowired
    BookService bookService;
    @FXML
    private TableColumn<Record, String> dateCol;

    @FXML
    private TableColumn<Record, String> bookNameCol;

    @FXML
    private TableView<Record> recordTable;

    @FXML
    private TableColumn<Record, Integer> bookIdCol;

    @FXML
    void onReturn(ActionEvent event)
    {
        Record record=recordTable.getSelectionModel().getSelectedItem();
        if(record!=null)
        {
            Book book=bookService.getBookById(record.getBookId());
            boolean result = AlertUtil.confirm("Are you sure want to return this book?");
            if(result)
            {
                bookService.returnBook(book);
                recordService.addRecord(book,UserUtil.currentUser,"return");
                AlertUtil.info("return success");
                onRefresh(event);
            }
        }else
        {
            AlertUtil.erro("please choose a book to return");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        onRefresh(null);
    }
    @FXML
    void onRefresh(ActionEvent event)
    {
        List<Record> recordList=recordService.getRentingListByUserName(UserUtil.currentUser.getUserName());
        ObservableList<Record> fxRecordList= FXCollections.observableList(recordList);
        recordTable.setItems(fxRecordList);
    }

}
