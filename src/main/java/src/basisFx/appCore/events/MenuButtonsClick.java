package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.menu.LeftAndTopBarItemLeaf;
import basisFx.appCore.menu.LeftAndTopBarRepresetation;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuButtonsClick extends AppEvent{

    protected LeftAndTopBarItemLeaf LeftAndTopBarItemLeaf;
    protected LeftAndTopBarRepresetation represent;

    protected  Button  but;

    public MenuButtonsClick(LeftAndTopBarItemLeaf component, LeftAndTopBarRepresetation represent ) {
        this.LeftAndTopBarItemLeaf = component;
        this.represent=represent;

    }

    @Override
    public void setEventToElement(AppNode node) {
        this.but=(Button) node.getElement();

        but.setOnMouseClicked((event) -> {

            represent.setDefaultStyleHorisontalButtons();
            but.getStyleClass().add(CSSclasses.LEFT_SIDE_MENU_HORIZONTAL_BUTTONS_CLICKED.get());

            run();
        });

    }

    @Override
    public void setEventToElement(Node node) {

    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        clearContent();
        LeftAndTopBarItemLeaf.getPanelCreator().create().initTemplateMethod(Registry.mainWindow);


    }

    private void clearContent() {
        AnchorPane mainContentAnchor =
                (AnchorPane)  Registry.mainWindow.getNodeFromMap(WindowAbstraction.DefaultPanelsNames.mainContentAnchor.name());
        mainContentAnchor.getChildren().clear();
        Registry.mainWindow.closeDynamicContentPanel();
    }

}
