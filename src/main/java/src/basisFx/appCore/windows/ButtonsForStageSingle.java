package basisFx.appCore.windows;

import basisFx.appCore.elements.AnchorWrapper;
import basisFx.appCore.elements.ButtonWrapper;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;

public class ButtonsForStageSingle extends ButtonsForStage {

    private String closeStr="";
    private double fontHeight= 20d;
    private double topMatgin=4d;
    private double height=30d;
    private double width=30d;
    private Insets padding=new Insets(0d, 0d, 0d, 0d);
    private FontsStore fs=FontsStore.FAWESOME5SOLID;

    public ButtonsForStageSingle(String parentAnchorName) {
        super(parentAnchorName);
    }

    @Override
    protected void customInit() {

        buttonsAnchor =
                AnchorWrapper.newBuilder()
                        .setCSSid(CSSid.WindowButtonsPanel)
                        .setCoordinate( new Coordinate(0d,5d,null,null))
                        .setHeight(25d).setWidth(82d)
                        .setParentAnchor(parentAnchor)
                        .setMetaName("buttonsAnchor")
                        .build().getElement();


        //крестик
        closingButton=
                ButtonWrapper.newBuilder()
                        .setFont(fs)
                        .setFontSize(fontHeight)
                        .setWidth(width)
                        .setHeight(height)
                        .setInsects(padding)
                        .setCoordinate(new Coordinate(topMatgin, 0d, null, null))
                        .setCSSid(CSSid.TOP_CONTROL_SINGLE_BUTTON)
                        .setParentAnchor(buttonsAnchor)
                        .setStage(windowAbstraction.getStage())
                        .setEvents(new ClosePopupAndSubWindow())
                        .setText(closeStr)
                        .setMetaName("closingButton")
                        .setContentDisplay(ContentDisplay.CENTER)
                        .build();
    }
}
