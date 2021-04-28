package basisFx.appCore.windows;

import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.PanelCreator;
import basisFx.appCore.guiStructura.GUIStructura;
import lombok.Setter;
import lombok.experimental.Accessors;

public class WindowBuilder {
    public GUIStructura GUIStructura=null;
    public PanelCreator panelCreator =null;
    public Double width=null;
    public Double height=null;
    public String fxmlFileName=null;
    @Setter public String title=null;
    public String parentAnchorNameForFXML=null;
    public ButtonsForStage buttonsForStage=null;
    public String message;
    public CallBack preClosingCallBack;
    public CallBackParametrized callBackParametrized;

    private WindowBuilder(Builder builder) {
        GUIStructura = builder.GUIStructura;
        panelCreator = builder.panelCreator;
        width = builder.width;
        height = builder.height;
        fxmlFileName = builder.fxmlFileName;
        title = builder.title;
        parentAnchorNameForFXML = builder.parentAnchorNameForFXML;
        buttonsForStage = builder.buttonsForStage;
        message = builder.message;
        preClosingCallBack = builder.preClosingCallBack;
        callBackParametrized = builder.callBackParametrized;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Accessors (chain = true) @Setter
    public static final class Builder {
        private basisFx.appCore.guiStructura.GUIStructura GUIStructura;
        private PanelCreator panelCreator;
        private Double width;
        private Double height;
        private String fxmlFileName;
        private String title;
        private String parentAnchorNameForFXML;
        private ButtonsForStage buttonsForStage;
        private String message;
        private CallBack preClosingCallBack;
        private CallBackParametrized callBackParametrized;

        private Builder() {
        }

        public WindowBuilder build() {
            return new WindowBuilder(this);
        }
    }
}
