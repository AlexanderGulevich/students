package basisFx.appCore.utils;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class FXMLLoader {
    public static AnchorPane loadAnchorPane(String fxmlName){

        Parent parent = getParent(fxmlName);
//        if (parent == null) throw new NullPointerException();
        return (AnchorPane) parent;
    }
    public static Button loadButton(String fxmlName){

        Parent parent = getParent(fxmlName);
        return (Button) parent;
    }
    public static JFXCheckBox loadCheckBox(String fxmlName){

        Parent parent = getParent(fxmlName);
        return (JFXCheckBox) parent;
    }


    private static Parent getParent(String fxmlName) {
        if (!fxmlName.contains(".fxml")) fxmlName+=".fxml";

        URL url=PathToFileUtils.getUrl("/fxml/" + fxmlName);
        Parent parent=null;

        try {
            parent = javafx.fxml.FXMLLoader.load(url);

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
            e.printStackTrace();
        }  catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
        return parent;
    }
}
