package basisFx.appCore.windows;

import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.PanelCreator;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FXMLLoader;
import basisFx.appCore.guiStructura.GUIStructura;
import basisFx.appCore.utils.Registry;
import basisFx.service.WindowService;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public abstract class WindowImpl {
    @Getter @Setter protected WindowAbstraction windowAbstraction;
    @Getter protected PanelCreator panelCreator;
    @Getter protected CallBack callBack;
    @Getter protected CallBackParametrized callBackParametrized;
    @Getter protected ButtonsForStage buttonsForStage;
    @Getter protected GUIStructura GUIStructura;
    @Getter  protected String fxmlFileName;
    @Getter  protected WindowService windowService;
    @Getter protected Double width;
    @Getter protected Double height;
    @Getter protected String titleName;
    @Getter protected String message;
    protected AnchorPane topLevelAnchorFromFXML;
    protected String parentAnchorNameForFXML;
    protected WindowBuilder builder;

    protected abstract void setDefaultWidthAndHeight();
    public abstract void customInit(WindowAbstraction windowAbstraction);
    public abstract void addToRegistry(WindowAbstraction windowAbstraction);

    public WindowImpl(WindowBuilder builder) {
        width=builder.width;
        height=builder.height;
        message=builder.message;
        titleName=builder.title;
        GUIStructura =builder.GUIStructura;
        fxmlFileName =builder.fxmlFileName;
        buttonsForStage=builder.buttonsForStage;
        parentAnchorNameForFXML=builder.parentAnchorNameForFXML;
        callBack =builder.preClosingCallBack;
        panelCreator =builder.panelCreator;
        callBackParametrized=builder.callBackParametrized;
        this.builder=builder;
    }

    public  void initTemplateMethod(WindowAbstraction windowAbstraction){
        addToRegistry(windowAbstraction);
        toInitSize();
        toCreateDynamicContent(windowAbstraction);
        toHandleFXML(windowAbstraction);
        toInitButtonsForStage(windowAbstraction);
        toInitAddedStructura(windowAbstraction);
        customInit(windowAbstraction);
    }

    private void toInitSize() {
        if (height == null || width == null)  setDefaultWidthAndHeight();
    }

    private void toInitAddedStructura(WindowAbstraction windowAbstraction) {
        if (GUIStructura != null)  GUIStructura.init(windowAbstraction);
    }
    private void toInitButtonsForStage(WindowAbstraction windowAbstraction) {
        if (buttonsForStage != null)   buttonsForStage.initTemplateMethod(windowAbstraction);
    }
    private void toCreateDynamicContent(WindowAbstraction windowAbstraction) {
        if (panelCreator != null) panelCreator.create().initTemplateMethod(windowAbstraction);
    }
    private void toHandleFXML(WindowAbstraction windowAbstraction) {
        if (builder.fxmlFileName != null){
            loadAnchorPaneFromFXML();
            toBindWithWindow(windowAbstraction);
            initFXMLService(windowAbstraction);
        }
    }
    private void loadAnchorPaneFromFXML() {
        topLevelAnchorFromFXML = FXMLLoader.loadAnchorPane(builder.fxmlFileName);
    }

    private void initFXMLService(WindowAbstraction windowAbstraction) {
        HashMap<String, WindowService> crossWindowMediators = Registry.crossWindowMediators;
        windowService = Registry.crossWindowMediators.get(builder.fxmlFileName);
        windowService.setCurrentWindow(windowAbstraction);
        windowService.init();
        windowService.setCallBackParametrized(callBackParametrized);
        windowService.setPreClosingCallBack(callBack);
    }

    private void toBindWithWindow(WindowAbstraction windowAbstraction) {
        AnchorPane parentAnchorHolder = (AnchorPane) windowAbstraction.getNodeFromMap(parentAnchorNameForFXML);
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setChildNode(topLevelAnchorFromFXML);
        coordinate.setParentAnchorPane(parentAnchorHolder);
        coordinate.bonding();
    }


}
