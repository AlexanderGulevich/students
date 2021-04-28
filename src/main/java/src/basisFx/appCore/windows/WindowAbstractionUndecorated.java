package basisFx.appCore.windows;

import basisFx.appCore.settings.CSSid;
import basisFx.appCore.utils.CSSHandler;
import basisFx.appCore.elements.AnchorWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowAbstractionUndecorated extends WindowAbstraction {

    public WindowAbstractionUndecorated(Stage primaryStage, WindowImpl implimentation) {
        super(primaryStage,implimentation);
        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.stage.show();
    }

    public WindowAbstractionUndecorated(WindowImpl implimentation) {
        super(implimentation);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    protected void createScene() {

        scene= new Scene(
                root,
                this.windowImpl.getWidth()+ 10 ,
                this.windowImpl.getHeight()+ 10
        );
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        CSSHandler.getInstanse().loadStylesToSceneFromJar(scene);
    }


    @Override
    protected void initRoot() {
        root= AnchorWrapper.newBuilder()
                .setCSSid(CSSid.TRANSPARENT_ROOT)
                .setInsects(new Insets(5,5,5,5))
                .setMetaName("Root")
                .build()
                .getElement();
    }


}

    