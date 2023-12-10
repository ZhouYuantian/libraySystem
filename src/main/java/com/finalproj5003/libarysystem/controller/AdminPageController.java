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
public class AdminPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    void onAddBook(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(AddBookView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());
    }

    @FXML
    void onBookList(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(BookListView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());

        Button rentButton=(Button) view.getView().lookup("#btnRent");
        rentButton.setVisible(false);
    }

    @FXML
    void onUserList(ActionEvent event)
    {
        final AbstractFxmlView view = SpringContextUtil.getBean(UserListView.class);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view.getView());
    }

    @FXML
    void onViewHistory(ActionEvent event) {
        final AbstractFxmlView view = SpringContextUtil.getBean(RecordListView.class);
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
