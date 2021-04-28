package basisFx.domain.price;

import basisFx.appCore.poi.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static basisFx.appCore.poi.CellStylesStore.StyleKind.*;

public class WriterForPrice extends Writer {
    protected StringHandler strHandler;
    protected HSSFWorkbook workbook;
    protected FileOutputStream out;
    protected HSSFRow row;
    protected HSSFSheet spreadsheet;
    protected Integer numRows = 0;
//    protected      StringHandler strHandler;
//    protected Workbook workbook ;
//    protected      FileOutputStream out;
//    protected Row row;
//    protected Sheet spreadsheet;
//    protected      Integer numRows=0;

    private Price price;
    private String path;
    private ArrayList categoriesArrayList;
    int firstColIndex = 1;
    int last = 9;
    private CellHandler cellHandler;

    boolean bigImgSize ;
    boolean imgExistatnt ;



    public WriterForPrice(Price pr, String path, boolean bigImgSize, boolean imgExistatnt) {
        this.price = pr;
        this.path = path;
        this.categoriesArrayList = pr.getCategoriesArrayList();

        this.bigImgSize=bigImgSize;
        this.imgExistatnt=imgExistatnt;

        strHandler = new StringHandler();
        workbook = new HSSFWorkbook();
        spreadsheet = workbook.createSheet("Складские остатки");

        cellHandler = new CellHandler(workbook, spreadsheet);

        setPrintSetup();

        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    protected void setColumnWidth(int... vars) {

        int columnNum = 0;

        for (int var : vars) {
            spreadsheet.setColumnWidth(columnNum, 256 * var); //   1/256 символа
            columnNum += 1;

        }


    }

    protected void setPrintSetup() {
        PrintSetup printSetup = spreadsheet.getPrintSetup();
        printSetup.setScale((short) 85);
        printSetup.setLandscape(true);
        spreadsheet.setMargin(Sheet.TopMargin, 0.1);
        spreadsheet.setMargin(Sheet.RightMargin, 0.1);
        spreadsheet.setMargin(Sheet.BottomMargin, 0.1);
        spreadsheet.setMargin(Sheet.BottomMargin, 0.1);

        spreadsheet.setMargin(Sheet.HeaderMargin, 0.25);
        spreadsheet.setMargin(Sheet.FooterMargin, 0.25);


//             spreadsheet.setAutobreaks(true);
//             spreadsheet.setFitToPage(true);
//             spreadsheet.setPrintGridlines(true);

    }

    ;


    @Override
    protected void createFile() throws IOException {

//         out = new FileOutputStream(new File(this.path+"\\Прайс "+this.price.getPriceDateString()+".xlsx"));
        out = new FileOutputStream(new File(this.path + "\\Прайс " + this.price.getPriceDateString() + ".xls"));

        createHeader();
        createTableHead();
        createPermanentData();

        setColumnWidth(2, 8, 15, 23, 45, 10, 10, 9, 14, 20);

        workbook.write(out);
        out.close();
        workbook.close();

    }


    private void createHeader() {
        createHollowMergedRow();
        createNewRow();

        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue("РУП \"Бобруйская укрупненная типография им.А.Т.Непогодина\"  ")
                .setRowHeight(row, 30)
                .setCellStyle(TITLE_16_BOLD_vCENTR_text)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, last - 3);

        cellHandler.setCell(row.createCell(last - 2))
                .setCellValue("     КОНТАКТЫ ТИПОГРАФИИ")
                .addMergedRegion(row.getRowNum(), row.getRowNum(), last - 2, last)
                .setCellStyle(TITLE_11_BOLD_GREY80_vCENTR_text);

        createNewRow();

        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue("Ведомость наличия товара на " + price.getPriceDateString() + " г.")
                .setRowHeight(row, 22)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, last - 3)
                .setCellStyle(Standart12);


        cellHandler.setCell(row.createCell(last - 2))
                .setRowHeight(row, 22)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), last - 2, last)
                .setCellValue("     e-mail:        hepogodin@mail.ru")
                .setCellStyle(Standart11);

        createNewRow();

        cellHandler.setCell(row.createCell(firstColIndex))
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, last - 3);


        cellHandler.setCell(row.createCell(last - 2))
                .setCellValue("     тел.:             8 0225 72 25 16")
                .setRowHeight(row, 22)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), last - 2, last)
                .setCellStyle(Standart11);


        createNewRow();


        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue("Наименование заказчика :  ")
                .setRowHeight(row, 50)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, firstColIndex + 2)
                .setCellStyle(TITLE_14_BOLD_GREY80_vCENTR_text);

        cellHandler.setCell(row.createCell(firstColIndex + 3))
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex + 3, last)
                .multipleSetStyle(row, firstColIndex + 3, last, Huge22BoldGrey50Text);

        createHollowMergedRow();

    }

    private void createNewRow() {
        row = spreadsheet.createRow(numRows++);
    }

    private void createHollowMergedRow() {
        createNewRow();
        cellHandler.setCell(row.createCell(firstColIndex)).addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, last);
    }

    private void createTableHead() {

        createNewRow();
        row.setHeightInPoints(40);

        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue("№")
                .setCellStyle(ORANGE_FILLED_CELL_HUGE_16_WHITE_TEXT);

        cellHandler.setCell(row.createCell(firstColIndex + 1))
                .setCellValue("Шрихкод и КАРТИНКА при наведении")
                .setCellStyle(ORANGE_FILLED_CELL_Little_9_Blue_TEXT);

        cellHandler.setCell(row.createCell(firstColIndex + 2))
                .setCellValue("Наименование продукции")
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex + 2, firstColIndex + 3)
                .multipleSetStyle(row, firstColIndex + 2, firstColIndex + 3, TABLEHEAD_18_Bold_80Grey);

        cellHandler.setCell(row.createCell(firstColIndex + 4))
                .setCellValue("Ед. Изм.")
                .setCellStyle(TABLEHEAD);

        cellHandler.setCell(row.createCell(firstColIndex + 5))
                .setCellValue("Кол-во в упаковке")
                .setCellStyle(TABLEHEAD);

        cellHandler.setCell(row.createCell(firstColIndex + 6))
                .setCellValue("Цена б/НДС")
                .setCellStyle(TABLEHEAD);


        cellHandler.setCell(row.createCell(firstColIndex + 7))
                .setCellValue("Количество на складе")
                .setCellStyle(TABLEHEAD);


        cellHandler.setCell(row.createCell(firstColIndex + 8))
                .setCellValue("Заявка")
                .setCellStyle(TABLEHEAD);

    }

    private void createPermanentData() {

        createNewRow();

        for (Iterator it = categoriesArrayList.iterator(); it.hasNext(); ) {
            PriceCategory pc = (PriceCategory) it.next();

            createCategory(firstColIndex, "                * " + pc.getName().toUpperCase(), last, TABLECATEGORY);

            row.setHeightInPoints(15);
            createNewRow();

            ArrayList<PriceItem> priceItems = pc.getFilds();
            List<PriceItem> collect = priceItems.stream().sorted(Comparator.comparing(priceItem -> priceItem.toString())).collect(Collectors.toList());
            priceItems.clear();
            priceItems.addAll(collect);

            for (PriceItem filds : priceItems) {
                if (filds.getVisibitity().getBoolean()) {
                    row.setHeightInPoints(15);
                    createOrder(filds);
                    createBarcode(filds);
                    createName(filds);
                    createMeasure(filds);
                    createAmountInBox(filds);
                    createPricePerUnit(filds);
                    createAmountInPrice(filds);
                    createBlankLastCol();
                    createNewRow();
                }
            }
        }
    }

    private void createOrder(PriceItem filds) {
        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue(filds.getOrderNumber())
                .setCellStyle(PERMANENTDATASTRING);
    }

    private void createBlankLastCol() {
        cellHandler.setCell(row.createCell(firstColIndex + 8))
                .setCellStyle(PERMANENTDANUMERIC_NotNegative);
    }

    private void createAmountInPrice(PriceItem filds) {
        cellHandler.setCell(row.createCell(firstColIndex + 7))
                .setCellStyle(PERMANENTDANUMERIC_NotNegative)
                .setCellValue(
                        new BigDecimal(
                                String.valueOf(filds.getAmountInPrice())
                        ).setScale(2, RoundingMode.CEILING).doubleValue()

                );
    }

    private void createPricePerUnit(PriceItem filds) {
        cellHandler.setCell(row.createCell(firstColIndex + 6))
                .setCellStyle(PERMANENTDANUMERIC_withNehative)
                .setCellValue(
                        new BigDecimal(
                                String.valueOf(filds.getPricePerUnit())
                        ).setScale(2, RoundingMode.CEILING).doubleValue()

                );
    }

    private void createAmountInBox(PriceItem filds) {
        String amountInBox = filds.getAmountInBox();
        if (amountInBox != null) {
            cellHandler.setCell(row.createCell(firstColIndex + 5))
                    .setCellValue(Double.parseDouble(amountInBox))
                    .setCellStyle(PERMANENTDATASTRING);
        } else {
            cellHandler.setCell(row.createCell(firstColIndex + 5))
                    .setCellValue("---")
                    .setCellStyle(CELL_BORDERED_STRING_10_CENTER);
        }
    }

    private void createName(PriceItem filds) {

        String name;
        if (filds.getAlias() != null) {
            name=filds.getAlias();
        }else {
            name=filds.getName();
        }

        cellHandler.setCell(row.createCell(firstColIndex + 2))
                .setCellValue("  " + name)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex + 2, firstColIndex + 3)
                .multipleSetStyle(row, firstColIndex + 2, firstColIndex + 4, PERMANENTDATASTRING);
    }

    private void createMeasure(PriceItem filds) {
        cellHandler.setCell(row.createCell(firstColIndex + 4))
                .setCellValue("  " + filds.getMeasure())
                .setCellStyle(PERMANENTDATASTRING);
    }

    private void createBarcode(PriceItem filds) {

        String barcode = filds.getBarcode();
        if (barcode != null) {

            if (imgExistatnt && filds.getImg() != null && filds.getImg().getImgBig()!=null&& filds.getImg().getImgSmall()!=null) {
                cellHandler.setCell(row.createCell(firstColIndex + 1))
                        .setCellValue("  " + filds.getBarcode())
                        .setCellStyle(ORANGE_FILLED_CELL_9_Brown_TEXT);
                try {
                    if (imgExistatnt) {
                        try {
                                CommentUtils.setComment(workbook, spreadsheet, cellHandler.getCell(), filds,bigImgSize);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                cellHandler.setCell(row.createCell(firstColIndex + 1))
                        .setCellValue("  " + filds.getBarcode())
                        .setCellStyle(NOT_FILLED_CELL_9_BLUE_TEXT);
            }

        } else {

            cellHandler.setCell(row.createCell(firstColIndex + 1))
                    .setCellValue("---")
                    .setCellStyle(CELL_BORDERED_STRING_10_CENTER);
        }
    }

    private void createCategory(int firstColIndex, String s, int last, CellStylesStore.StyleKind tablecategory) {
        cellHandler.setCell(row.createCell(firstColIndex))
                .setCellValue(s)
                .addMergedRegion(row.getRowNum(), row.getRowNum(), firstColIndex, last)
                .multipleSetStyle(row, firstColIndex, last, tablecategory);
    }


}
