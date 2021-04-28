package basisFx.appCore.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFColor;

public interface StyleHandle {
    StyleHandle setFontHeight(int h);

    StyleHandle setFontName(String n);

    StyleHandle setFontBold();

    StyleHandle setWrapText();

    StyleHandle setRowHeight(int h);

    StyleHandle setHorizontalAlignmentCENTER();

    StyleHandle setHorizontalAlignmentLeft();

    StyleHandle setHorizontalAlignmentRight();

    StyleHandle setVerticalAlignmentCENTER();

    StyleHandle setFontItalic();

    StyleHandle setFontColor(short s);

    StyleHandle setFontColorRGB(int i, int k, int j);

    StyleHandle setCellType(CellType ct);

    StyleHandle setDataFormatForNumericNotNegative();

    StyleHandle setDataFormatForNumericWithNegative();

    CellStyle getСellStyle();

    StyleHandle setBorder(BorderStyle top, BorderStyle right,
                           BorderStyle bottom, BorderStyle left, short s);

    StyleHandle setBorderRGB(BorderStyle top, BorderStyle right,
                              BorderStyle bottom, BorderStyle left, XSSFColor s);

    StyleHandle setFillForegroundCol(short s);

    StyleHandle setFillForegroundCol(XSSFColor color);

    StyleHandle setFillPattern(FillPatternType f);

    StyleHandle setFillForegroundColRGB(int red, int green, int blue);
}
