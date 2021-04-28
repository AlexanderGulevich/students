package basisFx.appCore.grid;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

public abstract class GridOrganization {

    @Setter @Accessors(chain = true)  protected WindowAbstraction currentWindow;
    @Getter  protected Insets insets = new Insets(3, 3, 3, 3);
    @Getter @Setter protected GridPaneWrapper parentGridWrapper;
    @Getter @Setter protected CtrlPosition ctrlPosition;
    protected Label label;

    public abstract void organize();

    public SingleTable setInsets(Insets insets) {
        this.insets = insets;
        return null;
    }
    protected void bindHeight(GridPaneWrapper gridPaneWrapper){
        parentGridWrapper.getElement().prefHeightProperty().addListener((observable, oldValue, newValue) -> {
            gridPaneWrapper.getElement().setPrefHeight(newValue.doubleValue()-10d);
        });

    }
    protected void bindHeight(TableWrapper tableWrapper){
        parentGridWrapper.getElement().prefHeightProperty().addListener((observable, oldValue, newValue) -> {
            tableWrapper.getElement().setPrefHeight(newValue.doubleValue()-10d);
        });
    }
    public  void setTableWrapper(TableWrapper tableWrapper){};

    protected void handleLabel() {
        if (parentGridWrapper != null) {
            if (parentGridWrapper.label != null) {
                label = parentGridWrapper.label.getElement();
                if (currentWindow != null) {
                    currentWindow.setNodeToMap(label,"gridLabel");
                }
            }
        }

    }


}
