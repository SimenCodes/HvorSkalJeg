package no.hackerspace_ntnu.hvorskaljeg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;

import biweekly.component.VEvent;

public class MainActivity extends AppCompatActivity {

  CalendarManager calendarManager;

  // Fields for our views, so we can reference them later on.
  TextView courseView;
  TextView locationView;
  TextView timeView;
  EditText usernameInput;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Now that we have a layout, we find our views
    courseView = (TextView) findViewById(R.id.courseView);
    locationView = (TextView) findViewById(R.id.locationView);
    timeView = (TextView) findViewById(R.id.timeView);
    usernameInput = (EditText) findViewById(R.id.usernameInput);

    // Let's init our calendar down here. Later on, we'll need to pass some arguments
    // that aren't available until onCreate has been called.
    calendarManager = new CalendarManager(getPreferences(MODE_PRIVATE));
  }

  /**
   * This will be run by Android every time our activity is about to be displayed onscreen
   */
  @Override
  protected void onStart() {
    super.onStart();
    updateUI();
  }

  private void updateUI() {
    VEvent lecture = calendarManager.getNextLecture();
    if (lecture == null) return;

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

  public void onSetUsernameClicked(View button) {
    final String username = usernameInput.getText().toString();
    // We ALWAYS do network on a separate thread.
    // Otherwise, the app will freeze while we are fetching data.
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          calendarManager.downloadCalendar(username);
          // Only the UI thread is allowed to do UI stuff.
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              updateUI();
            }
          });
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void onMapButtonClicked(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String roomName = calendarManager.getNextLecture().getLocation().getValue();
          Uri mazeMapUrl = MazeMap.getMapLink(roomName);

          // Tell Android we'd like to open this URI.
          // It will open the MazeMap app if it is installed, or fall back to a web browser.
          Intent intent = new Intent(Intent.ACTION_VIEW, mazeMapUrl);
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // MazeMap doesn't let users press "Back", so make a new task so they can use the app switcher
          startActivity(intent);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
