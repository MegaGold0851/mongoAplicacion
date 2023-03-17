package com.megagold.mongoennube_vuno;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @FXML
    private static Tab tabScp;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 620);
        stage.setTitle("SCP En la nube");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("InsertarSCP.fxml"));
        AnchorPane content1 = null;
        try {
            content1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((Tab) fxmlLoader.getNamespace().get("tabScp")).setContent(content1);
    }

    public static void main(String[] args) {
        launch();
    }
}