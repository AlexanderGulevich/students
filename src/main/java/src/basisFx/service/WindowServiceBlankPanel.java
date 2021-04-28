package basisFx.service;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.RowAddToTable;
import basisFx.appCore.events.RowDeleteFromTable;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public  class WindowServiceBlankPanel extends WindowService {

    private RowAddToTable rowAddToTable ;
    private RowDeleteFromTable rowDeleteFromTable ;

    @FXML private AnchorPane dynamicContentAnchorHolder;
    @FXML private AnchorPane titlePanel;
    @FXML private Button okBut;
    @FXML private Label title;

    public WindowServiceBlankPanel() {
        Registry.crossWindowMediators.put("BlankPanel",this);
    }
    @Override
    public void init() {
        currentWindow.setNodeToMap(dynamicContentAnchorHolder,"dynamicContentAnchorHolder");
        currentWindow.setNodeToMap(titlePanel,"titlePanel");
        currentWindow.setNodeToMap(okBut,"selectBut");
        currentWindow.setNodeToMap(title,"title");

        initCloseButtonEvent();
        initStageDragging();
        initTitle();
        bondingToContentAnchorWidth();
    }
    private void bondingToContentAnchorWidth() {
        dynamicContentAnchor = currentWindow.getCurrentDynamicContent().getDynamicContentAnchorHolder();
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setChildNode(dynamicContentAnchor);
        coordinate.setParentAnchorPane(dynamicContentAnchorHolder);
        coordinate.bonding();
    }
    private void initCloseButtonEvent() {
        ClosePopupAndSubWindow closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setMediator(this);
        closePopupAndSubWindow.setCallBackTyped(()->{
            if (callBackParametrized != null) { //if calback for bind table exist

        }
        closePopupAndSubWindow.setEventToElement(okBut);
return null;
    });  }
    private void initStageDragging() {
        new StageDragging().setEventToElement(titlePanel);
    }
    private void initTitle() {
        title.setText(currentWindow.getWindowImpl().getTitleName());
    }
    @Override
    public void inform(Object node) {
    }

}
