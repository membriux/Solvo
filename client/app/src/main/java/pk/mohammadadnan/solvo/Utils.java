package pk.mohammadadnan.solvo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getString(long date){
        Date dateObject = new Date(date);
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm aa, d LLL, yyyy");
        return formatDate.format(dateObject);
    }
}
