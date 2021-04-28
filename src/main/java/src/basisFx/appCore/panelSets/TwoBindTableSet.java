package basisFx.appCore.panelSets;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.table.*;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Range;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.dataSource.UnitOfWork;
import basisFx.service.ServiceTablesTwoLinked;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Singular;

import java.util.List;
@Builder
public class TwoBindTableSet implements  PanelSets{
    @Builder.Default private ServiceTablesTwoLinked mediator=new ServiceTablesTwoLinked();
    private TableWrapper leftTableWrapper;
    private TableWrapper rightTableWrapper;
    private RangeDirector rangeDirector;
    @Builder.Default private UnitOfWork unitOfWork=new UnitOfWork();
    @Builder.Default private Coordinate coordinate=new Coordinate(0d, 10d, null, 0d);
    private WindowAbstraction currentWindow;
    private String cssClass;
    @Singular("leftCols")  private List<ColWrapper> leftCols;
    @Singular("rightCols")  private List<ColWrapper> rightCols;
    private String bigTitle;
    private String littleTitleLeft;
    private String littleTitleRight;
    private Class aClassLeft;
    private Class aClassRight;
    private AnchorPane parentAnchor;
    private double percentWidthLeft;
    private double percentWidthRight;

    @Override
    public void configure() {
        rangeDirector=new RangeDirector(new ComboBox<>(), mediator, Range.LAST10,Range.getAll());

        leftTableWrapper = TableWrapper.newBuilder()
                .setGridName(littleTitleLeft)
                .setOrganization(new SingleTable(new ButSizeLittle(),new CtrlPosTop()))
                .setActiveRecordClass(aClassLeft)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(true)
                .setServiceTables(mediator)
                .setColWrappers(leftCols)
                .build();

        rightTableWrapper = TableWrapper.newBuilder()
                .setGridName(littleTitleRight)
                .setOrganization( new SingleTable(new ButSizeLittle(),new CtrlPosButAndCombobox(rangeDirector.getComboBox())))
                .setActiveRecordClass(aClassRight)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(true)
                .setIsSortableColums(false)
                .setServiceTables(mediator)
                .setColWrappers(rightCols)
                .build();


        GridPaneWrapper.newBuilder()
                .setColumnVsPercent(percentWidthLeft)
                .setColumnVsPercent(percentWidthRight)
                .setGridName(bigTitle)
                .setParentAnchor(parentAnchor)
                .setCoordinate(coordinate)
                .setGridLinesVisibility(false)
                .setOrganization( new TwoHorisontalBondGrids( leftTableWrapper.getGridPaneWrapper(), rightTableWrapper.getGridPaneWrapper() ) )
                .build();

        mediator.setAccessoryTableWrapper(rightTableWrapper);
        mediator.setPrimaryTableWrapper(leftTableWrapper);
        mediator.setRangeDirector(rangeDirector);
        mediator.initElements();

    }
}
