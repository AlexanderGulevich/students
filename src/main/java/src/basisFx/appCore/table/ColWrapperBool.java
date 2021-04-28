package basisFx.appCore.table;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ColWrapperBool<T> extends ColWrapper {

    protected TableColumn<ActiveRecord, BoolComboBox> column;
    protected ActiveRecord domain;
    protected Class <? extends ActiveRecord> domainClass;

    private ColWrapperBool(Builder builder) {
        tableWrapper = builder.tableWrapper;
        propertyName = builder.propertyName;
        columnName = builder.columnName;
        columnSize = builder.columnSize;
        isEditeble = builder.isEditeble;
        domain = BoolComboBox.getINSTANCE();
        column =  new TableColumn<>(columnName);
        column.setEditable(isEditeble);
        column.setResizable(false);
        setCellValueFactory();
        setCellFactory();
        setOnEditCommit();
    }
    @Override
    public void setOnEditCommit() {
        column.setOnEditCommit((event) -> {
            if (checkValue(event)) {
                int row = event.getTablePosition().getRow();
                ObservableValue<BoolComboBox> value = event.getTableColumn().getCellObservableValue(row);
                if (value instanceof WritableValue) {
                    ((WritableValue<BoolComboBox>)value).setValue(event.getNewValue());
                }
                ActiveRecord domain = (ActiveRecord) event.getRowValue();
                tableWrapper.getMediator().wasChanged(tableWrapper,domain);

            };
        });

    }
    private void setCellFactory() {
        Callback<TableColumn<ActiveRecord, BoolComboBox>, TableCell<ActiveRecord, BoolComboBox>> comboBoxCellFactory
                = (TableColumn<ActiveRecord, BoolComboBox> param) -> new  ComboBoxCustomCell();
        column.setCellFactory(comboBoxCellFactory);
    }

    private void setCellValueFactory() {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    @Override
    public TableColumn getColumn() {
        return column;
    }

    @Override
    protected boolean checkValue(TableColumn.CellEditEvent event) {
        return true;
    }

    @Override
    protected boolean checkValue(String s) {
        return false;
    }

    public static final class Builder {
        public Class domainClass;
        private TableWrapper tableWrapper;
        private String propertyName;
        private String columnName;
        private Double columnSize;
        private Boolean isEditeble;

        private Builder() {
        }

        public Builder setDomainClass(Class domainClass) {
            this.domainClass = domainClass;
            return this;
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

        public ColWrapperBool build() {
            return new ColWrapperBool(this);
        }
    }

    class ComboBoxCustomCell extends TableCell<ActiveRecord, BoolComboBox> {

        private ComboBox<BoolComboBox> comboBox;

        private ComboBoxCustomCell() {}

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                setText(null);
                setGraphic(comboBox);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getNamedDomainObject().getStringValue());
            setGraphic(null);
        }

        @Override
        public void updateItem(BoolComboBox item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (comboBox != null) {
                        comboBox.setValue(getNamedDomainObject());
                    }
                    setText(getNamedDomainObject().getStringValue());
                    setGraphic(comboBox);
                } else {
                    setText(getNamedDomainObject().getStringValue());
                    setGraphic(null);
                }
            }
        }

        private void createComboBox() {
            ObservableList<BoolComboBox> comboBoxValues = BoolComboBox.getComboBoxes();

            comboBox = new ComboBox<>(comboBoxValues);

            comboBox.getStyleClass().add(CSSclasses.COMBOBOX_BFx.get());
            comboBox.setEditable(false);
//            comboBox.setPromptText("fgfg");
            comboBoxConverter(comboBox);
            comboBox.valueProperty().set(getNamedDomainObject());
            comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            comboBox.setOnAction((e) -> {
                commitEdit(comboBox.getSelectionModel().getSelectedItem());
//                tableWrapper.getMediator().wasChanged(tableWrapper,comboBox.getSelectionModel().getSelectedItem());




            });
            comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
                }
            });
        }

        private void comboBoxConverter(ComboBox<BoolComboBox> comboBox) {
//             Define rendering of the list of values in ComboBox drop down.
            comboBox.setCellFactory((c) -> new ListCell<BoolComboBox>() {
                @Override
                protected void updateItem(BoolComboBox value, boolean empty) {
                    super.updateItem(value, empty);
                    if (value == null || empty) {
                        setText(null);
                    } else {
                        setText(value.getStringValue());
                    }
                }
            });
        }

        private BoolComboBox getNamedDomainObject() {
            if(getItem()== null){//if not exist
                BoolComboBox comboBoxValue =new BoolComboBox("");
                return comboBoxValue;
            }else {
                return  getItem();
            }
        }
    }




}
