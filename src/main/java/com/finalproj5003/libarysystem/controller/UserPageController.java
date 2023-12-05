package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.utils.SpringContextUtil;
import com.finalproj5003.libarysystem.view.BookListView;
import com.finalproj5003.libarysystem.view.PasswordChangeView;
import com.finalproj5003.libarysystem.view.UserRecordListView;
import com.finalproj5003.libarysystem.view.ReturnPageView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
}
