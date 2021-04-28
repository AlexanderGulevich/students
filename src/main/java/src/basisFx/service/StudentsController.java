package basisFx.service;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.CloseMainWindow;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.grid.ButSizeEnum;
import basisFx.appCore.grid.CtrlPosEnum;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.students.Faculty;
import basisFx.domain.students.Male;
import basisFx.domain.students.Student;
import basisFx.domain.students.StudentsGroup;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.atomic.AtomicReference;

public class StudentsController extends WindowService {
    @FXML private AnchorPane head;
    @FXML private Button close;
    @FXML private AnchorPane contentPanelForTable;
    public static Faculty faculty;
    
    SingleTableSet singleTableSet;


    public StudentsController() {
        {
            Registry.crossWindowMediators.put("students",this);
        }
    }

    @Override
    public void inform(Object node) {

    }

    @Override
    public void init() {

        new StageDragging().setEventToElement(head);
        new CloseMainWindow().setEventToElement(close,Registry.mainWindow.getStage());
//        new MaximazingSwither().setEventToElement(head,Registry.mainWindow.getStage());
        createTable();
    }




    private void createTable() {

        singleTableSet = SingleTableSet.builder()
                .aClass(Student.class)
                .parentAnchor(contentPanelForTable)
                .cssClassesStringsList("table_font_size_12").cssClassesStringsList("wrappedHeaderColumn_font_size_14")
                .isSortable(false).isEditable(true)
                .currentWindow(currentWindow)
                .bigTitle(null).littleTitle("Информация по студентам")
                .coordinate(new Coordinate(30d, 30d, 30d, 30d))
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP).butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(null).delButEvent(null)
                .column(ColumnFabric.stringCol("ФИО", "name", 0.25d, true))
                .column(ColumnFabric.dateCol("ДР", "birthday", 0.1d, true))
                .column(ColumnFabric.comboBoxCol( Male.class,"Пол ","male",0.05d,true))
                .column(ColumnFabric.popup( "Группа", "studentsGroup",  0.1d,   getWindowBuilderGroup()       ))
                .column(ColumnFabric.bindCol( "Факультет", 0.2d,     r -> {
                    Student var = (Student) r;
                    if (!ActiveRecord.isNewDomane(var)) {
                        return  var .getStudentsGroup().facultyProperty();
                    }else {
                        var.studentsGroupProperty().addListener((observable, oldValue, newValue) ->
                                {
                                    var.setFaculty(newValue.getFaculty());
                                }
                                ); ;

                        return var.facultyProperty();

                    }



                }       ))

                .column(ColumnFabric.stringCol("Предприятие", "enterprise", 0.1d, true))
                .column(ColumnFabric.stringCol("Город", "city", 0.1d, true))
                .column(ColumnFabric.popupViaBtnCol( "Факультеты", "список",  0.1d,   getWindowBuilderFaculty()       ))
                .build();

        singleTableSet.configure();

//        if (bindRecordsHasChanged) {
//            singleTableSet.setItems(PriceItem.getINSTANCE().getAllFullData());
//            bindRecordsHasChanged=false;
//        }


        TableWrapper tableWrapper = singleTableSet.getMediatorSingleTable().getTableWrapper();
        Registry.dataExchanger.put("outer_table_wrapper",tableWrapper);






    }

    private WindowBuilder getWindowBuilderGroup() {
        WindowBuilder faculty = WindowBuilder.newBuilder()
                .setTitle("Группы").setMessage(null).setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d).setHeight(600d)
                .setPreClosingCallBack(() -> {
//                            TableWrapper tableWrapper =(TableWrapper)   Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
//                            tableWrapper.getMediator().refresh(tableWrapper);
                        })
                .setPanelCreator( ()->new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        SingleTableSet.builder()
                                .aClass(StudentsGroup.class)
                                .isSortable(false) .isEditable(true)
                                .currentWindow(window)
                                .bigTitle("")
                                .littleTitle("Введите наименование группы, факультета")
                                .parentAnchor(dynamicContentAnchorHolder)
                                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_MIDDLE)
                                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
                                .addButEvent(null)
                                .delButEvent(null)
                                .column(ColumnFabric.stringCol("Наименование", "name", 0.3d, true))
                                .column(ColumnFabric.comboBoxCol(  Faculty.class,"Факультет ","faculty",0.7d,true))
                                .build().configure();

                    }
                } )


                .build();
        return faculty;
    }

    private WindowBuilder getWindowBuilderFaculty() {
        WindowBuilder faculty = WindowBuilder.newBuilder()
                .setTitle("Факультеты").setMessage(null).setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d).setHeight(600d)
                .setPreClosingCallBack(() -> {
                            TableWrapper tableWrapper =(TableWrapper)   Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
                            tableWrapper.getMediator().refresh(tableWrapper);
                        })
                .setPanelCreator( ()->new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        SingleTableSet.builder()
                                .aClass(Faculty.class)
                                .isSortable(false) .isEditable(true)
                                .currentWindow(window)
                                .bigTitle(" ")
                                .littleTitle("Введите наименование факультета")
                                .parentAnchor(dynamicContentAnchorHolder)
                                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_MIDDLE)
                                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
                                .addButEvent(null)
                                .delButEvent(null)
                                .column(ColumnFabric.stringCol("Наименование", "name", 1d, true))
                                .build().configure();

                    }
                } )


                .build();
        return faculty;
    }


}
