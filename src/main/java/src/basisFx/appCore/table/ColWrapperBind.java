package basisFx.appCore.table;

import basisFx.appCore.interfaces.CallBackTypedAndParametrized;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColWrapperBind extends ColWrapper {
        protected TableColumn<ActiveRecord, ActiveRecord> column;
        protected CallBackTypedAndParametrized callBackTypedAndParametrized;
        private ColWrapperBind(Builder builder) {
            columnName = builder.columnName;
            columnSize = builder.columnSize;
            callBackTypedAndParametrized = builder.callBackTypedAndParametrized;
            column =  new TableColumn <>(columnName);
            column.getStyleClass().add(CSSclasses.column_with_button_BFx.get());
            column.setResizable(false);
            column.setEditable(false);
            column.setCellFactory((TableColumn<ActiveRecord, ActiveRecord> param) -> new ButtonCustomCell());
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ActiveRecord, ActiveRecord>, ObservableValue<ActiveRecord>>() {
                @Override
                public ObservableValue<ActiveRecord> call(TableColumn.CellDataFeatures<ActiveRecord, ActiveRecord> param) {
                    return ((ObservableValue<ActiveRecord> ) callBackTypedAndParametrized.call(param.getValue()));
                }
            });
            setOnEditCommit();
        }
        public static Builder newBuilder() {
            return new Builder();
        }
        class ButtonCustomCell extends TableCell<ActiveRecord,ActiveRecord> implements Mediator {
            @Override
            public void updateItem(ActiveRecord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    if (item != null) {
                        setText(item.toString());
                    }

                }
            }

            @Override
            public void inform(Object node) {

            }
        }

        public static final class Builder {
            private String columnName;
            private Double columnSize;
            protected CallBackTypedAndParametrized callBackTypedAndParametrized;
            private Builder() {
            }

            public Builder setCallBackTypedAndParametrized(CallBackTypedAndParametrized callBackTypedAndParametrized) {
                this.callBackTypedAndParametrized = callBackTypedAndParametrized;
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
            public ColWrapperBind build() {
                return new ColWrapperBind(this);
            }

        }

        @Override
        public void setOnEditCommit() {  }

        @Override
        public TableColumn getColumn() {return column;}

        @Override
        protected boolean checkValue(TableColumn.CellEditEvent event) {return false;}

        @Override
        protected boolean checkValue(String s) {return false;}

}
