package basisFx.appCore.table;

import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.windows.WindowImpl;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import basisFx.domain.students.Student;
import basisFx.domain.students.StudentsGroup;
import basisFx.service.StudentsController;
import basisFx.service.WindowService;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColWrapperPopup extends ColWrapper {
        protected TableColumn<ActiveRecord, ActiveRecord> column;
        protected WindowBuilder windowBuilder;
        private ColWrapperPopup(Builder builder) {
            propertyName = builder.propertyName;
            columnName = builder.columnName;
            isEditeble=builder.isEditeble;
            columnSize = builder.columnSize;
            windowBuilder = builder.windowBuilder;
            column =  new TableColumn <>(columnName);
            column.getStyleClass().add("leftColumnContentElighment");
//            column.getStyleClass().add(Message.column_with_button_BFx.get());
            column.setResizable(false);
            column.setEditable(isEditeble);
            column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
            column.setCellFactory((TableColumn<ActiveRecord, ActiveRecord> param) -> new ButtonCustomCell());
            setOnEditCommit();
        }
        public static Builder newBuilder() {
            return new Builder();
        }
        class ButtonCustomCell extends TableCell<ActiveRecord,ActiveRecord> implements Mediator {
            protected TableRow tableRow;
            @Override
            public void startEdit() {
                if (!isEmpty()) {
                    super.startEdit();
//                    setText(null);
                    setFocused(true);
                    setEditable(true);
                    setHover(true);
                    setGraphic(null);
                    WindowAbstraction windowAbstraction = Registry.windowFabric.customSubWindow(windowBuilder);
                    System.out.printf("");
                    WindowImpl windowImpl = windowAbstraction.getWindowImpl();
                    WindowService windowService =windowImpl.getWindowService();
                    windowService.setPreClosingCallBack(windowBuilder.preClosingCallBack);
                    windowService.setCallBackParametrized(
                            (t)->{
                                ActiveRecord record = (ActiveRecord) t;
                                if (record != null) {
                                    updateItem(  record, false);
                                }
                            });

                }
            }
            @Override
            public void updateItem(ActiveRecord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    if (item != null) {
                        setText("  "+ item.toString());
                        commitEdit(item);
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
            protected  WindowBuilder windowBuilder;
            private String propertyName;
            private Boolean isEditeble;

            private Builder() {
            }

            public Builder setWindowBuilder(WindowBuilder windowBuilder) {
                this.windowBuilder = windowBuilder;
                return this;
            }

            public Builder setColumnName(String val) {
                columnName = val;
                return this;
            }


            public Builder setIsEditeble(Boolean val) {
                isEditeble = val;
                return this;
            }
            public Builder setColumnSize(Double val) {
                columnSize = val;
                return this;
            }
            public Builder setPropertyName(String val) {
                propertyName = val;
                return this;
            }

            public ColWrapperPopup build() {
                return new ColWrapperPopup(this);
            }

        }

        @Override
        public void setOnEditCommit() {
//
            column.setOnEditCommit((event) -> {
                    int row = event.getTablePosition().getRow();
                    ObservableValue<ActiveRecord> value = event.getTableColumn().getCellObservableValue(row);

                    if (value instanceof WritableValue) {
                        ((WritableValue<ActiveRecord>)value).setValue(event.getNewValue());



                    }


                ActiveRecord domain = (ActiveRecord) event.getRowValue();
                    tableWrapper.getMediator().wasChanged(tableWrapper,domain);

            });


        }

        @Override
        public TableColumn getColumn() {return column;}

        @Override
        protected boolean checkValue(TableColumn.CellEditEvent event) {return false;}

        @Override
        protected boolean checkValue(String s) {return false;}

}
