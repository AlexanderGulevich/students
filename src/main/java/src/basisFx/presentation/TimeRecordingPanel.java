package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.panelSets.AutoCommitByDateTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.DateViaPopup;
import basisFx.appCore.windows.WindowInfoDispatcher;
import basisFx.dataSource.BFxPreparedStatement;
import basisFx.domain.*;
import basisFx.appCore.DynamicContentPanel;
import java.sql.Date;
import java.time.LocalDate;

public class TimeRecordingPanel extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {

        DataStoreCallBack cb = activeRecord -> {
            TimeRecordingForEmployers entry = (TimeRecordingForEmployers) activeRecord;
            LocalDate date = entry.getDate();
            Integer employerId = entry.getEmployer().getId();
            boolean filled =BFxPreparedStatement
                    .create("SELECT * FROM EmployeesRatePerHour " + "WHERE EMPLOYERID=?" + " and " +" STARTDATE<=?")
                    .setInt(1, employerId)
                    .setDate(2, Date.valueOf(date))
                    .executeAndCheck();
                if (!filled) {
                    entry.setHours(null);
                    WindowInfoDispatcher.add("К сожалению, для данной даты не установлен тариф для следующего сотрудника: \n"
                    + entry.getEmployer().getName().toUpperCase().trim() );
                    return false;
                }
            return true;
        };

        AutoCommitByDateTableSet.builder()
                .aClass(TimeRecordingForEmployers.class)
                .dateGetter(new DateViaPopup())
                .checkingCallBack(cb)
                .isEditable(true).isSortable(false)
                .currentWindow(window)
                .bigTitle("Учет рабочего времени")
                .littleTitle(null)
                .cssClass(null)
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_N_O_N)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_NON)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.comboBoxCol(
                                Employer.class,
                                "Работник",
                                "employer",
                                0.7d,
                                false
                        ))
                .column(
                        ColumnFabric.doubleCol(
                                "Отработанные часы",
                                "hours",
                                0.3d,
                                true
                        )
                )
                .build().configure();

    }

}
