package basisFx.appCore.table;

import basisFx.appCore.utils.Registry;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

public class ColWrapperString<T> extends ColWrapper {
    protected TableColumn<T,String> column;

    private ColWrapperString(Builder builder) {
        propertyName = builder.propertyName;
        columnName = builder.columnName;
        columnSize = builder.columnSize;
        isEditeble = builder.isEditeble;

        column =  new TableColumn<>(columnName);


        column.setResizable(false);
        column.setEditable(isEditeble);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
//        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setCellFactory(new CustomeTextFieldTableCell().init());

        setOnEditCommit();

        if (columnSize != null) {
            column.setPrefWidth(columnSize);
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public void setOnEditCommit() {
        column.setOnEditCommit((event) -> {
            if (checkValue(event)) {
                int row = event.getTablePosition().getRow();
                ObservableValue<String> value = event.getTableColumn().getCellObservableValue(row);
                if (value instanceof WritableValue) {
                    ((WritableValue<String>)value).setValue(event.getNewValue());
                }
                ActiveRecord domain = (ActiveRecord) event.getRowValue();
                tableWrapper.getMediator().wasChanged(tableWrapper,domain);

            };
        });

    }

    @Override
    public TableColumn getColumn() {
        return column;
    }

    @Override
    protected boolean checkValue(TableColumn.CellEditEvent event) {
        String value = (String) event.getNewValue();
        value=value.trim();
        value=value;
//        int row = event.getTablePosition().getRow();
//        ObservableValue<DefaultPanelsNames> observableValue = event.getTableColumn().getCellObservableValue(row);
//        DefaultPanelsNames value= observableValue.getValue().trim();
        if (value.length()==0){
            Registry.windowFabric.infoWindow("Вы ввели в поле ввода пустую строку!");
            return false;
        }
        return true;
    }

    @Override
    protected boolean checkValue(String s) {
        return false;
    }




    public static final class Builder {
        private String propertyName;
        private String columnName;
        private double columnSize;
        private Boolean isEditeble;

        private Builder() {
        }

        public Builder setPropertyName(String val) {
            propertyName = val;
            return this;
        }

        public Builder setColumnName(String val) {
            columnName = val;
            return this;
        }

        public Builder setColumnSize(double val) {
            columnSize = val;
            return this;
        }

        public Builder setIsEditeble(Boolean val) {
            isEditeble = val;
            return this;
        }

        public ColWrapperString build() {
            return new ColWrapperString(this);
        }
    }


    class CustomeStringConverter extends DefaultStringConverter {

        /** {@inheritDoc} */
        @Override public String toString(String value) {
            return (value != null) ? "  "+value : "";
        }

        /** {@inheritDoc} */
        @Override public String fromString(String value) {
            return value.trim();
        }

    }

    class CustomeTextFieldTableCell extends TextFieldTableCell {


        public  <S> Callback<TableColumn<S,String>, TableCell<S,String>> init() {
            return forTableColumn(new CustomeStringConverter());
        }


    }



}
