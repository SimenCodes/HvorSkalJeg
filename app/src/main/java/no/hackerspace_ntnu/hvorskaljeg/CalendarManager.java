package no.hackerspace_ntnu.hvorskaljeg;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is where we do everything related to downloading and parsing our calendar.
 * As the app grows, we will probably have to split this into several classes.
 */
public class CalendarManager {

    static OkHttpClient httpClient = new OkHttpClient();
    SharedPreferences preferences;
    ICalendar calendar = null;

    public CalendarManager(SharedPreferences preferences) {
        this.preferences = preferences;

        // Let's try to read our stored lecture plan
        try {
            String ical = preferences.getString("ical", null);
            calendar = Biweekly.parse(ical).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void downloadCalendar(String username) throws IOException {
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);
        String semester;

        if(month >= 6){
            semester = "host";
        } else{
            semester = "var";
        }

        //Log.w("CalendarManager", year + "");
        String url = "https://ntnu.1024.no/" + year +"/" + semester + "/" + username + "/ical/forelesninger";

        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        String ical = response.body().string();

        calendar = Biweekly.parse(ical).first();
        // Save the ical file so we don't have to download it later.
        preferences.edit().putString("ical", ical).apply();
    }

    /**
     * @return the chronologically first lecture that haven't ended yet.
     */
    @Nullable
    VEvent getNextLecture() {
        if (calendar == null) return null;
        List<VEvent> allLectures = calendar.getEvents();

        Date now = new Date();
        VEvent ourLecture = null;
        Date ourEnd = null;

        for (VEvent lecture : allLectures) {
            Date end = lecture.getDateEnd().getValue();
            if (end.after(now) && (ourLecture == null || end.before(ourEnd))) {
                ourLecture = lecture;
                ourEnd = end;
            }
        }

        return ourLecture;
    }
}
