/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.appCore;

import basisFx.appCore.elements.AnchorWrapper;
import basisFx.appCore.settings.CSSid;

import static basisFx.appCore.settings.Settings.PRELODER_COUNT_LIMIT;

import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.IconToPlatform;
import basisFx.appCore.utils.PathToFileUtils;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class AppPreloader extends Preloader {


    private Scene scene;

//    private Label progress;
    private AnchorPane root;
    private Stage stage;
    private AnchorPane visibleRoot;

    public double WIDTH_VISIBLE=600d;
    public double HEIGHT_VISIBLE= 400d ;

    public double WIDTH_TRANSPARENT= WIDTH_VISIBLE+10d;
    public double HEIGHT_TRANSPARENT= HEIGHT_VISIBLE+10d;

    @Override
    public void init() throws Exception {
      
        Platform.runLater(() -> {

        setTransparentRoot();
        setVisibleRoot();


        this.scene= new Scene(root,WIDTH_TRANSPARENT,HEIGHT_TRANSPARENT);


        scene.setFill( Color.TRANSPARENT);

//                    InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("css/preloader.css");
//                    scene.getStylesheets().add(PathToFileUtils.getFilePath("/src/res/res/css/preloader.css" ));
                    scene.getStylesheets().add(PathToFileUtils.getResourseExternalForm("/css/preloader.css"));
            }

        );



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage=primaryStage;

        IconToPlatform.init(stage);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
  
          }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {

//        if (info instanceof ProgressNotification) {
//            progress.setText(((ProgressNotification) info).getProgress() + "%");
//        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
  
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                stage.hide();
                break;
        }
    }
    
    
        
    public static void coundown(EntryPoint ep)  {
   
        for (int i = 0; i <PRELODER_COUNT_LIMIT; i++) {
            double progress = (100 * i) / PRELODER_COUNT_LIMIT;
            
            LauncherImpl.notifyPreloader(
                    ep, new Preloader.ProgressNotification(progress)
            );
        }
    }
    
     protected void setTransparentRoot(){
                 this.root=AnchorWrapper.newBuilder()
                         .setCSSid(CSSid.PRELOADER_TRANSPARENT_ROOT)
                         .setInsects(new Insets(5d, 5d, 5d, 5d))
                         .build().getElement();
              
             
     }
     
     protected void setVisibleRoot(){
                 this.visibleRoot=AnchorWrapper.newBuilder()
                         .setParentAnchor(root)
                         .setCSSid(CSSid.PRELOADER_VISIBLE_ROOT)
                         .setCoordinate( new Coordinate(0d, 0d, 0d, 0d))
                         .build().getElement();
                 
                 
                
                 
                 
                
     }

}
