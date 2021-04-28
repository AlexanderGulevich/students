package basisFx.domain.price;

import java.io.IOException;
import java.util.Iterator;

import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.appCore.poi.Reader;
import basisFx.appCore.utils.Registry;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class PriceReader extends Reader {

    
    private PriceUtils priceUtils
            =new PriceUtils();
    @Getter
    private Price price=new Price();

    public  PriceReader(String path) {
        try {
            openWorkBook(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        rowIteration();

        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Registry.dataExchanger.put("price",price);


    }
    public PriceReader(File file)  {
        try {
            openWorkBook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        rowIteration();

        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Registry.dataExchanger.put("price",price);
    }
    
       @Override
       protected void rowIteration() {
           sheet=wb.getSheetAt(4);
           price.setPriceDateString(
                   priceUtils.readDate(sheet)
           );


           Iterator<Row> rowIterator = sheet.iterator();
               while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        if (priceUtils.isCategory(row.getCell(1))) {
                            String categoryPriceName= priceUtils.readCategoryName(row.getCell(1));
                            try {
                                price.createCategory(categoryPriceName, rowHandler(row,rowIterator));
                            } catch (Exception ex) {
                                Logger.getLogger(PriceReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                       }
                        if (priceUtils.isEnd(row.getCell(0))) {
                            Double summa = priceUtils.readTotalSumma(row.getCell(13));
                            price.setTotalSumma(summa);
//                            priceUtils.getMessageMap().get(PriceUtils.Message.SUM).add("---"+summa.toString() + "  руб.");
                            break;
                       }
       }


           Registry.dataExchanger.put("PriceMessage", priceUtils.getMessageMap()   )  ;


       }
       
       private   ArrayList<PriceItem> rowHandler(Row row, Iterator<Row> rowIterator){
           
            ArrayList<PriceItem> products=new ArrayList<>();

             while (! priceUtils.isEndOfCategory(row.getCell(1))) {
             row = rowIterator.next();

                     if (priceUtils.isFild(row.getCell(2))) {

                         String barcode = priceUtils.readBarcode(row.getCell(2));

                         PriceItem product=new PriceItem();

                         product.setOrderNumber(priceUtils.readOrderString(row.getCell(2)));
                         product.setPure_order(priceUtils.readPureOrder(row.getCell(2)));
                         product.setName(priceUtils.readProdactName(row.getCell(2),barcode));
                         product.setBarcode(barcode);
                         product.setAmountInBox(priceUtils.readAmountInbox(row.getCell(2)));
                         product.setAmountInPrice(priceUtils.readAmount(row.getCell(8)));
                         product.setMeasure(priceUtils.readMeasure(row.getCell(6)));
                         product.setPricePerUnit(priceUtils.readPricePerUnit(row.getCell(10)));

                         product.setImg( Img.getINSTANCE().find(product) );
                         product.setStoredCategory(product.findStoredCategory() );
                         product.setAlias(product.findAlias() );
                         product.setVisibitity(new BoolComboBox(product.findVisibility()));

                         products.add(product);
                    }
             }
             return  products;
       }


    }



