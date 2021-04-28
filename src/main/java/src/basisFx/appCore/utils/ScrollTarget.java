//package basisFx.presentation.dynamicContents;
//
//import basisFx.appCore.table.ColWrapper;
//import basisFx.appCore.domainScetch.ComboBoxValue;
//import basisFx.appCore.nods.AppNode;
//import basisFx.appCore.nods.TableWrapper;
//import basisFx.appCore.DynamicContentPanel;
//import basisFx.domain.domaine.Country;
//import basisFx.domain.domaine.Equipment;
//import basisFx.appCore.settings.CSSid;
//import javafx.scene.layout.AnchorPane;
//
///**
// * Created by AlexanderGulevich on 18.03.2018.
// *
// * @autor AlexanderGulevich
// */
//public class ScrollTarget extends DynamicContentPanel {
//
//
//
//
//    @Override
//    protected void configurate() {
//
//
//     AppNode.NodeBuilder.configure()
//             .setId(CSSid.TILE_PANE).setParent(panel)
//             .setCoordinate(80d , 0d, 0d, 0d)
//             .createTilePaneWrapper()
//             .setChildrenAnchorSize(0.3d,30d)
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//             .childGenerator(pane -> {setCountryTable(pane);})
//                .getElement();
//
//
//    }
//
//
//
//    private void setCountryTable(AnchorPane pane){
//
//        TableWrapper countryTableWrapper = AppNode.NodeBuilder.configure()
//                .setId(CSSid.TABLE).setCoordinate(pane,50d, 40d, 10d, 10d)
//                .<Equipment>createTableViewWrapper().setTablesWidthProperty(0.4, pane.widthProperty()).setTablesHeight(200d)
//                .setActiveRecord(this.dataMapperFabric.getNamedDataMapper())
//                .setDbTableName("Country").refresh()
//                .setColums(
//                        columnFabric.<Country,DefaultPanelsNames>stringCol(ColWrapper.Bulder.configure()
//                                        .setColumnName("Наименование")
//                                        .setBindPropertyName("name")
//                                        .setValueChecking(check.createTextCheck())
//                                        .setColumnSize(1)
//                                        .setDomainChanging(
//                                                (obj,val)->{((ComboBoxValue)obj).setRate((DefaultPanelsNames)val);}
//                                        )
//                        )
//                );
//
//        buttonFactory
//                .littleRowAddButton(countryTableWrapper,pane,Country.class,35d,5d,null,null);
//        buttonFactory
//                .littleRowDeleteButton(countryTableWrapper,pane,70d,5d,null,null);
//
//
//
//    }
//
//
//
//
//
//}
