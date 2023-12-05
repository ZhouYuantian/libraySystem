package com.finalproj5003.libarysystem;

import com.finalproj5003.libarysystem.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LibarySystemApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(LibarySystemApplication.class, LoginView.class,args);
    }

}
