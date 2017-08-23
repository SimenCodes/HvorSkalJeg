package no.hackerspace_ntnu.hvorskaljeg;

import android.support.annotation.Nullable;

import java.io.IOException;
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
  ICalendar calendar = null;

  void downloadCalendar(String username) throws IOException {
    String url = "https://ntnu.1024.no/2017/host/" + username + "/ical/forelesninger";

    Request request = new Request.Builder().url(url).build();
    Response response = httpClient.newCall(request).execute();
    String ical = response.body().string();

    calendar = Biweekly.parse(ical).first();
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
