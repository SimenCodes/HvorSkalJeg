package no.hackerspace_ntnu.hvorskaljeg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    // … so that we can do stupid stuff with them!
    locationView.setText("R2");
    timeView.setText("D2");
  }
}
