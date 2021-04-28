package basisFx.appCore.panelSets;


import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.*;
import basisFx.appCore.events.AppEvent;
import basisFx.appCore.events.TableEvents;
import basisFx.appCore.grid.*;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.table.ColWrapper;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.DateGetter;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.dataSource.UnitOfWork;
import basisFx.service.ServiceTablesAutoCommitByDate;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Builder
public class AutoCommitByDateTableSet implements  PanelSets {

    @Builder.Default private ServiceTablesAutoCommitByDate mediator=new ServiceTablesAutoCommitByDate();
    @Builder.Default private TableWrapper tableWrapper=null;
    @Builder.Default private LabelWrapper label=null;
    @Singular ("checkingCallBack")
    private List<DataStoreCallBack> checkingCallBack ;
    protected CallBackParametrized<ActiveRecord> checkingTableRecordCallBack;
    @Builder.Default private UnitOfWork unitOfWork=new UnitOfWork();
    @Builder.Default private CtrlPosFactory posFactory=CtrlPosFactory.getInstance();
    @Builder.Default private ButSizeFactory bsFactory=ButSizeFactory.getInstance();
    @Builder.Default private Coordinate coordinate=new Coordinate(0d, 10d, null, 0d);
    @Builder.Default private RangeDirector rangeDirector=null;
    private DateGetter dateGetter;
    private TableEvents delButEvent;
    private TableEvents addButEvent;
    private WindowAbstraction currentWindow;
    private boolean isEditable;
    private boolean isSortable;
    private String cssClass;
    @Singular("column")  private List<ColWrapper> column;
    private String bigTitle;
    private String littleTitle;
    private Class aClass;
    private AnchorPane parentAnchor;
    private ButSizeEnum butSizeEnum;
    private CtrlPosEnum ctrlPosEnum;
    @Builder.Default private ButSizeForGrid buttonsForGrid=null;
    @Builder.Default private CtrlPosition ctrlPosition=null;

    @Override
    public void configure() {
        mediator.setDateGetter(dateGetter);
        mediator.setCheckingTableRecordCallBack(checkingTableRecordCallBack);
        butInit();
        initDateGetter();
        createLabel();
        createTable();
        handleEvents(buttonsForGrid);
        createGrid(buttonsForGrid, ctrlPosition);
        initService();
    }

    private void butInit() {
        buttonsForGrid = bsFactory.getButSizeForGrid(butSizeEnum);
        ctrlPosition = posFactory.getCtrlPosition(ctrlPosEnum);
    }

    private void initService() {
        mediator.setTableWrapper(tableWrapper);
        mediator.setDateGetter(dateGetter);
        mediator.setDataStoreCallBack(checkingCallBack);
        mediator.initElements();
    }

    private void createGrid(ButSizeForGrid buttonsForGrid, CtrlPosition ctrlPosition) {
        GridPaneWrapper.newBuilder()
                .setOrganization(new SingleTable(tableWrapper,buttonsForGrid ,ctrlPosition))
                .setGridName(littleTitle)
                .setParentAnchor(parentAnchor)
                .setCoordinate(new Coordinate(50d,10d,10d,0d))
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
                .setIsSortableColums(isSortable)
                .setServiceTables(mediator)
                .setColWrappers(column)
                .build();

    }

    private void createLabel() {
        label = LabelWrapper.newBuilder()
                .setCssClasses(CSSclasses.LABEL_COMMON)
                .setText(bigTitle)
                .setParentAnchor(parentAnchor)
                .setCoordinate(new Coordinate(0d, 300d, null, 10d))
                .setFont(FontsStore.ROBOTO_LIGHT)
                .setAlignment(Pos.TOP_LEFT)
                .setFontSize(30d)
                .build();
    }

    private void initDateGetter() {

        dateGetter.setCoordinate( new Coordinate(10d, 15d, null, null));
        dateGetter.setMediator(mediator);
        dateGetter.setParentAnchor(parentAnchor);
        dateGetter.init();
    }

}
