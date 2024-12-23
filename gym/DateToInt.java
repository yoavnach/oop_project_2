package gym;

public class DateToInt {

    public static int getYear(String time) {
        try {
            return Integer.parseInt(time.substring(6,10));
        }
        catch (Exception e) {
            return 0;
        }

    }
    public static int getMonth(String time) {
        try {
            return Integer.parseInt(time.substring(3,5));
        }
        catch (Exception e) {
            return 0;
        }

    }
    public static int getDay(String time) {
        try {
            return Integer.parseInt(time.substring(0,2));
        }
        catch (Exception e) {
            return 0;
        }

    }
    public static int getHour(String time) {
        try {
            return Integer.parseInt(time.substring(11,13));
        }
        catch (Exception e) {
            return 0;
        }

    }
    public static int getMinute(String time) {
        try {
            return Integer.parseInt(time.substring(14,16));
        }
        catch (Exception e) {
            return 0;
        }

    }
}

