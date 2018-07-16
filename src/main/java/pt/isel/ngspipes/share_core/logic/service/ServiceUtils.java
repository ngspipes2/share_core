package pt.isel.ngspipes.share_core.logic.service;

import java.util.Calendar;
import java.util.Date;

public class ServiceUtils {

    public static boolean sameCreationDate(Date savedCreationDate, Date newCreationDate) {
        Calendar savedCalendar = Calendar.getInstance();
        savedCalendar.setTime(savedCreationDate);

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(newCreationDate);

        return  savedCalendar.get(Calendar.YEAR) == newCalendar.get(Calendar.YEAR) &&
                savedCalendar.get(Calendar.MONDAY) == newCalendar.get(Calendar.MONDAY) &&
                savedCalendar.get(Calendar.DAY_OF_MONTH) == newCalendar.get(Calendar.DAY_OF_MONTH) &&
                savedCalendar.get(Calendar.HOUR_OF_DAY) == newCalendar.get(Calendar.HOUR_OF_DAY) &&
                savedCalendar.get(Calendar.MINUTE) == newCalendar.get(Calendar.MINUTE) &&
                savedCalendar.get(Calendar.SECOND) == newCalendar.get(Calendar.SECOND);
    }

}
