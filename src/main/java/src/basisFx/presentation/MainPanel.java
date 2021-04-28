package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainPanel extends DynamicContentPanel {



    @Override
    public void customDynamicElementsInit() {
        AnchorPane mainPanel = FXMLLoader.loadAnchorPane("mainPanel");
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setParentAnchorPane(dynamicContentAnchorHolder);
        coordinate.setChildNode(mainPanel);
        coordinate.bonding();

    }
}
