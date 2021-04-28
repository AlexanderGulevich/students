package basisFx.appCore.poi;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFDialogsheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StyleHandlerFabric {


    public static StyleHandle createStyleHandlerXSSF(XSSFWorkbook wb, XSSFSheet spreadsheet){
        return new StyleHandlerXSS(wb, spreadsheet);
    }
    public static StyleHandle createStyleHandlerHSSF(HSSFWorkbook wb, HSSFSheet spreadsheet){
        return new StyleHandlerHSS(wb, spreadsheet);
    }
    public static StyleHandle createStyleHandler(Workbook wb, Sheet spreadsheet){

        if((  wb instanceof  HSSFWorkbook &&    spreadsheet instanceof HSSFSheet)){

            return   createStyleHandlerHSSF(((HSSFWorkbook) wb), ((HSSFSheet) spreadsheet));
        }

        if((  wb instanceof  XSSFWorkbook &&    spreadsheet instanceof XSSFSheet)){

            return createStyleHandlerXSSF(((XSSFWorkbook) wb), ((XSSFSheet) spreadsheet));
        }

        return null;


    }



}
