package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.utils.SpringContextUtil;
import com.finalproj5003.libarysystem.view.*;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

@FXMLController
public class UserPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    void onBookList(ActionEvent event)
    {
        final AbstractFxmlView view = SpringContextUtil.getBean(BookListView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());

        Button rentButton=(Button) view.getView().lookup("#btnRent");
        rentButton.setVisible(true);
    }

    @FXML
    void onReturn(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(ReturnPageView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());
    }

    @FXML
    void onHistory(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(UserRecordListView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());
    }

    @FXML
    void onChangePassword(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(PasswordChangeView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());
    }

    @FXML
    void onLogout(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(LoginView.class);
        GUIState.getScene().setRoot(view.getView());
        GUIState.getStage().show();
    }
}
