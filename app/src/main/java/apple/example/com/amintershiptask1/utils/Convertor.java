package apple.example.com.amintershiptask1.utils;

public class Convertor {

    public String convertCallDuration(Integer timeInSeconds) {
        String result = "";
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds - (60 * minutes);
        String min = "" + minutes;
        String sec = "" + seconds;
        if (min.length() == 1) {
            min = "0" + min;
        }
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        result = min + ":" + sec;
        return result;
    }
}
