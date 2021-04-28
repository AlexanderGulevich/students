package basisFx.presentation;

        import basisFx.appCore.DynamicContentPanel;
        import basisFx.appCore.utils.Coordinate;
        import basisFx.appCore.utils.FXMLLoader;
        import javafx.scene.layout.AnchorPane;

public class Students extends DynamicContentPanel {



    @Override
    public void customDynamicElementsInit() {
        AnchorPane mainPanel = FXMLLoader.loadAnchorPane("Students");
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setParentAnchorPane(dynamicContentAnchorHolder);
        coordinate.setChildNode(mainPanel);
        coordinate.bonding();

    }
}
