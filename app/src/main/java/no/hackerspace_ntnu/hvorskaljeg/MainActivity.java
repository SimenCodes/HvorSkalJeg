package no.hackerspace_ntnu.hvorskaljeg;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

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
        if (lecture == null) {
            courseView.setText(null);
            locationView.setText(null);
            timeView.setText(null);
            return;
        }

        // Find the data we'd like to display…
        String location = lecture.getLocation().getValue();
        String course = lecture.getSummary().getValue();

        Date lectureStart = lecture.getDateStart().getValue();
        Date now = new Date();
        long minutes = (lectureStart.getTime() - now.getTime()) / 60000;

        // …and display it
        courseView.setText("Du skal ha " + course + " i");
        locationView.setText(location);
        if (minutes > 60) {
            // Plenty of time until lecture starts (famous last words)
            if ((minutes / 1440) == 1) {
                timeView.setText("om " + minutes / 1440 + " dag og " + (minutes / 60) % 24 + " timer");
            } else if ((minutes / 1440) < 1) {
                timeView.setText("om " + (minutes / 60) % 24 + " timer");
            } else {
                timeView.setText("om " + minutes / 1440 + " dager og " + (minutes / 60) % 24 + " timer");
            }
        } else if (minutes < 60 & minutes > 1) {
            timeView.setText("om " + minutes + " minutter");
        } else if (minutes < -5) {
            // Lecture has started already
            if (minutes > -60) {
                timeView.setText("for " + (-minutes) + " minutter siden");
            } else {
                timeView.setText("for " + (-minutes / 60) % 24 + " timer siden");
            }
        } else {
            // Lecture started just now!
            timeView.setText("NÅ!");
        }
    }

    public void onDownloadClicked(final View button) {
        button.setEnabled(false); // Don't let people spam the button.
        final String username = usernameInput.getText().toString();
        // We ALWAYS do network on a separate thread.
        // Otherwise, the app will freeze while we are fetching data.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calendarManager.downloadCalendar(username);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Only the UI thread is allowed to do UI stuff.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                        button.setEnabled(true);
                    }
                });
            }
        }).start();
    }

    public void onMapButtonClicked(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    VEvent nextLecture = calendarManager.getNextLecture();
                    if (nextLecture == null) return;
                    final String roomName = nextLecture.getLocation().getValue();
                    final Map<String, Uri> results = MazeMap.getMapLink(roomName);
                    if (results.size() == 1) {
                        // If there's only one result, go right there.
                        Uri link = results.values().iterator().next();
                        openMazeMap(link);
                    } else {
                        // If there are multiple results, display a dialog
                        // and let the user select the right room.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                displayRoomDialog(roomName, results);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Displays a dialog where the user can select a room.
     *
     * @param roomName The room the user is searching for
     * @param rooms    A map of user-friendly room descriptions and MazeMap links
     */
    void displayRoomDialog(String roomName, final Map<String, Uri> rooms) {
        final String[] names = rooms.keySet().toArray(new String[]{});
        new AlertDialog.Builder(MainActivity.this)
            .setTitle("Hvilket " + roomName + "?")
            .setItems(names, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int indexOfClickedName) {
                    // Open the link corresponding to the selected room.
                    Uri link = rooms.get(names[indexOfClickedName]);
                    openMazeMap(link);
                }
            })
            .show();

    }

    private void openMazeMap(Uri link) {
        // Tell Android we'd like to open this URI.
        // It will open the MazeMap app if it is installed, or fall back to a web browser.
        Intent intent = new Intent(Intent.ACTION_VIEW, link);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // MazeMap doesn't let users press "Back", so make a new task so they can use the app switcher
        startActivity(intent);
    }

}
