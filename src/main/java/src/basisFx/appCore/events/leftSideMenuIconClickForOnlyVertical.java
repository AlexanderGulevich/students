package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.interfaces.PanelCreator;
import basisFx.appCore.menu.LeftAndTopBarItemComposite;
import basisFx.appCore.menu.LeftAndTopBarRepresetation;
import basisFx.appCore.menu.LeftRepresetation;
import basisFx.appCore.menu.MenuComponent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class leftSideMenuIconClickForOnlyVertical extends AppEvent{
    protected LeftAndTopBarItemComposite component;
    protected LeftRepresetation represent;
    protected Button  but;


    public leftSideMenuIconClickForOnlyVertical(MenuComponent component, LeftRepresetation represent) {
        this.represent=represent;
        this.component = (LeftAndTopBarItemComposite) component;
    }

    public leftSideMenuIconClickForOnlyVertical(MenuComponent component, LeftRepresetation represent, Button button) {
        this.represent=represent;
        this.component = (LeftAndTopBarItemComposite) component;
        setEventToElement(button);
    }

    @Override
    public void setEventToElement(AppNode n) {
        but=(Button) n.getElement();

        but.setOnAction((event) -> {
            run();
        });


    }

    @Override
    public void setEventToElement(Node node) {
        but= ((Button) node);

        but.setOnAction((event) -> {
            run();
        });

    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        represent.setDefaultStyleVerticalButtons();
        represent.setCommonTextName(component);
        but.getStyleClass().clear();
        but.getStyleClass().add("only_vertical_menu_BUTTONS_CLICKED");
        if (callBack != null) callBack.call();
        clearContent();
        PanelCreator panelCreator = component.getPanelCreator();
        if (panelCreator != null) {
            panelCreator.create().initTemplateMethod(Registry.mainWindow);
        }

             }



    private void clearContent() {
        AnchorPane mainContentAnchor =
                (AnchorPane)  Registry.mainWindow.getNodeFromMap(WindowAbstraction.DefaultPanelsNames.mainContentAnchor.name());
        mainContentAnchor.getChildren().clear();
        Registry.mainWindow.closeDynamicContentPanel();
    }











}







