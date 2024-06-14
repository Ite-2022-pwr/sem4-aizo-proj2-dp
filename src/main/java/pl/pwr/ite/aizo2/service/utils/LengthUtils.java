package pl.pwr.ite.aizo2.service.utils;

public class LengthUtils {

    public static <T> int getLen(T[] array) {
        int count = 0;
        for(T ele : array) {
            if(ele != null){
                count++;
            }
        }
        return count;
    }
}
