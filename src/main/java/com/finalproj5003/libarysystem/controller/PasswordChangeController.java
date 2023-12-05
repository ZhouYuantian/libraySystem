package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.service.UserService;
import com.finalproj5003.libarysystem.utils.AlertUtil;
import com.finalproj5003.libarysystem.utils.UserUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class PasswordChangeController {
    @Autowired
    UserService userService;
    @FXML
    private PasswordField tfConfim;

    @FXML
    private PasswordField tfPassword;

    @FXML
    void onConfirm(ActionEvent event)
    {
        String password=tfPassword.getText();
        String confirm=tfConfim.getText();

        if(password.isEmpty()){
            AlertUtil.erro("invalid password");
        }else if(!password.equals(confirm))
        {
            AlertUtil.erro("password not match");
        }else
        {
            UserUtil.currentUser.setPassword(password);
            userService.updateUserById(UserUtil.currentUser);
            AlertUtil.info("success");
        }
    }
}
