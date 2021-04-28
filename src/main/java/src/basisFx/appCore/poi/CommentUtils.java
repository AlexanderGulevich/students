package basisFx.appCore.poi;

import basisFx.appCore.utils.ImgUtils;
import basisFx.domain.price.Img;
import basisFx.domain.price.PriceItem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class CommentUtils {

    public static void setComment(HSSFWorkbook wb, HSSFSheet spreadsheet, Cell cell, PriceItem priceItem, boolean isBig) throws IOException, SQLException {

        InputStream imgStream=null;
        int cols=0;
        int rowsDivider=0;
        Img img = priceItem.getImg();
        if (img != null) {
            if (isBig) {
                 imgStream = img.getImgBig();
                 cols=3;
                rowsDivider=20;
            }else {
                imgStream = img.getImgSmall();
                cols=2;
                rowsDivider=20;
            }
        }

        BufferedImage bim = ImageIO.read(imgStream);
        ByteArrayOutputStream baos = ImgUtils.toOutputStream(bim);

            int height = bim.getHeight();
            int rows = BigDecimal.valueOf(height).divide(BigDecimal.valueOf(rowsDivider),0, RoundingMode.HALF_UP).toBigInteger().intValue();
            int picIndex = wb.addPicture(baos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG);
            int c = cell.getColumnIndex();
            int r = cell.getRowIndex();

        HSSFCreationHelper creationHelper = wb.getCreationHelper();
        HSSFPatriarch drawingPatriarch = spreadsheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = creationHelper.createClientAnchor();
            anchor.setCol1(c+1);
            anchor.setCol2(c + cols);
            anchor.setRow1(r);
            anchor.setRow2(r + rows);

            HSSFComment comment = drawingPatriarch.createCellComment(anchor);
            comment.setBackgroundImage(picIndex);

            cell.setCellComment(comment);


//        }
    }}
