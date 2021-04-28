package basisFx.appCore.panelSets;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.AppEvent;
import basisFx.appCore.events.TableEvents;
import basisFx.appCore.grid.*;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.table.ColWrapper;
import basisFx.appCore.table.ColWrapperPopupViaBtnButYN;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Range;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.dataSource.UnitOfWork;
import basisFx.service.ServiceTablesSingle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class SingleTableSet implements PanelSets {
    @Builder.Default @Getter private ServiceTablesSingle mediatorSingleTable=new ServiceTablesSingle();
    @Builder.Default private UnitOfWork unitOfWork=new UnitOfWork();
    private TableWrapper tableWrapper;
    @Builder.Default private CtrlPosFactory posFactory=CtrlPosFactory.getInstance();
    @Builder.Default private ButSizeFactory bsFactory=ButSizeFactory.getInstance();
    @Builder.Default private Coordinate coordinate=new Coordinate(0d, 10d, null, 0d);
    private RangeDirector rangeDirector;
    private TableEvents delButEvent;
    private TableEvents addButEvent;
    private WindowAbstraction currentWindow;
    @Singular ("checkingCallBack")
    private List<DataStoreCallBack> checkingCallBack ;
    private boolean isEditable;
    private boolean isSortable;
    private String cssClass;
    @Singular("column")  private List<ColWrapper> column;
    @Singular("colWrapperPopupViaBtnButYN")  private List<ColWrapperPopupViaBtnButYN.Builder> colWrapperPopupViaBtnButYN;
    private String bigTitle;
    private String littleTitle;
    private Class aClass;
    @Singular("cssClassesStringsList") List<String> cssClassesStringsList;
    private AnchorPane parentAnchor;
    private ButSizeEnum butSizeEnum;
    private CtrlPosEnum ctrlPosEnum;
    private ButSizeForGrid buttonsForGrid;
    private CtrlPosition ctrlPosition;

    @Override
    public void configure() {
        butInit();
        rangeDirectorHandle();
        bigTitleHandle();
        createTable();
        handleEvents(buttonsForGrid);
        createGrid(buttonsForGrid, ctrlPosition);
        putObjToRegistres();
        initService();

    }

    private void butInit() {
        buttonsForGrid = bsFactory.getButSizeForGrid(butSizeEnum);
        ctrlPosition = posFactory.getCtrlPosition(ctrlPosEnum);


    }

    private void putObjToRegistres() {
        currentWindow.setNodeToMap(tableWrapper,"tableWrapper");
        if (Registry.mainWindow == currentWindow) { Registry.mainWindow.setNodeToMap(tableWrapper,"outer_table_wrapper");}
    }

    private void initService() {
        mediatorSingleTable.setTableWrapper(tableWrapper);
        mediatorSingleTable.setDataStoreCallBack(checkingCallBack);
        mediatorSingleTable.initElements();
    }

    private void createGrid(ButSizeForGrid buttonsForGrid, CtrlPosition ctrlPosition) {
        GridPaneWrapper.newBuilder()
                .setOrganization(new SingleTable( tableWrapper,buttonsForGrid ,ctrlPosition))
                .setGridName(littleTitle)
                .setParentAnchor(parentAnchor)
                .setCoordinate(coordinate)
                .setWindowAbstraction(currentWindow)
                .setGridLinesVisibility(false)
                .build();
    }

    private void handleEvents(ButSizeForGrid buttonsForGrid) {
        if (addButEvent != null) {
            addButEvent.setTableWrapper(tableWrapper);
            buttonsForGrid.setAddButEvent(((AppEvent) addButEvent));
        }
        if (delButEvent != null) {
            delButEvent.setTableWrapper(tableWrapper);
            buttonsForGrid.setDelButEvent(((AppEvent) delButEvent));
        }
    }

    private void createTable() {

        tableWrapper = TableWrapper.newBuilder()
                .setActiveRecordClass(aClass)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(isEditable)
                .setCssClass(cssClass)
                .setCssClassesStrings(cssClassesStringsList)
                .setIsSortableColums(isSortable)
                .setServiceTables(mediatorSingleTable)
                .setColWrappers(column)
                .setColWrapperPopupViaBtnButYN(colWrapperPopupViaBtnButYN)
                .build();
    }

    private void bigTitleHandle() {
        if (bigTitle != null) {
            coordinate = new Coordinate(35d, 10d, 10d, 0d);
            LabelWrapper.newBuilder()
                    .setCssClasses(CSSclasses.LABEL_COMMON)
                    .setText(bigTitle)
                    .setParentAnchor(parentAnchor)
                    .setCoordinate(new Coordinate(0d, null, -10d, 0d))
                    .setFont(FontsStore.ROBOTO_LIGHT)
                    .setAlignment(Pos.TOP_LEFT)
                    .setFontSize(30d)
                    .build();
        }
    }

    private void rangeDirectorHandle() {
        if(ctrlPosEnum== CtrlPosEnum.CTRL_POS_BUT_AND_COMBOBOX){
            rangeDirector= new RangeDirector( new ComboBox<>(), mediatorSingleTable, Range.LAST10,
                    Range.get( Range.LAST10, Range.LAST30, Range.ALLTIME )
            );
            ((CtrlPosButAndCombobox) ctrlPosition).setComboBox(rangeDirector.getComboBox());
            mediatorSingleTable.setRangeDirector(rangeDirector);
        }
}
    public  void setItems(ObservableList<ActiveRecord> list ) {
        mediatorSingleTable.setItems(list);
    }

    public  void setItems(List<ActiveRecord> recordList ) {
        ObservableList<ActiveRecord> activeRecords = FXCollections.<ActiveRecord>observableArrayList();
        activeRecords.setAll(recordList);
        mediatorSingleTable.setItems(activeRecords);
    }

}
