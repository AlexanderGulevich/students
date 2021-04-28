package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.utils.DirectoryChooserWrapper;
import basisFx.appCore.utils.Extensions;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.util.List;

public class DirectoryChosserEvent extends AppEvent{
protected Node  node;
protected Stage stage;
protected Extensions extensions;
@Getter
private List<File> files;
@Getter
private String path=null;



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
        this.node= node;
        this.stage=stage;
        run();
    }

    @Override
    public void run() {

        if (node != null) {
            node.setOnMousePressed(event -> {

                if (booleanCalBack()) {
                    DirectoryChooserWrapper directoryChooserWrapper = new DirectoryChooserWrapper();
                    path = directoryChooserWrapper.getPath();
                    mediator.inform(node);
                    if (nodeWrapper != null) mediator.inform(nodeWrapper);
                }
            });
        }

    }



}