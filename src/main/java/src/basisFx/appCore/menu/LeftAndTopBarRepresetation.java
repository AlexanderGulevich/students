package basisFx.appCore.menu;

import basisFx.appCore.elements.ButtonWrapper;
import basisFx.appCore.events.MenuButtonsClick;
import basisFx.appCore.events.leftSideMenuIconClick;
import basisFx.appCore.settings.CSSclasses;
import java.util.ArrayList;
import java.util.Iterator;

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

public class LeftAndTopBarRepresetation extends MenuRepresent {
    protected Label label =
            ((Label) Registry.mainWindow.getNodeFromMap("commonMenuLabel"));
    protected HBox horisontalMenuButHolder =
            ((HBox) Registry.mainWindow.getNodeFromMap("horisontalMenuButHolder"));
    protected VBox vButHolder =
            (VBox) Registry.mainWindow.getNodeFromMap("vButHolder")  ;
    protected AnchorPane contentAnchorPane =
            (AnchorPane) Registry.mainWindow.getNodeFromMap("mainContentAnchor")  ;
    private ArrayList<ButtonWrapper> buttonWrappers=
            new ArrayList<>();
    private leftSideMenuIconClick startButEvent;

    public LeftAndTopBarRepresetation(MenuSketch sketch) {
        vButHolder.getChildren().clear();
        horisontalMenuButHolder.getChildren().clear();

        label.setText("");
        makeStructuredMenuView(sketch.getMenuTree(), null);
        setDefaultStyleVerticalButtons();
        setDefaultStyleHorisontalButtons();
        fireStartButton();
    }

    private void fireStartButton() {
        startButEvent.run();
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
            if (c.isComposit())
                createVerticalBut((LeftAndTopBarItemComposite) c);
        }
            );
    }
    private void createVerticalBut(LeftAndTopBarItemComposite c) {
        Button button = FXMLLoader.loadButton(c.getFxmlFileName());
        button.setText(c.getFontSymbol());
        button.setFont(FontLogic.loadFont(c.getFontsStore(),c.getFontSize()));
        vButHolder.getChildren().add(button);
        leftSideMenuIconClick clickEvent = new leftSideMenuIconClick(c, this,button);
        clickEvent.setCallBack(c.getCallBack());
        if (c.isActive())  startButEvent=clickEvent;

    }
    public void setDefaultStyleHorisontalButtons() {
        ObservableList<Node> buttons = horisontalMenuButHolder.getChildren();
        for (Iterator<Node> iterator = buttons.iterator(); iterator.hasNext(); ) {
            Node next = iterator.next();
            if (next instanceof Button) {
                next.getStyleClass().clear();
                next.getStyleClass().add(CSSclasses.LEFT_SIDE_MENU_HORIZONTAL_BUTTONS.name());
            }
        }
    }
    public void setDefaultStyleVerticalButtons() {
        ObservableList<Node> buttons = vButHolder.getChildren();
        for (Iterator<Node> iterator = buttons.iterator(); iterator.hasNext(); ) {
            Node next = iterator.next();
            if (next instanceof Button) {
                next.getStyleClass().clear();
                next.getStyleClass().add(CSSclasses.LEFT_SIDE_MENU_VERTICAL_BUTTONS.name());
            }
        }
    }
    public void setCommonTextName(LeftAndTopBarItemComposite itemComposite) {
        label.setText(itemComposite.getDescription());
    }
    public void setHorisontalButtons(LeftAndTopBarItemComposite itemComposite) {
        horisontalMenuButHolder.getChildren().clear();
        if (itemComposite.isComposit()) {
            contentAnchorPane.getChildren().clear();
            ArrayList<MenuComponent> inerLevelComponents = itemComposite.getChilds();

            inerLevelComponents.forEach( c ->
            //создание кнопок горизонтальных
                    ButtonWrapper.newBuilder()
                            .setCssClasses(CSSclasses.LEFT_SIDE_MENU_HORIZONTAL_BUTTONS)
                            .setText(((LeftAndTopBarItemLeaf) c).getName())
                            .setFont(FontsStore.ROBOTO_LIGHT)
                            .setEvents(new MenuButtonsClick(((LeftAndTopBarItemLeaf) c),this))
                            .setParentHBox(horisontalMenuButHolder)
                            .build()
                    );

        }
        ObservableList<Node> children = horisontalMenuButHolder.getChildren();
        if (!children.isEmpty()) {
            Node node = children.get(0);
            if (node != null) {
                node.getStyleClass().add(CSSclasses.LEFT_SIDE_MENU_VERTICAL_BUTTONS_FIRST.name());
            }

        }

    }
}




