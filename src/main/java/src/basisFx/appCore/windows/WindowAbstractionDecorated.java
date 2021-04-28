package basisFx.appCore.windows;

import basisFx.appCore.utils.CSSHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public  class WindowAbstractionDecorated extends WindowAbstraction {

    public WindowAbstractionDecorated(Stage st, WindowImpl implimentation) {
        super(implimentation);
        stage =st;
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(windowImpl.getTitleName());
        stage.show();
    }

    @Override
    protected void createScene() {
        scene= new Scene(root, this.windowImpl.getWidth(), this.windowImpl.getHeight());
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        CSSHandler.getInstanse().loadStylesToSceneFromJar(scene);
    }

    public WindowAbstractionDecorated(WindowImpl implimentation) {
        super(implimentation);
        stage =new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(windowImpl.getTitleName());
        stage.show();
    }

    @Override
    protected void initRoot() {
//        root= AnchorWrapper.newBuilder()
//                .setCSSid(CSSid.TRANSPARENT_ROOT)
//                .setInsects(new Insets(0d, 0d, 0d, 0d))
//                .build().getElement();
    }


}
