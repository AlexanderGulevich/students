package basisFx.appCore.grid;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import lombok.Setter;
import lombok.experimental.Accessors;

public abstract class CtrlPosition {

    protected Insets insets = new Insets(3, 3, 3, 3);
    protected GridPaneWrapper parentGridWrapper;


    public abstract void organize(Label label, Button addBut, Button delBut, TableView tableView);
    public abstract void organize(Button addBut, Button delBut, TableView tableView);
    public abstract void organize(Label label,  TableView tableView);
    public abstract void organize(TableView tableView);

    public void setParentGridWrapper(GridPaneWrapper parentGridWrapper) {
        this.parentGridWrapper = parentGridWrapper;
    }
}
