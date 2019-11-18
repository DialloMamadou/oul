package controlleurvue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage loginStage,mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginStage=primaryStage;
        ouvrirloginwindow();
    }
    public  void openMainWindow() {
        try {

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vues/main.fxml"));
            AnchorPane pane=loader.load();
            Scene scene=new Scene(pane);
            mainStage=new Stage();
            scene.getStylesheets().add(getClass().getResource("/sample/style/styleSheet.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void ouvrirloginwindow() {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vues/login.fxml"));

            AnchorPane anchorPane=loader.load();
            Scene scene=new Scene(anchorPane);
            scene.getStylesheets().add(getClass().getResource("/style/styleSheet.css").toExternalForm());

            loginStage.setScene(scene);
            loginStage.setTitle("login");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
