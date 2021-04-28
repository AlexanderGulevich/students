package basisFx.appCore.table;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.text.DecimalFormat;

public class ColWrapperDouble<T>extends ColWrapper {

    protected TableColumn<T,Double> column;
    protected String groupingSeparator ;
    protected DecimalFormat decimalFormat = new DecimalFormat("###,###.00");

    private ColWrapperDouble(Builder builder) {
        tableWrapper = builder.tableWrapper;
        propertyName = builder.propertyName;
        columnName = builder.columnName;
        columnSize = builder.columnSize;
        isEditeble = builder.isEditeble;

        column =  new TableColumn<>(columnName);
        column.setId("rightColumnContentElighment");
        groupingSeparator = String.valueOf(decimalFormat.getDecimalFormatSymbols().getGroupingSeparator());

        column.setResizable(false);
        column.setEditable(isEditeble);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        column.setCellFactory(TextFieldTableCell.forTableColumn(new CustomeDoubleStringConverter()));

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

                int row = event.getTablePosition().getRow();
                ObservableValue<Double> v = event.getTableColumn().getCellObservableValue(row);
                if (v instanceof WritableValue) {
                    ((WritableValue<Double>)v).setValue(Double.valueOf(event.getNewValue()));
                }
                ActiveRecord domain = (ActiveRecord) event.getRowValue();
                tableWrapper.getMediator().wasChanged(tableWrapper,domain);

        });
    }

    @Override
    public TableColumn getColumn() {
        return column;
    }

    @Override
    protected boolean checkValue(TableColumn.CellEditEvent event) {

            return false;
    }

    @Override
    protected boolean checkValue(String s) {
        try {
            s=s.trim();
            if(s.contains(",")){
                s=s.replace(',','.');
            }
            Double v=Double.parseDouble(s);
        }catch (NumberFormatException   e){
            Registry.windowFabric.infoWindow("Вы ввели в поле ввода неправильное значение!" +
                    "\n" +"Введенное значение - " +"\" "+s+" \"");
            return false;
        }
        return  true;
    }

    public static final class Builder {
        private TableWrapper tableWrapper;
        private String propertyName;
        private String columnName;
        private double columnSize;
        private Boolean isEditeble;

        private Builder() {
        }

        public Builder setTableWrapper(TableWrapper val) {
            tableWrapper = val;
            return this;
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

        public ColWrapperDouble build() {
            return new ColWrapperDouble(this);
        }
    }


    class CustomeDoubleStringConverter extends DoubleStringConverter{
        /** {@inheritDoc} */
        @Override public Double fromString(String value) {
            // If the specified value is null or zero-length, return null
            if (value == null) {
                return null;
            }

            value = value.trim();
            value=value.replaceAll(groupingSeparator,"");
            value=value.replaceAll(" ","");

            if (value.length() < 0) {
                return null;
            }

            if (value.equals("") ) {
                return 0d;
            }

            if (value.equals(" ") ) {
                return 0d;
            }
            if (value.equals("  ") ) {
                return 0d;
            }

            if (checkValue(value)) {
                if(value.contains(",")){
                    value=value.replace(',','.');
                }
                Double aDouble = Double.valueOf(value);
                return aDouble;
            }
            return null;
        }

        /** {@inheritDoc} */
        @Override public String toString(Double value) {

            if (value != null && value!=0.0d) {
                String string = decimalFormat.format(value)+"    ";
                return string;
            }
            return "";
        }
    }
}
