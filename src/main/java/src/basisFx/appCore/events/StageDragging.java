package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StageDragging extends AppEvent{
protected Node  node;
protected static double xOffset = 0;
protected static double yOffset = 0;
protected Stage stage;

    @Override
    public void setEventToElement(AppNode appNode) {
        this.nodeWrapper =appNode;
        this.node= appNode.getElement();
        stage=appNode.getStage();
        run();

}

    @Override
    public void setEventToElement(Node node) {
        this.node= node;


        run();
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {


         node.setOnMousePressed(event -> {

              if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {

                  Window window = node.getScene().getWindow();
                  stage= ((Stage) window);

                  if(!stage.isMaximized()){
                     xOffset = stage.getX() - event.getScreenX();
                     yOffset = stage.getY() - event.getScreenY();
                  }

             }



         });

         node.setOnMouseDragged(event -> {

             Window window = node.getScene().getWindow();
             stage= ((Stage) window);

              if(!stage.isMaximized()){
                     stage.setX(event.getScreenX() + xOffset);
                     stage.setY(event.getScreenY() + yOffset);
              }
         });


    }








}