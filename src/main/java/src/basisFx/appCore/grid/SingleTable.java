package basisFx.appCore.grid;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.Insets;


public class SingleTable extends GridOrganization{
    private ButSizeForGrid butSizeForGrid;
    protected TableWrapper tableWrapper=null;

    public SingleTable(TableWrapper tableWrapper, ButSizeForGrid butSizeForGrid, CtrlPosition position) {

            this.tableWrapper = tableWrapper;
            this.ctrlPosition =position;
            this.butSizeForGrid = butSizeForGrid;
            this.butSizeForGrid.setTableWrapper(tableWrapper);
            this.butSizeForGrid.init();

    }
    public SingleTable(WindowAbstraction currentWindow,TableWrapper tableWrapper, ButSizeForGrid butSizeForGrid, CtrlPosition position) {

            this.tableWrapper = tableWrapper;
            this.ctrlPosition =position;
            this.butSizeForGrid = butSizeForGrid;
            this.butSizeForGrid.setTableWrapper(tableWrapper);
            this.butSizeForGrid.init();
            setCurrentWindow(currentWindow);

    }

    public void setTableWrapper(TableWrapper tableWrapper) {
        this.tableWrapper = tableWrapper;
    }

    public SingleTable(ButSizeForGrid butSizeForGrid, CtrlPosition position) {
        this.ctrlPosition =position;
        this.butSizeForGrid = butSizeForGrid;

    }

    @Override
    public SingleTable setInsets(Insets insets) {
        this.insets = insets;
        return this;
    }


    @Override
    public void organize() {
        handleLabel();

        if (butSizeForGrid instanceof ButSizeNon && ctrlPosition instanceof CtrlPosNON ){
                organizeNonButtons();
            }else{
                organizeWithButtons();
            }


    }

    private void organizeNonButtons() {
        parentGridWrapper.setColumnComputerWidth();
        ctrlPosition.setParentGridWrapper(parentGridWrapper);
        bindHeight(tableWrapper);
        if (label != null) {
            ctrlPosition.organize(label, tableWrapper.getElement()  );
        }else{

            ctrlPosition.organize( tableWrapper.getElement()  );
        }

    }

    private void organizeWithButtons() {
        butSizeForGrid.setTableWrapper(tableWrapper);
        butSizeForGrid.init();

        parentGridWrapper.setColumnComputerWidth();
        parentGridWrapper.setColumnFixed(butSizeForGrid.getColumnWidth());
        parentGridWrapper.setColumnFixed(butSizeForGrid.getColumnWidth());

        ctrlPosition.setParentGridWrapper(parentGridWrapper);
        bindHeight(tableWrapper);


        if (label != null) {
            ctrlPosition.organize(
                    label,
                    butSizeForGrid.buttonAdd,
                    butSizeForGrid.buttonDel,
                    tableWrapper.getElement()
            );
        }else{
            ctrlPosition.organize(
                    butSizeForGrid.buttonAdd,
                    butSizeForGrid.buttonDel,
                    tableWrapper.getElement()
            );
        }


    }
}
