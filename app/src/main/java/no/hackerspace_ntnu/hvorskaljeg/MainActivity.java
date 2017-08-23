package no.hackerspace_ntnu.hvorskaljeg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

import biweekly.component.VEvent;

public class MainActivity extends AppCompatActivity {

  CalendarManager calendarManager;

  // Fields for our views, so we can reference them later on.
  TextView courseView;
  TextView locationView;
  TextView timeView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Now that we have a layout, we find our views
    courseView = (TextView) findViewById(R.id.courseView);
    locationView = (TextView) findViewById(R.id.locationView);
    timeView = (TextView) findViewById(R.id.timeView);

    // Let's init our calendar down here. Later on, we'll need to pass some arguments
    // that aren't available until onCreate has been called.
    calendarManager = new CalendarManager();
  }

  /**
   * This will be run by Android every time our activity is about to be displayed onscreen
   */
  @Override
  protected void onStart() {
    super.onStart();
    VEvent lecture = calendarManager.getNextLecture();

    // Find the data we'd like to display…
    String location = lecture.getLocation().getValue();
    String course = lecture.getSummary().getValue();

    Date lectureStart = lecture.getDateStart().getValue();
    Date now = new Date();
    long minutes = (lectureStart.getTime() - now.getTime()) / 60000;

    // …and display it
    courseView.setText("Du skal ha " + course + " i");
    locationView.setText(location);
    if (minutes > 1) {
      // Plenty of time until lecture starts (famous last words)
      timeView.setText("om " + minutes + " min");
    } else if (minutes < -5) {
      // Lecture has started already
      timeView.setText("for " + minutes + " min siden");
    } else {
      // Lecture started just now!
      timeView.setText("NÅ!");
    }
  }
}
