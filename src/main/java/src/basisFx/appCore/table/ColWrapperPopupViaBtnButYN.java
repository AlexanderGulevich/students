package basisFx.appCore.table;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.SubWindowCreaterByBut;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.interfaces.Mediator;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.price.PriceItem;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleAction;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.*;
import javafx.util.Callback;

public class ColWrapperPopupViaBtnButYN extends ColWrapper {

    protected TableColumn<ActiveRecord, ActiveRecord> column;
    protected  WindowBuilder windowBuilder;
    private  DataStoreCallBack  checkingCallBack ;
    private String btnNameY;
    private String btnNameN;


    private ColWrapperPopupViaBtnButYN(Builder builder) {
        columnName = builder.columnName;
        columnSize = builder.columnSize;
        btnNameY = builder.btnNameY;
        btnNameN = builder.btnNameN;
        checkingCallBack=builder.checkingCallBack;
        windowBuilder = builder.windowBuilder;
        column =  new TableColumn <>(columnName);
        column.getStyleClass().add(CSSclasses.column_with_button_BFx.get());
        column.setResizable(false);
        column.setEditable(false);
        tableWrapper=builder.tableWrapper;



//
//        column.setOnEditCommit(event -> {
//            PriceItem newValue = (PriceItem) event.getRowValue();
//            System.out.println("newValue----"+newValue.getImg().getBarcode());
//        });

//
//        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ActiveRecord, ActiveRecord>, ObservableValue<ActiveRecord>>() {
//            @Override
//            public ObservableValue<ActiveRecord> call(TableColumn.CellDataFeatures<ActiveRecord, ActiveRecord> param) {
//                PriceItem priceItem = (PriceItem) param.getValue();
//                if (priceItem.getImg() != null) {
//
//                }
//                priceItem.imgProperty().addListener();
//                  ((ObservableValue<ActiveRecord> ) callBackTypedAndParametrized.call(param.getValue()));
//            }
//        });

        column.setCellFactory((TableColumn<ActiveRecord, ActiveRecord> param) -> {
            ButtonCustomCell buttonCustomCell = new ButtonCustomCell();
            return buttonCustomCell;
//            param.
//
//            buttonCustomCell.itemProperty().addListener(new ChangeListener<ActiveRecord>() {
//                @Override
//                public void changed(ObservableValue<? extends ActiveRecord> observable, ActiveRecord oldValue, ActiveRecord newValue) {
//                    System.out.println("ColWrapperPopupViaBtnButYN.changed----oldValue----"+oldValue);
//                    System.out.println("ColWrapperPopupViaBtnButYN.changed----newValue----"+newValue);
//                }
//            });
//
//            buttonCustomCell.itemProperty().addListener(new InvalidationListener() {
//                @Override
//                public void invalidated(Observable observable) {
//                    System.out.println("InvalidationListener.changed----oldValue----"+observable);
//                    System.out.println("InvalidationListener.changed----newValue----"+observable);
//                }
            });


//            param.getTableView().getItems().stream().forEach(record -> {
//                PriceItem record1 = (PriceItem) record;
//                record1.imgProperty().addListener();
//                record1.imgProperty().addListener(observable -> {
//                    buttonCustomCell.refreshName(record);
//                    System.out.println("   record1.imgProperty().addListener(observable -> {\n" +
//                            "                    buttonCustomCell.refreshName(record);");
//                });
//            });


//            param.setOnEditCommit(event -> {
//                ActiveRecord newValue = event.getNewValue();
//                buttonCustomCell.refreshName(newValue);
//            });
//            param.setOnEditStart(event ->   {
//                    ActiveRecord newValue = event.getNewValue();
//            buttonCustomCell.refreshName(newValue); });
//            return buttonCustomCell;
//        });




    }

    public static Builder newBuilder() {
        return new Builder();
    }



    class ButtonCustomCell extends TableCell<ActiveRecord,ActiveRecord> implements Mediator  {

        private SubWindowCreaterByBut subWindowCreaterByBut = new SubWindowCreaterByBut();
        private   Button btn ;
        protected  TableRow tableRow  ;

        public ButtonCustomCell() {
            createButton();
        }

        @Override
        public void startEdit() {
            ActiveRecord item = getItem();
            if (!isEmpty()) {
                super.startEdit();
                setText(null);
                setGraphic(btn);
            }
        }

        @Override
        public void updateItem(ActiveRecord item, boolean empty) {

            super.updateItem(item, empty);

            ActiveRecord record = getItem();
            if (empty) {
                setGraphic(null);
                refreshName(item);
            } else {

                //todo to create calback
                tableRow = getTableRow();
                PriceItem priceItem = (PriceItem) tableRow.getItem();
                if (priceItem != null) {
                        refreshName(priceItem);
                }
                 setGraphic(btn);
            }
        }

        private void createButton() {

            ActiveRecord item = getItem();
            btn = new Button();

            if (btn != null) {

                btn.getStyleClass().add(CSSclasses.table_column_buttons_BFx.get());
                subWindowCreaterByBut.setWindowBuilder(windowBuilder);
                subWindowCreaterByBut.setEventToElement(btn);
                subWindowCreaterByBut.setMediator(this);
            }


        }

        private void refreshName(ActiveRecord record) {
            if (checkCbButYN(record)) {
                btn.setText(btnNameY);
            }else {
                btn.setText(btnNameN);
            }
        }
        private boolean checkCbButYN(ActiveRecord record) {
            if (checkingCallBack != null) {
                ActiveRecord item = record;

                if (checkingCallBack.check(item)) {
                   return true;
                }else {
                    return false;
                }
            }else {
                return true;
            }
        }

        @Override
        public void inform(Object node) {
            if(node== subWindowCreaterByBut){
                tableWrapper.fireRowClick(tableRow);
            }
        }




    }

    public static final class Builder {
        private TableWrapper tableWrapper;
        private String columnName;
        private Double columnSize;
        private String btnNameY;
        private String btnNameN;
        protected  WindowBuilder windowBuilder;
        private  DataStoreCallBack  checkingCallBack ;

        private Builder() {
        }

        public TableWrapper getTableWrapper() {
            return tableWrapper;
        }

        public void setTableWrapper(TableWrapper tableWrapper) {
            this.tableWrapper = tableWrapper;
        }

        public Builder setWindowBuilder(WindowBuilder windowBuilder) {
            this.windowBuilder = windowBuilder;
            return this;
        }
        public Builder setDataStoreCallBack(DataStoreCallBack checkingCallBack) {
            this.checkingCallBack = checkingCallBack;
            return this;
        }

        public Builder setColumnName(String val) {
            columnName = val;
            return this;
        }

        public Builder setColumnSize(Double val) {
            columnSize = val;
            return this;
        }

        public Builder setBtnNameY(String val) {
            btnNameY = val;
            return this;
        }
        public Builder setBtnNameN(String val) {
            btnNameN = val;
            return this;
        }


        public ColWrapperPopupViaBtnButYN build() {
            return new ColWrapperPopupViaBtnButYN(this);
        }

    }

    @Override
    public void setOnEditCommit() {}

    @Override
    public TableColumn getColumn() {return column;}

    @Override
    protected boolean checkValue(TableColumn.CellEditEvent event) {return false;}

    @Override
    protected boolean checkValue(String s) {return false;}
}
