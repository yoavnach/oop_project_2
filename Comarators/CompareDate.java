package Comarators;
import gym.DateToInt;

import java.util.Comparator;
import java.util.Date;

public class CompareDate implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        //dd-MM-yyyy hh:mm
        try
        {

            if(DateToInt.getYear(o1)<DateToInt.getYear(o2))
            {
                return 1;
            }
            if(DateToInt.getYear(o1)>DateToInt.getYear(o2))
            {
                return -1;
            }
            if(DateToInt.getMonth(o1)<DateToInt.getMonth(o2))
            {
                return 1;
            }
            if(DateToInt.getMonth(o1)>DateToInt.getMonth(o2))
            {
                return -1;
            }
            if(DateToInt.getDay(o1)>DateToInt.getDay(o2))
            {
                return 1;
            }
            if(DateToInt.getDay(o1)<DateToInt.getDay(o2))
            {
                return -1;
            }
            if(DateToInt.getHour(o1)>DateToInt.getHour(o2))
            {
                return 1;
            }
            if(DateToInt.getHour(o1)<DateToInt.getHour(o2))
            {
                return -1;
            }
            if(DateToInt.getMinute(o1)>DateToInt.getMinute(o2))
            {
                return 1;
            }
            if(DateToInt.getMinute(o1)<DateToInt.getMinute(o2))
            {
                return -1;
            }
            return 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return -2;
        }

    }
}
