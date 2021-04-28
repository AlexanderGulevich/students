package basisFx.appCore.reflection;

import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.annotation.Sorting;
import basisFx.appCore.utils.DomainPropertiesMetaInfo;
import basisFx.appCore.utils.Range;

import java.util.ArrayList;

public class ExpressionProcessor {

    public static String getWhere(ArrayList<DomainPropertiesMetaInfo> metaInfo,int outerDomainId ) {
        DTOexp dto =new DTOexp();
        dto.outerDomainId=outerDomainId;
        examineAnnotatedField(metaInfo,dto);
        return exemineWhere(dto);
    }
    public static String getWhereForPeriod(ArrayList<DomainPropertiesMetaInfo> metaInfo,int outerDomainId ) {
        DTOexp dto =new DTOexp();
        dto.outerDomainId=outerDomainId;
        dto.isPeriodocal =true;
        examineAnnotatedField(metaInfo,dto);
        return exemineWhere(dto);
    }
    public static String getWhereForPeriod(ArrayList<DomainPropertiesMetaInfo> metaInfo ) {
        DTOexp dto =new DTOexp();
        dto.isPeriodocal =true;
        examineAnnotatedField(metaInfo,dto);
        return exemineWhere(dto);
    }
    public static String getWhere(ArrayList<DomainPropertiesMetaInfo> metaInfo,int outerDomainId,Range range) {
        DTOexp dto=new DTOexp();
        dto.outerDomainId=outerDomainId;
        examineAnnotatedField(metaInfo,dto);
        examineRange(dto,range);
        return exemineWhere(dto);
    }
    public static String getWhere(ArrayList<DomainPropertiesMetaInfo> metaInfo, Range range) {
        DTOexp dto=new DTOexp();
        examineAnnotatedField(metaInfo,dto);
        examineRange(dto,range);
        return exemineWhere(dto);
    }

    private static DTOexp examineAnnotatedField(ArrayList<DomainPropertiesMetaInfo> metaInfo, DTOexp dto) {

        for (DomainPropertiesMetaInfo info : metaInfo) {
            DataStore dataStoreAnnotation = info.getDataStoreAnnotation();
            if (dataStoreAnnotation != null) {
                if (dataStoreAnnotation.SORTING() == Sorting.ASK) {
                    dto.order_by = " " + " ORDER BY " + info.getPropertyName() + " " + "ASK";
                }
                if (dataStoreAnnotation.SORTING() == Sorting.DESC) {
                    dto.order_by = " " + " ORDER BY " + info.getPropertyName() + " " + "DESC";
                }
                if (dataStoreAnnotation.AS_OUTER_ID()) {
                    dto.outerDomainName=" " + info.getPropertyName() +" ";
                }
                if (dataStoreAnnotation.ANALIZED_DATE()) {
                    dto.analizedDateName = info.getPropertyName();
                }
            }
        }
        return dto;
    }



    private static DTOexp examineRange(DTOexp dto, Range range) {

        switch (range) {
            case  ACTUAL: dto.limit=" LIMIT 1 ";
                break;
            case  ALLTIME:
                break;
            case  YEAR:dto.year=" YEAR("+dto.analizedDateName+") =YEAR(CURRENT_DATE) ";
                break;
            case  YEAR2:dto.year=dto.analizedDateName+" >= (NOW() - INTERVAL 2 YEAR - 1 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  YEAR3:dto.year=dto.analizedDateName+" >= (NOW() - INTERVAL 3 YEAR - 1 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  MONTH:dto.month=" MONTH("+dto.analizedDateName+") =MONTH(CURRENT_DATE) AND  YEAR("+dto.analizedDateName+") =YEAR(CURRENT_DATE) ";
                break;
            case  DAY30: dto.intervalDays=dto.analizedDateName+" >= (NOW() - INTERVAL 30 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  DAY60:dto.intervalDays=dto.analizedDateName+" >= (NOW() - INTERVAL 60 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  DAY90:dto.intervalDays=dto.analizedDateName+">= (NOW() - INTERVAL 90 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  DAY180:dto.intervalDays=dto.analizedDateName+" >= (NOW() - INTERVAL 180 DAY) AND ("+dto.analizedDateName+" <= NOW()) ";
                break;
            case  LAST10: dto.limit=" LIMIT 10 ";
                break;
            case  LAST30: dto.limit=" LIMIT 30 ";
                break;
        }
        return dto;
    }


    private static String exemineWhere(DTOexp dto) {

        ArrayList <String>list=new ArrayList<>();

        if (dto.outerDomainName != null)    list.add(dto.outerDomainName+ "=" + dto.outerDomainId+" ");
        if (dto.year != null)  list.add(dto.year);
        if (dto.month != null)  list.add(dto.month);
        if (dto.intervalDays != null)  list.add(dto.intervalDays);
        if (dto.isPeriodocal != null)  list.add( " "+dto.analizedDateName + " >=?" +" and " + dto.analizedDateName + "<= ? ");

        if(list.toArray().length>0) {


            String exp = " where " + String.join(" and ", list);

            if (dto.order_by != null) exp += dto.order_by;
            if (dto.limit != null) exp += dto.limit;

            return exp;
        }else {
            String exp = " ";

            if (dto.order_by != null) exp += dto.order_by;
            if (dto.limit != null) exp += dto.limit;

            return exp;
        }
    }


}
