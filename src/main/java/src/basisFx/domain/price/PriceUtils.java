package basisFx.domain.price;

import basisFx.appCore.poi.StringHandler;
import basisFx.appCore.utils.Registry;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PriceUtils {
    public enum Message {
        NOT_READ("Не получилось прочитать заказ: "),
        BRACKET("Скобка на закрыта: "),
//        SUM("СУММА ПО ТЕКУЩИМ СОСТАТКАМ СОСТАВЛЯЕТ : "),
        BARCODE_NON("Отсутствует штрихкод : "),
        BARCODE_13("Шрихкод нижеследующего товара содержит  менее 13 символов: ");
        private final String mess;
        private Message(String mess) {
            this.mess = mess;
        }
        public String get() {
            return mess;
        }
    }
    @Getter
    HashMap<Message,ArrayList<String>> messageMap =new HashMap<>();

    protected StringHandler strHandler=new StringHandler();

    public PriceUtils() {
        messageMap.put(Message.NOT_READ,new ArrayList<>());
        messageMap.put(Message.BARCODE_13,new ArrayList<>());
        messageMap.put(Message.BRACKET,new ArrayList<>());
//        messageMap.put(Message.SUM,new ArrayList<>());
        messageMap.put(Message.BARCODE_NON,new ArrayList<>());
    }
    public Boolean isCategory(Cell c ) {
     String value=null;
            if (!(c==null)){
//                 if (!(c.getCellTypeEnum()==c.getCellTypeEnum().BLANK)){
                 if (!(c.getCellType()== CellType.BLANK)){
                        if (c.getCellType()==CellType.STRING){
//                        if (c.getCellTypeEnum()==c.getCellTypeEnum().STRING){
                            value=c.getStringCellValue();
                                if (value.contains("готовая продукция")&&
                                        (!(value.contains("Итого по группе:")))){
                                     return true;
                                  }else{ return false;}
                        }else{ return false;}
                }else{ return false;}
            }else{ return false;}
    }
     public Boolean isFild(Cell c){
            if (!(c==null)){
                 if (!(c.getCellTypeEnum()==c.getCellTypeEnum().BLANK)){//не пустая ячейка
                        if (c.getCellTypeEnum()==c.getCellTypeEnum().STRING){//соит строку
                            return true;
                        }else{ return false;}
                }else{ return false;}
            }else{ return false;}
     }

     public Boolean isDate(Cell c){
            String value;
             if (!(c==null)){
                 if (!(c.getCellTypeEnum()==c.getCellTypeEnum().BLANK)){
                        if (c.getCellTypeEnum()==c.getCellTypeEnum().STRING){
                            value=c.getStringCellValue();
                            if (value.contains("по подразделениям")) {
                                 return true;
                            } else{ return false;}
                        }else{ return false;}
                }else{ return false;}
            }else{ return false;}
     }
     
     public Boolean isEnd(Cell c){
            String value;
             if (!(c==null)){
                 if (!(c.getCellTypeEnum()==c.getCellTypeEnum().BLANK)){
                        if (c.getCellTypeEnum()==c.getCellTypeEnum().STRING){
                            value=c.getStringCellValue();
                            if (value.contains("ИТОГО ПО ПРЕДПРИЯТИЮ")) {
                                 return true;
                            } else{ return false;}
                        }else{ return false;}
                }else{ return false;}
            }else{ return false;}
     }

     public String readCategoryName(Cell c){
          String value= c.getStringCellValue();
                                if (value.contains("готовая продукция")&&
                                        (!(value.contains("Итого по группе:")))){
                                     value= strHandler.delText(value, "готовая продукция,");
                                     value= strHandler.delText(value, "готовая продукция");
                                     value= strHandler.delDimension(value, "(", ")");
                                     value= strHandler.delDimension(value, "(", ")");
                                     return value;
                                  }else{ return "не найдено";}
     }

     public String readProdactName(Cell c,String barcode){
         String value= c.getStringCellValue();
         if (barcode != null) value=strHandler.delText(value,barcode);
         try {
             value=value.substring( value.indexOf("-")+1);
             value=value.substring(value.indexOf(" "));

         } catch (Exception e) {
             Registry.windowFabric.infoWindow("Возникла проблемма в этом месте: \n" + value);
         }

         String amountInbox = readAmountInbox(c);
         value=strHandler.delText(value,"("+amountInbox+")");
         value=strHandler.delText(value,"( "+amountInbox+")");
         value=strHandler.delText(value,"( "+amountInbox+" )");
         value=strHandler.delText(value,"("+amountInbox+" )");

         value=strHandler.clearEdges(value);
         return value;
     }

     public Boolean isEndOfCategory(Cell c){
         String value=null;
            if (!(c==null)){
                 if (!(c.getCellTypeEnum()==c.getCellTypeEnum().BLANK)){
                        if (c.getCellTypeEnum()==c.getCellTypeEnum().STRING){
                            value=c.getStringCellValue();
                                if (value.contains("Итого по группе: готовая продукция")){
                                     return true;
                                  }else{ return false;}
                        }else{ return false;}
                }else{ return false;}
            }else{ return false;}
     }

     public String readMeasure(Cell c){
            String value=c.getStringCellValue();
            value= strHandler.clearEdges(value);
            return value;
            
     }

     public Double readAmount(Cell c){
            Double value=null;
            value=c.getNumericCellValue();
            return value;
     }

     public Double readPricePerUnit(Cell c){
            
            Double value=null;
            value=c.getNumericCellValue();
            return value;
     }

     public String readDate(Sheet sheet){
        
        Row dateRow =sheet.getRow(3);
        Cell dateCell=dateRow.getCell(0);
        
        String dateCellString=dateCell.getStringCellValue();
        dateCellString =strHandler.delText(dateCellString, "по подразделениям на");
        dateCellString =strHandler.delText(dateCellString, "г.");
        dateCellString =dateCellString.toLowerCase();
        dateCellString =strHandler.clearEdges(dateCellString);
        
       
         if(dateCellString.contains("января")){   
             dateCellString=strHandler.changeWord(dateCellString, "января", "01") ;
                     }
         if(dateCellString.contains("февраля")){   
             dateCellString=strHandler.changeWord(dateCellString, "февраля", "02") ;
                     }
         if(dateCellString.contains("марта")){   
             dateCellString=strHandler.changeWord(dateCellString, "марта", "03") ;
                     }
         if(dateCellString.contains("апреля")){   
             dateCellString=strHandler.changeWord(dateCellString, "апреля", "04") ;
                     }
         if(dateCellString.contains("мая")){   
             dateCellString=strHandler.changeWord(dateCellString, "мая", "05") ;
                     }
         if(dateCellString.contains("июня")){   
             dateCellString=strHandler.changeWord(dateCellString, "июня", "06") ;
                     }
         if(dateCellString.contains("июля")){   
             dateCellString=strHandler.changeWord(dateCellString, "июля", "07") ;
                     }
         if(dateCellString.contains("августа")){   
             dateCellString=strHandler.changeWord(dateCellString, "августа", "08") ;
                     }
         if(dateCellString.contains("сентября")){   
             dateCellString=strHandler.changeWord(dateCellString, "сентября", "09") ;
                     }
         if(dateCellString.contains("октября")){   
             dateCellString=strHandler.changeWord(dateCellString, "октября", "10") ;
                     }
         if(dateCellString.contains("ноября")){   
             dateCellString=strHandler.changeWord(dateCellString, "ноября", "11") ;
                     }
         if(dateCellString.contains("декарбя")){   
             dateCellString=strHandler.changeWord(dateCellString, "декарбя", "12") ;
                     }
        
               
        dateCellString=strHandler.changeWord(dateCellString, " ", ".");
        dateCellString=strHandler.changeWord(dateCellString, " ", ".");
        
        return dateCellString;
        
        
        
    
    }

     public Double readTotalSumma(Cell c){
            Double value= c.getNumericCellValue();
            return value;
     }
     
     public String readOrderString(Cell c){
            String value=null;
            value=c.getStringCellValue();
            value= value.substring(0, 15);
         value= strHandler.delText(value, "з.");
         value= strHandler.delText(value, "з");
            if(  value.indexOf("-") <0){
                messageMap.get(Message.NOT_READ).add("---"+value);
            }else {
                value= value.substring(0, value.indexOf("-")+5);
                value=value.substring(0, value.indexOf(" "));
                value= strHandler.clearEdges(value);
                return value;
            }
            return null;
     }

     public String readPureOrder(Cell c){
         String value=c.getStringCellValue().substring(0, 15);
         value= strHandler.delText(value, "з.");
         value= strHandler.delText(value, "з");
         value= strHandler.delAllSpace(value);
         value= strHandler.clearEdges(value);
         int index = value.indexOf("-");

         if(  index <0){
             messageMap.get(Message.NOT_READ).add("---"+value);
             return null;
            }else {

             String left="";
             String right="";
             int length = value.length();
             int i = index-1;
             for (;i>=0 ; i--) {
                     try {
                     char a_char = value.charAt(i);
                     String s = String.valueOf(a_char);
                     Integer integer = Integer.valueOf(s);
                     left+=s;
                 } catch (Exception e) {
                     break;
                 }
             }
             int k = index+1;
             for (; k< length; k++) {
                 try {
                     char a_char = value.charAt(k);
                     String s = String.valueOf(a_char);
                     Integer integer = Integer.valueOf(s);
                     right+=s;
                 } catch (Exception e) {
                     break;
                 }
             }

            String reverse_left = new StringBuffer(left).reverse().toString();
             String result = reverse_left + right;
             return result;
         }

     }

     public String readBarcode(Cell c)  {
        String value=null;
        value=c.getStringCellValue();
        value= strHandler.clearEdges(value);
        char[] charArray = value.toCharArray();

        List<Character> chars = new ArrayList<>();
         for (int i = 0; i < charArray.length; i++) {
             chars.add(charArray[i]);
         }

         String barcode=new String();
         int barcodeLenth=0;

         for (Character aChar : chars) {
             try {
                 Integer integer = Integer.valueOf(aChar.toString());
                 String current=aChar.toString();
                 barcodeLenth += 1;
                 barcode+=integer.toString();
                 if (barcodeLenth==13) return barcode;
             } catch (NumberFormatException e) {
                 if (barcodeLenth==12 || barcodeLenth==11 || barcodeLenth==10 ) {
                     messageMap.get(Message.BARCODE_13).add("---"+value);
                 }
                 barcodeLenth=0;
                 barcode="";
                continue;
             }
         }
         messageMap.get(Message.BARCODE_NON).add("---"+c.getStringCellValue());
         return null;
     }
     
     public String readAmountInbox(Cell c){
         String value= c.getStringCellValue();
         int length = value.length();
         value= value.substring(length/2);
         String finded= null;
         if (value.contains("(")) {

             try {
                 finded = value.substring( value.lastIndexOf("(")+1, value.lastIndexOf(")"));
             } catch (Exception e) {
                 messageMap.get(Message.BRACKET).add("---"+c.getStringCellValue());
             }
                finded= strHandler.clearEdges(finded);
             try {
                 Integer.valueOf(finded);
             } catch (NumberFormatException e) {
                 finded=null;
             }

         }else{
             finded=null;
            }
            return finded;
     }
     
     
     public Integer readOrderNumber(String orderString){
         
          String leftValue=orderString.substring(0,
                  orderString.indexOf("-")
          
          );
       

         return Integer.parseInt(leftValue);

     }
     
     public Integer  readOrderYear(String orderString){
         
           String rightValue=orderString.substring(
                   orderString.indexOf("-")+1,
                   orderString.length()
          );

         return Integer.parseInt(rightValue);

     }


}
