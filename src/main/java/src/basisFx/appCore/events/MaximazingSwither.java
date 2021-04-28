package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.settings.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MaximazingSwither extends AppEvent{
    protected Node  node;
    protected boolean  max=false;

    protected Stage stage;

    @Override
    public void setEventToElement(AppNode appNode) {
        this.nodeWrapper =appNode;
        this.node= appNode.getElement();
          stage = nodeWrapper.getStage();
        node.setOnMouseClicked( (event) -> {

            run();
        }

        ) ;


}

    @Override
    public void setEventToElement(Node node) {

        node.setOnMouseClicked( (event) -> {

                    run();
                }

        ) ;
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {
        this.stage=stage;
        node.setOnMouseClicked( (event) -> {

                    run();
                }

        ) ;
    }

    @Override
    public void run() {
        try {

            System.err.println("MaximazingSwither.run");
            Thread.sleep(100);


            AnchorPane root=(AnchorPane) stage.getScene().getRoot();

//            if (stage.isMaximized()) {
//                if (stage.isFullScreen()) {
            if (max) {

                  max=false;

                  root.setPadding(new Insets(3d, 3d, 3d, 3d));

                    stage.setMaximized(false);
                    stage.setWidth(Settings.WIDTH);
                    stage.setHeight(Settings.HEIGHT);

                    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX(
                            (primScreenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY(
                            (primScreenBounds.getHeight() - stage.getHeight()) / 2);


//                MaximazingManager.notifyObjects();



            } else {

                 max=true;

                 root.setPadding(new Insets(0d, 0d, 0d, 0d));

//                nodeWrapper.getStage().setIconified(true);
//                nodeWrapper.getStage().setMaximized(true);
//                nodeWrapper.getStage().setFullScreen(true);


                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());


//                MaximazingManager.notifyObjects();

            }


            } catch (InterruptedException ex) {
                Logger.getLogger(CloseMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }




}
