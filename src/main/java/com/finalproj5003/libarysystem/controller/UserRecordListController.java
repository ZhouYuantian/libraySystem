package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.Record;
import com.finalproj5003.libarysystem.service.RecordService;
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
public class UserRecordListController implements Initializable {
    @Autowired
    RecordService recordService;
    @FXML
    private TableColumn<Record, String> dateCol;

    @FXML
    private TableColumn<Record, String> bookNameCol;

    @FXML
    private TableColumn<Record, String> actionCol;

    @FXML
    private TableColumn<Record, Integer> bookIdCol;

    @FXML
    private TableView<Record> recordTable;

    @FXML
    void onRefresh(ActionEvent event) {
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        List<Record> recordList=recordService.getRecordsByUserName(UserUtil.currentUser.getUserName());
        ObservableList<Record> fxRecordList= FXCollections.observableList(recordList);
        recordTable.setItems(fxRecordList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onRefresh(null);
    }
}
