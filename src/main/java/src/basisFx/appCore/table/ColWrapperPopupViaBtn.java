package basisFx.appCore.table;

import basisFx.appCore.events.SubWindowCreaterByBut;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

public class ColWrapperPopupViaBtn extends ColWrapper {

    protected TableColumn<ActiveRecord, ActiveRecord> column;
    protected  String btnName;
    protected  WindowBuilder windowBuilder;
    private  DataStoreCallBack  checkingCallBack ;


    private ColWrapperPopupViaBtn(Builder builder) {
        columnName = builder.columnName;
        columnSize = builder.columnSize;
        btnName = builder.btnName;
        checkingCallBack=builder.checkingCallBack;
        windowBuilder = builder.windowBuilder;
        column =  new TableColumn <>(columnName);
        column.getStyleClass().add(CSSclasses.column_with_button_BFx.get());
        column.setResizable(false);
        column.setEditable(false);
        column.setCellFactory((TableColumn<ActiveRecord, ActiveRecord> param) -> new ButtonCustomCell());
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
            if (!isEmpty()) {
                super.startEdit();
                createButton();
                setText(null);
                setGraphic(btn);
            }
        }

        @Override
        public void updateItem(ActiveRecord item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                tableRow = getTableRow();
                 setGraphic(btn);
            }
        }

        private void createButton() {

            if (checkingCallBack != null) {
                if (checkingCallBack.check(getItem())) {
                    btn = new Button(btnName);
                }else {
                    btn = new Button("---");
                }
            }else {
                btn = new Button(btnName);
            }

            if (btn != null) {

                btn.getStyleClass().add(CSSclasses.table_column_buttons_BFx.get());
                subWindowCreaterByBut.setWindowBuilder(windowBuilder);
                subWindowCreaterByBut.setEventToElement(btn);
                subWindowCreaterByBut.setMediator(this);
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
        private String columnName;
        private Double columnSize;
        private String btnName;
        protected  WindowBuilder windowBuilder;
        private  DataStoreCallBack  checkingCallBack ;

        private Builder() {
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

        public Builder setBtnName(String val) {
            btnName = val;
            return this;
        }


        public ColWrapperPopupViaBtn build() {
            return new ColWrapperPopupViaBtn(this);
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
