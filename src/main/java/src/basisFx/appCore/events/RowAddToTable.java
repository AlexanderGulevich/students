package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.TableBasedDirectors;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class RowAddToTable<T> extends AppEvent implements TableEvents {

    private Button but;

    @Setter @Getter private TableWrapper tableWrapper;
    @Getter private ActiveRecord currentNewInstance;

    public RowAddToTable(TableWrapper tableWrapper ) {
        this.tableWrapper = tableWrapper;
    }

    @Override
    public void setEventToElement(AppNode node) {
        but=(Button) node.getElement();
        but.setOnMouseClicked((event) -> {
            if (callBackTyped != null) {
                if (((Boolean) callBackTyped.call())) {
                    run();
                }
            }else {
                run();
            }
        });
    }

    @Override
    public void setEventToElement(Node node) {
        but=(Button) node;
        but.setOnMouseClicked((event) -> {
            if (callBackTyped != null) {
                if (((Boolean) callBackTyped.call())) {
                    run();
                }
            }else {
                run();
            }
        });
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        try {
            ActiveRecord newInstance = (ActiveRecord) tableWrapper.activeRecordClass.newInstance();
            if (tableWrapper.getOuterTable() != null) {
                newInstance.outerId=tableWrapper.getOuterTable().clickedDomain.getId();
            }
            if (tableWrapper.isItemListExist() && !tableWrapper.haveNewItem()) {

                System.out.println("RowAddToTable.run---tableWrapper.isItemListExist() && !tableWrapper.haveNewItem()");
                    tableWrapper.getItems().add(newInstance);
                    tableWrapper.scrollToItem(newInstance);
                    tableWrapper.focusItem(newInstance);

                if (mediator != null) {

                    currentNewInstance=newInstance;
                    mediator.inform(this);
                }

            }else {
                System.err.println("RowAddToTable--!tableWrapper.isItemListExist() && tableWrapper.haveNewItem()".toUpperCase());
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
