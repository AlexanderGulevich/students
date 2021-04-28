package basisFx.service;

import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Registry;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class WindowServiceInfo extends WindowService {
    @FXML private JFXButton okBut;
    @FXML private TextArea message;
    @FXML private AnchorPane titleAnchor;
    @FXML private AnchorPane rightAnchor;
    @FXML private Label titleLabel;

    @FXML public void initialize() {
    }

    public WindowServiceInfo() {

        Registry.crossWindowMediators.put("Info",this);

    }

    @Override
    public void init() {
        new ClosePopupAndSubWindow().setEventToElement(okBut);
        new StageDragging().setEventToElement(titleAnchor);
        new StageDragging().setEventToElement(rightAnchor);

    }

    public void setMessage(String str) {
        message.setText(str);
    }

    @Override
    public void inform(Object node) {

    }
}
