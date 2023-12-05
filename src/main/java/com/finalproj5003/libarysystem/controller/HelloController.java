package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.User;
import com.finalproj5003.libarysystem.service.UserService;
import com.finalproj5003.libarysystem.utils.SpringContextUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@FXMLController
public class HelloController implements Initializable {
    @FXML
    private TextField idTextField;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<User, String> idCol;
    @FXML
    private TableColumn<User, String> userNameCol;

    @FXML
    private TableColumn<User, String> passwordCol;

    @FXML
    private TableView<User> tableView;
    @FXML
    void onAdd(ActionEvent event) {

    }

    @FXML
    void onDelete(ActionEvent event) {

    }

    @Autowired
    UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        List<User> userList=userService.findAll();
        System.out.println(userList);
        ObservableList<User> observableList=FXCollections.observableList(userList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableView.setItems(observableList);
    }
}