package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FXMLLoader;
import basisFx.appCore.utils.FontLogic;
import basisFx.appCore.utils.Registry;
import basisFx.service.CategoriesPanel;
import javafx.scene.control.Label;

public class CommonPanel extends DynamicContentPanel {
    String fontSymbol;
    FontsStore fontsStore;
    double fontSize;

    private Label commonLabel;

    public CommonPanel(String fontSymbol, FontsStore fontsStore) {
        this.fontSymbol = fontSymbol;
        this.fontsStore = fontsStore;
        this.fontSize = fontSize;
    }

    @Override
    public void customDynamicElementsInit() {
        FXMLLoader.loadAnchorPane("categiriesPanel");
        CategoriesPanel panel = (CategoriesPanel) Registry.crossWindowMediators.get("CategoriesPanel");
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setParentAnchorPane(dynamicContentAnchorHolder);
        coordinate.setChildNode(panel.getAnchor());
        coordinate.bonding();

        commonLabel = panel.getCommonLabel();
        commonLabel.setText(fontSymbol);
        commonLabel.setFont(FontLogic.loadFont(fontsStore,200));
    }
}
