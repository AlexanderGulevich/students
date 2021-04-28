package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.TableBasedDirectors;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.service.ServiceTablesAutoCommitByDate;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class RowAddToSubmitTableWhisCommonDate<T> extends AppEvent implements TableEvents {

    private Button but;
    @Setter @Getter
    private TableWrapper tableWrapper;

    public RowAddToSubmitTableWhisCommonDate(TableWrapper tableWrapper ) {
        this.tableWrapper = tableWrapper;
    }

    @Override
    public void setEventToElement(AppNode node) {
        but=(Button) node.getElement();
        but.setOnMouseClicked((event) -> {
            run();
        });
    }

    @Override
    public void setEventToElement(Node node) {

    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        try {
            ActiveRecord newInstance = (ActiveRecord) tableWrapper.activeRecordClass.newInstance();
            if (tableWrapper.isItemListExist()
                    && ((ServiceTablesAutoCommitByDate) tableWrapper.getMediator()).
                          getDatePickerWrapper().getDate()!=null) {
                    tableWrapper.getItems().add(newInstance);
                    tableWrapper.scrollToItem(newInstance);
                    tableWrapper.focusItem(newInstance);
            }else {
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
