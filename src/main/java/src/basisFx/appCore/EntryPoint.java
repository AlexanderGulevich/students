package basisFx.appCore;

import basisFx.App;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;


public class EntryPoint extends Application  {
    
    

    public static void main(String[] args) {
        LauncherImpl.launchApplication(
                EntryPoint.class,
//                AppPreloader.class,
                args);
    }

     @Override
    public void init() throws Exception {
//        AppPreloader.coundown(this);

    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.err.println("System.getProperty(\"user.dir\")-----"+System.getProperty("user.dir").toUpperCase());
        System.err.println("File.separator-----"+ File.separator.toUpperCase());
        System.err.println("File.pathSeparator-----"+File.pathSeparator.toUpperCase());
        System.err.println("this.getClass().getResource(/).toExternalForm()-----"+this.getClass().getResource("").toExternalForm());
//        System.err.println("this.getClass().getResource(/).toExternalForm(/)-----"+this.getClass().getResource("/").toExternalForm());

        Application.setUserAgentStylesheet(
                this.getClass().getResource("/css/modena.css").toExternalForm());
//        Application.setUserAgentStylesheet(
//                this.getClass().getResource("/../../../res/css/modena/modena.css").toExternalForm());
        new App(primaryStage);

        

   }



}
