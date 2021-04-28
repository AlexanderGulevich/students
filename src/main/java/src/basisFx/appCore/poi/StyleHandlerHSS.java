package basisFx.appCore.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class StyleHandlerHSS implements StyleHandle {

    protected HSSFCellStyle сellStyle=null;
    protected HSSFFont font=null;
    protected Cell cell=null;
    protected HSSFWorkbook workbook=null;
    protected HSSFSheet spreadsheet=null;



    public StyleHandlerHSS(HSSFWorkbook wb, HSSFSheet spreadsheet) {
       
       this.workbook=wb;
       this.сellStyle= workbook.createCellStyle();
       this.font=workbook.createFont();
       this.spreadsheet=spreadsheet;
       this.сellStyle.setFont(font);

    }

    @Override
    public StyleHandle setFontHeight(int h) {
        
         font.setFontHeightInPoints((short)h);
         return this;
        
    }
    
    @Override
    public StyleHandle setFontName(String n) {
        
         font.setFontName(n);
         return this;
        
    }
    
    @Override
    public StyleHandle setFontBold() {
        
         font.setBold(true);
         return this;
        
    }
    
    @Override
    public StyleHandle setWrapText() {
       
          сellStyle.setWrapText(true);
 
         return this;
        
    }
    
    @Override
    public StyleHandle setRowHeight(int h) {
       
           cell.getRow().setHeightInPoints(h);
 
         return this;
        
    }
    
    @Override
    public StyleHandle setHorizontalAlignmentCENTER() {
        
        сellStyle.setAlignment(HorizontalAlignment.CENTER);
         return this;
        
    }
    @Override
    public StyleHandle setHorizontalAlignmentLeft() {
        
        сellStyle.setAlignment(HorizontalAlignment.LEFT);
         return this;
        
    }
   
    @Override
    public StyleHandle setHorizontalAlignmentRight() {
        
        сellStyle.setAlignment(HorizontalAlignment.RIGHT);
         return this;
        
    }
   
    @Override
    public StyleHandle setVerticalAlignmentCENTER() {
        
        сellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
         return this;
        
    }

    
    @Override
    public StyleHandle setFontItalic() {
        
         font.setItalic(true);
         return this;
        
    }
    
    @Override
    public StyleHandle setFontColor(short s) {

         font.setColor(s);   
         return this;
        
    }
    
    @Override
    public StyleHandle setFontColorRGB(int red, int green, int blue) {

        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(red, green,blue);
        short palIndex = myColor.getIndex();
        font.setColor(palIndex);
        return this;
        
    }
    
    @Override
    public StyleHandle setCellType(CellType ct) {
         cell.setCellType(ct);
         return this;
    }
    
    @Override
    public StyleHandle setDataFormatForNumericNotNegative() {
        HSSFCreationHelper creationHelper =workbook.getCreationHelper();
        сellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("# ##0"));
         return this;
    }
    
    @Override
    public StyleHandle setDataFormatForNumericWithNegative() {
        DataFormat format = workbook.createDataFormat();
        сellStyle.setDataFormat(format.getFormat("0.00"));
        return this;
        
    }
    
    @Override
    public CellStyle getСellStyle() {
                return сellStyle;
    }
    
    @Override
    public StyleHandle setBorder(BorderStyle top, BorderStyle right,
                                  BorderStyle bottom, BorderStyle left, short s){
        
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
    
    @Override
    public StyleHandle setBorderRGB(BorderStyle top, BorderStyle right,
                                     BorderStyle bottom, BorderStyle left, XSSFColor s){
        
        сellStyle.setBorderTop(top);
        сellStyle.setBorderBottom(bottom);
        сellStyle.setBorderLeft(left);
        сellStyle.setBorderRight(right);
        
        сellStyle.setBottomBorderColor(s.getIndex());
        сellStyle.setTopBorderColor(s.getIndex());
        сellStyle.setRightBorderColor(s.getIndex());
        сellStyle.setLeftBorderColor(s.getIndex());
        
    
        return this;
    
    }

    @Override
    public StyleHandle setFillForegroundCol(short s){
        
        сellStyle.setFillForegroundColor(s );
        

        return this;
    
    }
    @Override
    public StyleHandle setFillForegroundCol(XSSFColor color){
        
        сellStyle.setFillForegroundColor(color.getIndex() );
       
        return this;
    
    }
    
    @Override
    public StyleHandle setFillPattern(FillPatternType f){
        
        сellStyle.setFillPattern(f);
      
        
    
        return this;
    
    }

    @Override
    public StyleHandle setFillForegroundColRGB(int red, int green, int blue) {


        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(red, green,blue);

       сellStyle.setFillForegroundColor(
               myColor.getIndex()
       );


        
       return this;
    }
    

    
    
    
}
