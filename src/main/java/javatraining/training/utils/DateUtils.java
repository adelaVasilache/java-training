package javatraining.training.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adela Vasilache on 27.04.2018
 */
public class DateUtils {
    public static Date getDate(Integer year, Integer month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateInString = String.join("-", "01", month.toString(), year.toString());
        return sdf.parse(dateInString);
    }
}
