package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.entity.Record;
import com.finalproj5003.libarysystem.service.RecordService;
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
public class RecordListController implements Initializable {

    @Autowired
    RecordService recordService;

    @FXML
    private TextField tfKeyword;

    @FXML
    private TableColumn<Record, Integer> userIdCol;

    @FXML
    private TableColumn<Record, String > dateCol;

    @FXML
    private TableColumn<Record, String > bookNameCol;

    @FXML
    private TableColumn<Record, String> actionCol;

    @FXML
    private TableColumn<Record, String> userNameCol;

    @FXML
    private TableColumn<Record, Integer> bookIdCol;

    @FXML
    private TableView<Record> recordTable;

    @FXML
    void onSearch(ActionEvent event) {
        String keyword=tfKeyword.getText();
        List<Record> recordList=recordService.findAll(keyword);
        ObservableList<Record> fxRecordList= FXCollections.observableList(recordList);
        recordTable.setItems(fxRecordList);
    }

    @FXML
    void onRefresh(ActionEvent event)
    {
        List<Record> recordList=recordService.findAll();
        ObservableList<Record> fxRecordList= FXCollections.observableList(recordList);
        recordTable.setItems(fxRecordList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        List<Record> recordList=recordService.findAll();
        ObservableList<Record> fxRecordList= FXCollections.observableList(recordList);
        recordTable.setItems(fxRecordList);
    }
}
