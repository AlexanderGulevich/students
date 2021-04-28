package basisFx.service;

import basisFx.appCore.utils.Registry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

public class MainPanel extends WindowService {

    @FXML @Getter
    private Label commonLabel;
    @FXML @Getter
    private AnchorPane anchor;

    public MainPanel()
    {
        Registry.crossWindowMediators.put("CommonLabel",this);
    }

    @Override
    public void init() {

    }

    @Override
    public void inform(Object node) {

    }
}
