package ch.soreco.devday2014.cleancode.cleancodesession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is used for calculation
 */
public class Calculator implements Constants {

    public Calculator(String c) {
        if (!"VN".equalsIgnoreCase(c)) {
            throw new IllegalArgumentException(c + " not supported yet!");
        }
    }

    protected boolean isCNY(String ds) {

        if (ds == null) {
            throw new IllegalArgumentException("Illegal argument given");
        }

        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
            if (d.getTime() < START_RANGE || d.getTime() >= END_RANGE) {
                throw new IllegalArgumentException(ds + " not supported");
            }

            for (int i = 0; i < NYS.length; i++) {
                long ny = NYS[i];

                if (new SimpleDateFormat("yyyy-MM-dd").format(new Date(ny)).equals(ds)) {
                    return true;
                }

                int end = 0;
                GregorianCalendar c = new GregorianCalendar();
                c.setTimeInMillis(ny);
                switch (c.get(7)) {

                    case 1:
                        end = 5;
                        break;

                    case 2:
                        end = 4;
                        break;

                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        end = 6;
                        break;
                }

                while (end-- > 0) {
                    ny += 1000l*60*60*24;
                    if (new SimpleDateFormat("yyyy-MM-dd").format(new Date(ny)).equals(ds)) {
                        return true;
                    }

                }
            }
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Illegal argument given");
        }

        return false;
    }

    public boolean isPH(String ds) {
        if (ds == null) {
            throw new IllegalArgumentException("Illegal argument given");
        }

        if (isCNY(ds)) return true;
        
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(d);

            if ((c.get(6) == 1) || ((c.get(5) == 30) && c.get(2) == 3) || 
                    ((c.get(5) == 1) && c.get(2) == 4) || ((c.get(5) == 2) && c.get(2) == 8) || 
                    ((c.get(5) == 10) && c.get(2) == 2)) {
                return true;
            }

        } catch (ParseException ex) {
            throw new IllegalArgumentException("Illegal argument given");
        }
        return false;
    }

    public boolean isWE(String ds) {
        if (ds == null) {
            throw new IllegalArgumentException("Illegal argument given");
        }

        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(d);

            if ((c.get(7) == 1) || (c.get(7) == 7)) {
                return true;
            }

        } catch (ParseException ex) {
            throw new IllegalArgumentException("Illegal argument given");
        }
        return false;
    }
}
