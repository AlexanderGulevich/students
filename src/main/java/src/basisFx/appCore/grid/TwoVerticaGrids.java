package basisFx.appCore.grid;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TwoVerticaGrids extends GridOrganization {

    private final GridPaneWrapper gridWrapperTop;
    private final GridPaneWrapper gridWrapperBottom;


    public TwoVerticaGrids(GridOrganization organizationTop, String name1, GridOrganization organizationBottom, String name2 ) {

        gridWrapperTop = GridPaneWrapper.newBuilder()
                .setGridLinesVisibility(false)
                .setGridName(name1)
                .setOrganization(organizationTop).build();

        gridWrapperBottom = GridPaneWrapper.newBuilder()
                .setGridLinesVisibility(false)
                .setGridName(name2)
                .setOrganization(organizationBottom).build();


    }
    private void applyWidth() {

        parentGridWrapper.getElement().getColumnConstraints().get(0).prefWidthProperty().addListener(
                (observable, oldValue, newValue) -> {
                    gridWrapperTop.getElement().setPrefWidth(newValue.doubleValue());
                    gridWrapperBottom.getElement().setPrefWidth(newValue.doubleValue());
                }
        );


    }

    @Override
    public void organize() {

        parentGridWrapper.setRowConstraints();
        parentGridWrapper.setRowConstraints();
        applyWidth();

        if (parentGridWrapper.getText() != null) {
            createInnerNodsAndCommonName();
        }else{
            createInnerNodsWithoutCommonName();
        }
    }


    private void createInnerNodsAndCommonName() {
        LabelWrapper labelWrapper = createCommonLabel();

        Label label = labelWrapper.getElement();
        parentGridWrapper.addSpanNode(
                label,
                0,0,1,1, HPos.LEFT, VPos.CENTER,insets);

        GridPane gridPane1 = gridWrapperTop.getElement();
        parentGridWrapper.addSpanNode(
                gridPane1,
                0,1,1,1, HPos.LEFT, VPos.TOP,insets);


        GridPane gridPane2 = gridWrapperBottom.getElement();
        parentGridWrapper.addSpanNode(
                gridPane2,
                0,2,1,1, HPos.LEFT, VPos.TOP,insets);
    }

    private LabelWrapper createCommonLabel() {
        return LabelWrapper.newBuilder()
                .setText(parentGridWrapper.getText())
                .setCSSid(CSSid.LABEL_TEXT)
                .setFontSize(25d)
                .setFont(FontsStore.ROBOTO_BOLD)
                .build();
    }

    private void createInnerNodsWithoutCommonName() {
        parentGridWrapper.addSpanNode(
                gridWrapperTop.getElement(),
                0,0,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                gridWrapperBottom.getElement(),
                0,1,1,1, HPos.LEFT, VPos.TOP,insets);
    }

}
