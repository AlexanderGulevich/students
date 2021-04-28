package basisFx.appCore.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Reader {
    
    protected FileInputStream fileInputStrim;
//    protected XSSFSheet sheet;
    protected Sheet sheet;
    protected File  file;
//    protected XSSFWorkbook workbook;
    protected Workbook wb;
    protected XSSFRow row;
    protected StringHandler strHandler=new StringHandler();
    
    
     protected abstract void rowIteration();
    
    
    protected void openWorkBook(String pathAndName) throws FileNotFoundException, IOException, InvalidFormatException{

             file=new File(pathAndName);

             fileInputStrim = new FileInputStream( file) ;
             
             wb =WorkbookFactory.create(file);
             
             

//             workbook = new XSSFWorkbook(fileInputStrim);

           
   }

    protected void openWorkBook(File file) throws FileNotFoundException, IOException, InvalidFormatException{

            

             fileInputStrim = new FileInputStream( file) ;
             
             wb=WorkbookFactory.create(file);

//             workbook = new XSSFWorkbook(fileInputStrim);

           
   }

//
//    protected XSSFWorkbook getWorkbook() {
//         return workbook;
//     }
    protected Workbook getWorkbook() {
         return wb;
     }
    

    protected Sheet getSheet() {
        return sheet;
    }
    
 
       
      
    }
    
    
   
    
    
