package org.cegep.gg.modele;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	 /**
     * Retourne la date du jour au format spécifié.
     *
     * @param format Le format de date souhaité (par exemple, "dd-MM-yyyy").
     * @return La date du jour au format spécifié.
     */
    public static String getDateDuJour(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
