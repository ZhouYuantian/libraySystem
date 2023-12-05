package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.User;
import com.finalproj5003.libarysystem.service.UserService;
import com.finalproj5003.libarysystem.utils.AlertUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class UserListController implements Initializable {

    @Autowired
    UserService userService;

    @FXML
    private TextField tfUserName;

    @FXML
    private TableColumn<User, String> userNameCol;

    @FXML
    private TableColumn<User, String> passwordCol;

    @FXML
    private TableView<User> userTable;


    @FXML
    void onSearch(ActionEvent event)
    {
        String userName = tfUserName.getText();
        List<User> userList = userService.findAll(userName);
        ObservableList<User> fxUserList = FXCollections.observableArrayList(userList);
        userTable.setItems(fxUserList);
    }

    @FXML
    void onDelete(ActionEvent event)
    {
        User user=userTable.getSelectionModel().getSelectedItem();
        if(user!=null) {
            boolean result = AlertUtil.confirm("Are you sure want to delete this user?");
            if (result) {
                userService.deleteUser(user.getUserName());
                onSearch(event);
                AlertUtil.info("delete successful");
            }
        }
        else
        {
            AlertUtil.erro("please choose a user");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        List<User> userList = userService.findAll();
        ObservableList<User> fxUserList = FXCollections.observableArrayList(userList);
        userTable.setItems(fxUserList);
    }
}
