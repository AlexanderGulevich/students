package basisFx.appCore.windows;

import basisFx.appCore.utils.IconToPlatform;
import basisFx.appCore.elements.AnchorWrapper;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.DynamicContentPanel;
import basisFx.service.WindowService;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public    abstract  class  WindowAbstraction<T extends  Object> {

    @Getter protected Stage stage;
    @Getter protected Scene scene;
    @Getter @Setter protected DynamicContentPanel currentDynamicContent;
    @Getter protected AnchorPane root;
    @Getter protected AnchorPane topVisibleAnchor;
    @Getter protected WindowImpl windowImpl;
    public  enum DefaultPanelsNames {rootTransparent, topVisibleAnchor, mainContentAnchor}
    protected HashMap<String, T> nodMap =new HashMap<>();

    public WindowAbstraction(WindowImpl windowImpl ) {
        this.stage =new Stage();
        IconToPlatform.init(stage);
        this.windowImpl = windowImpl;
        initRoot();
        initTopVisiblePanel();
        createScene();
        windowImpl.initTemplateMethod(this);
    }
    public WindowAbstraction(Stage stage, WindowImpl windowImpl) {
        this.stage = stage;
        IconToPlatform.init(stage);
        this.windowImpl = windowImpl;
        initRoot();
        initTopVisiblePanel();
        createScene();
        windowImpl.initTemplateMethod(this);

    }
    public void closeDynamicContentPanel() {
        if (currentDynamicContent != null) {
            currentDynamicContent.closeDynamicContentPanel();
        }
        currentDynamicContent = null;
    }
    protected abstract void createScene();
    public static boolean isWindowNotExist(WindowBuilder builder){

        HashMap<String, WindowService> crossWindowMediators = Registry.crossWindowMediators;
        Object obj = Registry.crossWindowMediators.get(builder.fxmlFileName);
        if (Registry.crossWindowMediators.get(builder.fxmlFileName) != null) {
            return false;
        }
        return true;
    }

    public void setNodeToMap(T  node, String name) {
        nodMap.put(name,node);
    }
    public T getNodeFromMap(String str){
        return nodMap.get(str);
    }

    protected abstract void initRoot();
    private void initTopVisiblePanel() {
        AnchorWrapper anchorWrapper = AnchorWrapper.newBuilder()
                .setParentAnchor(root)
                .setCoordinate(new Coordinate(0d, 0d, 0d, 0d))
                .setCSSid(CSSid.TopVisiblePanel)
                .setMetaName(DefaultPanelsNames.topVisibleAnchor.name())
                .build();
        topVisibleAnchor =anchorWrapper.getElement();

        setNodeToMap((T) topVisibleAnchor,anchorWrapper.getMetaName());
    }
}
