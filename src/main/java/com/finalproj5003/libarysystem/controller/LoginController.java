package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.User;
import com.finalproj5003.libarysystem.service.AdminService;
import com.finalproj5003.libarysystem.service.UserService;
import com.finalproj5003.libarysystem.utils.AlertUtil;
import com.finalproj5003.libarysystem.utils.SpringContextUtil;
import com.finalproj5003.libarysystem.utils.UserUtil;
import com.finalproj5003.libarysystem.view.AdminPageView;
import com.finalproj5003.libarysystem.view.UserPageView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;



@FXMLController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;
    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUserName;

    @FXML
    void adminLogin(ActionEvent event){
        String username=tfUserName.getText();
        String password=tfPassword.getText();

        if(adminService.checkPassword(username,password))
        {
            final AbstractFxmlView view = SpringContextUtil.getBean(AdminPageView.class);
            GUIState.getScene().setRoot(view.getView());
            GUIState.getStage().show();
        }
        else
        {
            AlertUtil.warning("username or password not match");
        }
    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        String username=tfUserName.getText();
        String password=tfPassword.getText();

        if(userService.checkPassword(username,password))
        {
            UserUtil.loginUser(username);
            final AbstractFxmlView view = SpringContextUtil.getBean(UserPageView.class);
            GUIState.getScene().setRoot(view.getView());
            GUIState.getStage().show();
        }
        else
        {
            AlertUtil.warning("username or password not match");
        }
    }

    @FXML
    void userRegister(ActionEvent event)
    {
        String username=tfUserName.getText();
        String password=tfPassword.getText();

        if(username.isEmpty()){AlertUtil.erro("username invalid");}
        else if(password.isEmpty()){AlertUtil.erro("password invalid");}
        else
        {
            if(!userService.checkExistence(username))
            {
                User user=new User(username,password);
                userService.addUser(user);
                AlertUtil.info("register success");
            }
            else
            {
                AlertUtil.warning("fail: username already exist");
            }
        }

    }
}
