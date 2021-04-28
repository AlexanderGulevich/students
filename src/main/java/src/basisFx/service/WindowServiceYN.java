package basisFx.service;

import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Registry;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class WindowServiceYN extends WindowService {
    @FXML private JFXButton okBut;
    @FXML private JFXButton noBut;
    @FXML private TextArea textArea;
    @FXML private Label title;
    @FXML private AnchorPane titleAnchor;

    ClosePopupAndSubWindow okEvent;
    ClosePopupAndSubWindow noEvent;

    @FXML public void initialize() {
    }

    public WindowServiceYN() {
        Registry.crossWindowMediators.put("YN",this);
    }

    @Override
    public void init() {
        okEvent = new ClosePopupAndSubWindow();
        okEvent.setEventToElement(okBut);
        okEvent.setMediator(this);
        noEvent = new ClosePopupAndSubWindow();
        noEvent.setEventToElement(noBut);
        noEvent.setMediator(this);

        new StageDragging().setEventToElement(titleAnchor);

    }

    public void setMessage(String str) {
        textArea.setText(str);
    }

    @Override
    public void inform(Object node) {
        if (node==okBut){
            callBackParametrized.call(true);
        }
        if (node==noBut) {
            callBackParametrized.call(false);
        }
        }


}
