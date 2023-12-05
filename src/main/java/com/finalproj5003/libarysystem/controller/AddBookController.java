package com.finalproj5003.libarysystem.controller;

import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.service.BookService;
import com.finalproj5003.libarysystem.utils.AlertUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class AddBookController {
    @Autowired
    BookService bookService;
    @FXML
    private TextField tfEdition;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfBookName;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfStock;

    @FXML
    void onAddBook(ActionEvent event) {
        String edition=tfEdition.getText();
        String author=tfAuthor.getText();
        String description=tfDescription.getText();
        String stock=tfStock.getText();
        String bookName=tfBookName.getText();
        if(bookName.isEmpty())  {AlertUtil.erro("please provide the bookName");}
        else if(description.isEmpty())  {AlertUtil.erro("please provide the description");}
        else if(author.isEmpty()) { AlertUtil.erro("please provide the author");}
        else if(edition.isEmpty()) {AlertUtil.erro("please provide the edition");}
        else if(!stock.matches("[0-9]+"))  {AlertUtil.erro("stock invalid");}
        else
        {
            Book book=new Book(bookName,description,Integer.valueOf(stock),author,edition);
            bookService.addBook(book);
            AlertUtil.info("add success");
        }
    }
}
