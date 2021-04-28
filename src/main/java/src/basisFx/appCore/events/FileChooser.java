package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.utils.Extensions;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;

import java.io.File;
import java.util.List;

public class FileChooser extends AppEvent{
protected Node  node;
protected Stage stage;
protected Extensions extensions;
private javafx.stage.FileChooser fileDialog=new javafx.stage.FileChooser();
@Getter
private List<File> files;


    public FileChooser(Extensions extensions) {
        fileDialog.setTitle("Выберете один или несколько файлов");
        fileDialog.getExtensionFilters().add(extensions.get());
    }

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
                    files = fileDialog.showOpenMultipleDialog(stage);
                    mediator.inform(node);
                    if (nodeWrapper != null)   mediator.inform(nodeWrapper);
                }
            });
        }















    }








}