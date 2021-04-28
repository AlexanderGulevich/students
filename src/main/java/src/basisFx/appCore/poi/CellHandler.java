package basisFx.appCore.poi;

import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellHandler {
    @Getter protected      Cell cell;
    protected Workbook workbook ;
    protected      CellStylesStore cellStylesStore;
    protected Sheet spreadsheet;
    

    public CellHandler(Workbook workbook, Sheet spreadsheet) {

        this.workbook=workbook;
        this.spreadsheet=spreadsheet;
        this.cellStylesStore=new CellStylesStore(workbook, spreadsheet);

    }
    
    
    public CellHandler setCell(Cell cell){
        
        this.cell=cell;
         return this;
    
    }
    
    public CellHandler setCellValue(String val){
    
        cell.setCellValue(val);
        return this;
    }
    
    public CellHandler setCellValue(Double val){
        
        cell.setCellValue(val);
        return this;
        
    }

    public CellStylesStore getCellStylesStore() {
        return cellStylesStore;
    }
    
    public CellHandler setRowHeight(Row row, int h) {
       
             row.setHeightInPoints(h);
 
         return this;
        
    }
    public CellHandler setCellStyle(CellStylesStore.StyleKind sk) {
        
               cell.setCellStyle(cellStylesStore.getCellStyle(sk));
        return this;
    
    }
    
    public CellHandler addMergedRegion(int startRow,int endRow,int startCell,int endCell){


        spreadsheet.addMergedRegion(new CellRangeAddress(
                        startRow,        // ряд начала
                        endRow,          // ряд конца
                        startCell,       // ячейка начала
                        endCell          // ячейка конца
                ));

        return this;
    }
    
    public CellHandler multipleSetStyle(Row row, int srartCell, int endCell, CellStylesStore.StyleKind d){
        //set for rirst cell
        this.setCell(row.getCell(srartCell)).setCellStyle(d);
        for (int cellIndex=srartCell+1; cellIndex <=endCell; cellIndex++) {
            this.setCell(row.createCell(cellIndex)).setCellStyle(d);
        }
        return this;
    }
    
    



}
    
      
    
      
      
    

