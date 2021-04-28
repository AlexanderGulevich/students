package basisFx.appCore.grid;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class FourGrids extends GridOrganization {

    protected GridPaneWrapper leftGridWrapperTop;
    protected GridPaneWrapper rightGridWrapperTop;
    protected GridPaneWrapper leftGridWrapperBottom;
    protected GridPaneWrapper rightGridWrapperBottom;

    public GridPaneWrapper getLeftGridWrapperTop() {
        return leftGridWrapperTop;
    }

    public GridPaneWrapper getRightGridWrapperTop() {
        return rightGridWrapperTop;
    }

    public GridPaneWrapper getLeftGridWrapperBottom() {
        return leftGridWrapperBottom;
    }

    public GridPaneWrapper getRightGridWrapperBottom() {
        return rightGridWrapperBottom;
    }

    public FourGrids setGrids(GridPaneWrapper leftGridWrapperTop, GridPaneWrapper rightGridWrapperTop,
                              GridPaneWrapper leftGridWrapperBottom, GridPaneWrapper rightGridWrapperBottom) {
        this.leftGridWrapperTop = leftGridWrapperTop;
        this.rightGridWrapperTop = rightGridWrapperTop;
        this.leftGridWrapperBottom = leftGridWrapperBottom;
        this.rightGridWrapperBottom = rightGridWrapperBottom;

        return this;
    }

    @Override
    public void organize() {


        parentGridWrapper.setRowConstraints();

//        bindHeight(leftGridWrapper);
//        bindHeight(rightGridWrapper);

        if (parentGridWrapper.getText() != null) {
            createInnerNodsAndCommonName();
        }else{
            createInnerNodsWithoutCommonName();
        }
        applyWidth();
    }

    private void applyWidth() {

//        parentGridWrapper.getElement().getColumnConstraints().get(0).prefWidthProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    leftGridWrapper.getElement().setPrefWidth(newValue.doubleValue());
//                }
//        );
//        parentGridWrapper.getElement().getColumnConstraints().get(1).prefWidthProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    rightGridWrapper.getElement().setPrefWidth(newValue.doubleValue());
//                }
//        );


    }

    private void createInnerNodsAndCommonName() {
        LabelWrapper labelWrapper = createCommonLabel();

        parentGridWrapper.addSpanNode(
                labelWrapper.getElement(),
                0,0,2,1, HPos.LEFT, VPos.CENTER,insets);

        parentGridWrapper.addSpanNode(
                leftGridWrapperTop.getElement(),
                0,1,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                rightGridWrapperTop.getElement(),
                1,1,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                leftGridWrapperBottom.getElement(),
                0,2,1,1, HPos.LEFT, VPos.TOP,insets);

        parentGridWrapper.addSpanNode(
                rightGridWrapperBottom.getElement(),
                1,2,1,1, HPos.LEFT, VPos.TOP,insets);
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
//        parentGridWrapper.addSpanNode(
//                leftGridWrapper.getElement(),
//                0,0,1,1, HPos.LEFT, VPos.TOP,insets);
//
//        parentGridWrapper.addSpanNode(
//                rightGridWrapper.getElement(),
//                1,0,1,1, HPos.LEFT, VPos.TOP,insets);
    }


    protected void bindHeight(GridPaneWrapper gridPaneWrapper){
        parentGridWrapper.getElement().getRowConstraints().get(0).percentHeightProperty().addListener((observable, oldValue, newValue) -> {
            gridPaneWrapper.getElement().setPrefHeight(newValue.doubleValue()-10d);
        });
        parentGridWrapper.getElement().getRowConstraints().get(1).percentHeightProperty().addListener((observable, oldValue, newValue) -> {
            gridPaneWrapper.getElement().setPrefHeight(newValue.doubleValue()-10d);
        });


    }
    protected void bindHeight(TableWrapper tableWrapper){
        parentGridWrapper.getElement().prefHeightProperty().addListener((observable, oldValue, newValue) -> {
            tableWrapper.getElement().setPrefHeight(newValue.doubleValue()-10d);
        });
    }



}
