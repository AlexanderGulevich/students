package basisFx.appCore.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StyleHandlerXSS  implements StyleHandle{
    
    protected    XSSFCellStyle  сellStyle=null;
    protected    XSSFFont font=null;
    protected    Cell cell=null;
    protected    XSSFWorkbook workbook=null;
    protected    XSSFSheet spreadsheet=null;


    public StyleHandlerXSS(XSSFWorkbook wb, XSSFSheet spreadsheet) {
       
       this.workbook=wb;
       this.сellStyle= workbook.createCellStyle();
       this.font=workbook.createFont();
       this.spreadsheet=spreadsheet;
       this.сellStyle.setFont(font);

    }

    public StyleHandle setFontHeight(int h) {
        
         font.setFontHeightInPoints((short)h);
         return this;
        
    }
    
    public StyleHandle setFontName(String n) {
        
         font.setFontName(n);
         return this;
        
    }
    
    public StyleHandle setFontBold() {
        
         font.setBold(true);
         return this;
        
    }
    
    public StyleHandle setWrapText() {
       
             this.сellStyle.setWrapText(true);
 
         return this;
        
    }
    
    public StyleHandle setRowHeight(int h) {
       
             this.cell.getRow().setHeightInPoints(h);
 
         return this;
        
    }
    
    public StyleHandle setHorizontalAlignmentCENTER() {
        
        this.сellStyle.setAlignment(HorizontalAlignment.CENTER);
         return this;
        
    }
    public StyleHandle setHorizontalAlignmentLeft() {
        
        this.сellStyle.setAlignment(HorizontalAlignment.LEFT);
         return this;
        
    }
   
    public StyleHandle setHorizontalAlignmentRight() {
        
        this.сellStyle.setAlignment(HorizontalAlignment.RIGHT);
         return this;
        
    }
   
    public StyleHandle setVerticalAlignmentCENTER() {
        
        this.сellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
         return this;
        
    }

    
    public StyleHandle setFontItalic() {
        
         font.setItalic(true);
         return this;
        
    }
    
    public StyleHandle setFontColor(short s) {

         font.setColor(s);   
         return this;
        
    }
    
    public StyleHandle setFontColorRGB(int i, int  k, int j) {

         font.setColor(new XSSFColor(new java.awt.Color(i, k, j)));
         return this;
        
    }
    
    public StyleHandle setCellType(CellType ct) {

         cell.setCellType(ct);
         return this;
        
    }
    
    public StyleHandle setDataFormatForNumericNotNegative() {
        
        XSSFCreationHelper creationHelper = new XSSFCreationHelper(workbook);

        сellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("# ##0")); 

         return this;
        
    }
    
    public StyleHandle setDataFormatForNumericWithNegative() {
      
        DataFormat format = workbook.createDataFormat();
        
        сellStyle.setDataFormat(format.getFormat("0.00"));
        return this;
        
    }
    
    public CellStyle getСellStyle() {
               
                return сellStyle;
    }
    
    public StyleHandle setBorder(BorderStyle top, BorderStyle right,
                                     BorderStyle bottom, BorderStyle left, short s ){
        
        сellStyle.setBorderTop(top);
        сellStyle.setBorderBottom(bottom);
        сellStyle.setBorderLeft(left);
        сellStyle.setBorderRight(right);
        
        сellStyle.setBottomBorderColor(s);
        сellStyle.setTopBorderColor(s);
        сellStyle.setRightBorderColor(s);
        сellStyle.setLeftBorderColor(s);
        
    
        return this;
    
    }
    
    public StyleHandle setBorderRGB(BorderStyle top, BorderStyle right,
                                        BorderStyle bottom, BorderStyle left, XSSFColor s ){
        
        сellStyle.setBorderTop(top);
        сellStyle.setBorderBottom(bottom);
        сellStyle.setBorderLeft(left);
        сellStyle.setBorderRight(right);
        
        сellStyle.setBottomBorderColor(s);
        сellStyle.setTopBorderColor(s);
        сellStyle.setRightBorderColor(s);
        сellStyle.setLeftBorderColor(s);
        
    
        return this;
    
    }

    public StyleHandle setFillForegroundCol(short s ){
        
        сellStyle.setFillForegroundColor(s );
        

        return this;
    
    }
    public StyleHandle setFillForegroundCol(XSSFColor color ){
        
        сellStyle.setFillForegroundColor(color );
       
        return this;
    
    }
    
    public StyleHandle setFillPattern(FillPatternType f ){
        
        сellStyle.setFillPattern(f);
      
        
    
        return this;
    
    }

    public StyleHandle setFillForegroundColRGB(int red, int green, int blue) {

       сellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(red, green, blue)));
       
        
       return this;
    }
    

    
    
    
}
