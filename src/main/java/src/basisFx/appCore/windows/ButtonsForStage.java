package basisFx.appCore.windows;
import basisFx.appCore.elements.ButtonWrapper;
import javafx.scene.layout.AnchorPane;

public abstract class ButtonsForStage {

    protected ButtonWrapper hideButton;
    protected ButtonWrapper  maximazeButton;
    protected ButtonWrapper  closingButton;
    protected AnchorPane buttonsAnchor;
    protected String parentAnchorName;
    protected AnchorPane parentAnchor;
    protected WindowAbstraction windowAbstraction;

    public ButtonsForStage(String parentAnchorName) {
        this.parentAnchorName = parentAnchorName;
    }

    protected abstract void customInit();
    public  void initTemplateMethod(WindowAbstraction windowAbstraction){
        this.windowAbstraction=windowAbstraction;
        parentAnchor= ((AnchorPane) windowAbstraction.getNodeFromMap(parentAnchorName));
        customInit();
    }
}
