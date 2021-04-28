package basisFx.appCore.windows;

import basisFx.appCore.elements.AnchorWrapper;
import basisFx.appCore.elements.ButtonWrapper;
import basisFx.appCore.events.CloseMainWindow;
import basisFx.appCore.events.HideWindow;
import basisFx.appCore.events.MaximazingSwither;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;

public class    ButtonsForStageThreeEntity extends ButtonsForStage {

    private String closeStr="";
    private String hideStr="";
    private String maximazeStr="";
    private double fontHeight= 17d;
    private double topMatgin=4d;
    private double height=25d;
    private double width=25d;
    private Insets padding=new Insets(0d, 0d, 0d, 0d);
    private FontsStore fs=FontsStore.FAWESOME5SOLID;

    public ButtonsForStageThreeEntity(String parentAnchorName) {
        super(parentAnchorName);
    }

    @Override
    protected void customInit() {

        buttonsAnchor =
                AnchorWrapper.newBuilder()
                        .setCSSid(CSSid.WindowButtonsPanel)
                        .setWindowAbstraction(windowAbstraction)
                        .setCoordinate( new Coordinate(0d,0d,null,null))
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
                        .setCSSid(CSSid.TOP_CONTROL_BUTTON)
                        .setParentAnchor(buttonsAnchor)
                        .setStage(windowAbstraction.getStage())
                        .setEvents(new CloseMainWindow())
                        .setText(closeStr)
                        .setContentDisplay(ContentDisplay.CENTER)
                        .build();

        hideButton =
                ButtonWrapper.newBuilder()
                        .setFont(fs)
                        .setFontSize(fontHeight)
                        .setEvents(new HideWindow())
                        .setWidth(width)
                        .setHeight(height)
                        .setInsects(padding)
                        .setCoordinate(new Coordinate(topMatgin, width + width, null, null))
                        .setCSSid(CSSid.TOP_CONTROL_BUTTON)
                        .setParentAnchor(buttonsAnchor)
                        .setStage(windowAbstraction.getStage())
                        .setText(hideStr)
                        .setContentDisplay(ContentDisplay.CENTER)
                        .build();

        maximazeButton =
                ButtonWrapper.newBuilder()
                        .setFont(fs)
                        .setFontSize(fontHeight)
                        .setEvents(new MaximazingSwither())
                        .setWidth(width)
                        .setHeight(height)
                        .setInsects(padding)
                        .setCoordinate(new Coordinate(topMatgin, width, null, null))
                        .setCSSid(CSSid.TOP_CONTROL_BUTTON)
                        .setParentAnchor(buttonsAnchor)
                        .setStage(windowAbstraction.getStage())
                        .setText(maximazeStr)
                        .setContentDisplay(ContentDisplay.CENTER)
                        .build();

    }

}
