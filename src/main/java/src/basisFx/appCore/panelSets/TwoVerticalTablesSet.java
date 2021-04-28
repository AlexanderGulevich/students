package basisFx.appCore.panelSets;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.table.*;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.dataSource.UnitOfWork;
import basisFx.service.ServiceTablesSingle;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class TwoVerticalTablesSet  implements PanelSets{
    @Builder.Default private ServiceTablesSingle serviceTop =new ServiceTablesSingle();
    @Builder.Default private ServiceTablesSingle serviceBottom = new ServiceTablesSingle();
    private TableWrapper topTableWrapper;
    private TableWrapper bottomTableWrapper;

    @Builder.Default private UnitOfWork unitOfWork=new UnitOfWork();
    @Builder.Default private Coordinate coordinate=new Coordinate(0d, 10d, null, 0d);
    private WindowAbstraction currentWindow;
    private String cssClass;
    @Singular("topCols")  private List<ColWrapper> topCols;
    @Singular("bottomCols")  private List<ColWrapper> bottomCols;
    private String bigTitle;
    private String littleTitleTop;
    private String littleTitleBottom;
    private Class aClassTop;
    private Class aClassBottom;
    private AnchorPane parentAnchor;

    @Override
    public void configure() {

        topTableWrapper = TableWrapper.newBuilder()
                .setGridLinesVisibility(false)
                .setGridName(littleTitleTop)
                .setOrganization(new SingleTable(new ButSizeLittle(), new CtrlPosTop()))
                .setActiveRecordClass(aClassTop)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(true)
                .setIsSortableColums(false)
                .setServiceTables(serviceTop)
                .setColWrappers(topCols)
                .build();


        Registry.mainWindow.setNodeToMap(topTableWrapper,"outer_table_wrapper");



        bottomTableWrapper=TableWrapper.newBuilder()
                .setGridLinesVisibility(false)
                .setGridName(littleTitleBottom)
                .setOrganization(new SingleTable(new ButSizeLittle(),new CtrlPosTop()))
                .setActiveRecordClass(aClassBottom)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(true)
                .setIsSortableColums(false)
                .setServiceTables(serviceBottom)
                .setColWrappers(bottomCols)
                .build();



        GridPaneWrapper commonGridPaneWrapper = GridPaneWrapper.newBuilder()
                .setGridLinesVisibility(false)
                .setColumnVsPercent(100)
                .setGridName(bigTitle)
                .setParentAnchor(parentAnchor)
                .setCoordinate(new Coordinate(0d, 10d, 10d, 0d))
                .setOrganization(
                        new TwoVerticaGrids(
                                new SingleTable(topTableWrapper,new ButSizeLittle(),new CtrlPosTop())  , littleTitleTop,
                                new SingleTable(bottomTableWrapper,new ButSizeLittle(),new CtrlPosTop()), littleTitleBottom
                        )
                )
                .build();

        serviceTop.setTableWrapper(topTableWrapper);
        serviceTop.initElements();

        serviceBottom.setTableWrapper(bottomTableWrapper);
        serviceBottom.initElements();
    }
}
