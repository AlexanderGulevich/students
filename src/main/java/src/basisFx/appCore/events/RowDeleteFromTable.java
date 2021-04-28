package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.TableBasedDirectors;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class RowDeleteFromTable extends AppEvent implements TableEvents {
    private Button but;
    @Setter @Getter
    private TableWrapper tableWrapper;

    public RowDeleteFromTable(){
    }
    public RowDeleteFromTable(TableWrapper t) {
        this.tableWrapper = t;
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
        but=(Button) node;
        but.setOnMouseClicked((event) -> {

            run();

        });
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }


    @Override
    public void run()   {

        TableView.TableViewSelectionModel<ActiveRecord> selectionModel = tableWrapper.getElement().getSelectionModel();

        if(!selectionModel.isEmpty()){

            final ActiveRecord selectedItem = selectionModel.getSelectedItem();
            if (selectedItem != null) {
                tableWrapper.getItems().remove(selectedItem);
                tableWrapper.getMediator().wasRemoved(tableWrapper,selectedItem);
            }


        }



    }

}
