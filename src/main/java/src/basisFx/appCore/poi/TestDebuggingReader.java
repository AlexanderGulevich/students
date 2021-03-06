package basisFx.appCore.poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author 62
 */
public class TestDebuggingReader extends Reader{

    

    private  TestDebuggingReader(File file, int sheetNum ) throws IOException, FileNotFoundException, InvalidFormatException {
       openWorkBook(file);
       sheet=wb.getSheetAt(sheetNum);
       printLengthAndHeigthOfSheet(sheet);
       checkAllOfSheet(wb);
       rowIteration();
    }
    private  TestDebuggingReader()  {
      
    }
    protected void rowIteration() {
        
       
           Iterator<Row> rowIterator = sheet.iterator();
//           String categoryName;

               while (rowIterator.hasNext()) {

                       Row row = rowIterator.next();                 
                       
                       Iterator<Cell> cellIterator= row.cellIterator();
                       
                       while (cellIterator.hasNext()){
                           
                          Cell concreateCell= cellIterator.next();
                          
                          
                          String vallue=null;
                          
                          if(concreateCell.getCellTypeEnum()==CellType.FORMULA){
                          
                              vallue=concreateCell.getCellFormula();
                          
                          }else if(concreateCell.getCellTypeEnum()==CellType.NUMERIC){
                          
                              vallue=String.valueOf(concreateCell.getNumericCellValue());
                              
                          }else if(concreateCell.getCellTypeEnum()==CellType.STRING){
                          
                              vallue=String.valueOf(concreateCell.getStringCellValue());
                          }
                          
                          
                           try {
                                 System.out.println("??????----"+  row.getRowNum()   +
                                   "  ????????????----"+ concreateCell.getColumnIndex()+
                                   "  Enum----"+concreateCell.getCellTypeEnum().toString()+
                                   "  ????????????????---- "+vallue
                                   );
                           } catch (Exception e) {
                               
                               System.err.println("???????????? - ?????? "+ row.getRowNum()+ "????????????  -  "+concreateCell.getColumnIndex()+
                                     "  Enum = "+concreateCell.getCellTypeEnum().toString()  );
                           }
                         


                          
                       
                       }
//                   
                       

                       
               } 
               
               
              

       }
    
    
    public static TestDebuggingReader run(File file, int sheetNum) throws FileNotFoundException, InvalidFormatException{
    
        try {
            
            return new TestDebuggingReader(file, sheetNum);
            
        } catch (IOException ex) {
            
            Logger.getLogger(TestDebuggingReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public static TestDebuggingReader run(){
    
        
            
            return new TestDebuggingReader();
            
        
    }
    
    
    
    protected void checkAllOfSheet(Workbook wbk){
              for(int i=0; i<wbk.getNumberOfSheets();i++){
                  System.out.println("---?????? ??????????--- "+wbk.getSheetName(i) +"---?????????? ??????????--- "+i);
                  
              }

     }
     public  void printLengthAndHeigthOfSheet(Sheet sheet){
        
      
       short   numberOfCells=0 ;
       Integer   firstRow=null;
       Integer   lastRow= null;   
         
         Iterator<Row> rowIterator = sheet.iterator();
        
         while (rowIterator.hasNext()) {
           
             Row row = rowIterator.next();
             
             lastRow= row.getRowNum();
             
             if (firstRow==null){
                 
                 firstRow=row.getRowNum();
                 
             }
           
           
             if(numberOfCells<row.getLastCellNum()){
                 
                numberOfCells=row.getLastCellNum();
             }
    }
         
         System.out.println("?????????? ?????????????? ????????   "+firstRow);
         System.out.println("?????????? ???????????????????? ????????    "+ lastRow);
         System.out.println("???????????????????? ?????????? ??????????  "+sheet.getPhysicalNumberOfRows());
         System.out.println("???????????????????????? ?????????? ??????????  "+numberOfCells);


 
    
    }
}
