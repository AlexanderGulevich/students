package basisFx.appCore.menu;

import basisFx.appCore.elements.ButtonWrapper;
import basisFx.appCore.events.MenuButtonsClick;
import basisFx.appCore.events.leftSideMenuIconClick;
import basisFx.appCore.events.leftSideMenuIconClickForOnlyVertical;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.FXMLLoader;
import basisFx.appCore.utils.FontLogic;
import basisFx.appCore.utils.Registry;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Iterator;

public class LeftRepresetation extends MenuRepresent {
    protected Label label =
            ((Label) Registry.mainWindow.getNodeFromMap("commonMenuLabel"));
    protected VBox vButHolder =
            (VBox) Registry.mainWindow.getNodeFromMap("vButHolder")  ;
    protected AnchorPane contentAnchorPane =
            (AnchorPane) Registry.mainWindow.getNodeFromMap("mainContentAnchor")  ;
    private ArrayList<ButtonWrapper> buttonWrappers=
            new ArrayList<>();
    private leftSideMenuIconClickForOnlyVertical startButEvent;

    public LeftRepresetation(MenuSketch sketch) {
        makeStructuredMenuView(sketch.getMenuTree(), null);
        setDefaultStyleVerticalButtons();
        fireStartButton();
    }

    private void fireStartButton() {
        if (startButEvent != null) {
            startButEvent.run();
        }

    }

    @Override
    protected <T> void makeStructuredMenuView(MenuComponent c, MenuComposite parentMenu) {
        ArrayList<MenuComponent> components = null;
        if (c.isComposit()) {
            components = ((MenuComposite) c).getChilds();
            seachVerticalBut(components);
        } else {

        }
    }
    private void seachVerticalBut(ArrayList<MenuComponent> components) {
        components.forEach(c ->   {
            if (c.isComposit())   createVerticalBut((LeftAndTopBarItemComposite) c); }
            );
    }
    private void createVerticalBut(LeftAndTopBarItemComposite c) {
        Button button = FXMLLoader.loadButton(c.getFxmlFileName());
        button.setText(c.getFontSymbol());
        button.setFont(FontLogic.loadFont(c.getFontsStore(),c.getFontSize()));
        vButHolder.getChildren().add(button);
        leftSideMenuIconClickForOnlyVertical clickEvent = new leftSideMenuIconClickForOnlyVertical(c, this,button);
        clickEvent.setCallBack(c.getCallBack());
        if (c.isActive())  startButEvent=clickEvent;

    }
    public void setDefaultStyleVerticalButtons() {
        ObservableList<Node> buttons = vButHolder.getChildren();
        for (Iterator<Node> iterator = buttons.iterator(); iterator.hasNext(); ) {
            Node next = iterator.next();
            if (next instanceof Button) {
                next.getStyleClass().clear();
                next.getStyleClass().add("only_vertical_menu_BUTTONS");
            }
        }
    }
    public void setCommonTextName(LeftAndTopBarItemComposite itemComposite) {
        label.setText(itemComposite.getDescription());
    }
}




