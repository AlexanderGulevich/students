package basisFx.appCore.grid;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class TwoHorisontalBondGrids extends GridOrganization {

    protected GridPaneWrapper leftGridWrapper;
    protected GridPaneWrapper rightGridWrapper;

    public TwoHorisontalBondGrids(GridPaneWrapper leftGridWrapper, GridPaneWrapper rightGridWrapper) {
        this.leftGridWrapper = leftGridWrapper;
        this.rightGridWrapper = rightGridWrapper;
    }
    public TwoHorisontalBondGrids(TableWrapper tableWrapperLEFT, TableWrapper tableWrapperRIGHT) {
        this.leftGridWrapper = tableWrapperLEFT.getGridPaneWrapper();
        this.rightGridWrapper = tableWrapperRIGHT.getGridPaneWrapper();
    }


    @Override
    public void organize() {

        bindHeight(leftGridWrapper);
        bindHeight(rightGridWrapper);

        if (parentGridWrapper.getText() != null) {
            createInnerNodsAndCommonName();
        }else{
            createInnerNodsWithoutCommonName();
        }
        applyWidth();
    }

    private void applyWidth() {

        parentGridWrapper.getElement().getColumnConstraints().get(0).prefWidthProperty().addListener(
                (observable, oldValue, newValue) -> {
                    leftGridWrapper.getElement().setPrefWidth(newValue.doubleValue());
                }
        );
         parentGridWrapper.getElement().getColumnConstraints().get(1).prefWidthProperty().addListener(
                (observable, oldValue, newValue) -> {
                    rightGridWrapper.getElement().setPrefWidth(newValue.doubleValue());
                }
        );


    }

    private void createInnerNodsAndCommonName() {
        LabelWrapper labelWrapper = createCommonLabel();

        parentGridWrapper.addSpanNode(
                labelWrapper.getElement(),
                0,0,2,1, HPos.LEFT, VPos.CENTER,insets);

        parentGridWrapper.addSpanNode(
                leftGridWrapper.getElement(),
                0,1,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                rightGridWrapper.getElement(),
                1,1,1,1, HPos.LEFT, VPos.TOP,insets);
    }

    private LabelWrapper createCommonLabel() {
        return LabelWrapper.newBuilder()
                    .setText(parentGridWrapper.getText())
//                    .setCSSid(CSSid.LABEL_TEXT)
                    .setCSSid(CSSid.LABEL_COMMON_GRID_TEXT)
                    .setFontSize(25d)
                    .setFont(FontsStore.ROBOTO_BOLD)
                    .build();
    }

    private void createInnerNodsWithoutCommonName() {
        parentGridWrapper.addSpanNode(
                leftGridWrapper.getElement(),
                0,0,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                rightGridWrapper.getElement(),
                1,0,1,1, HPos.LEFT, VPos.TOP,insets);
    }



}
