package no.hackerspace_ntnu.hvorskaljeg;

import android.net.Uri;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class MazeMap {

  private static OkHttpClient httpClient = new OkHttpClient();

  /**
   * MazeMap doesn't open the search result page reliably when we send a link.
   * Instead, we do a "fake" search for our room, and find MazeMap's internal ID for the room.
   * Then, we return a URI that takes the user straight to that room.
   */
  static Uri getMapLink(String roomName) throws Exception {
    Request request = new Request.Builder()
        .url("https://api.mazemap.com/search/equery/?q=" + roomName + "&rows=1&withpois=true&campusid=1")
        .build();
    Response response = httpClient.newCall(request).execute();
    JSONObject json = new JSONObject(response.body().string());
    String poiId = json.getJSONArray("result").getJSONObject(0).getString("poiId");

    return Uri.parse("https://use.mazemap.com/?v=1&sharepoitype=poi&sharepoi=" + poiId);
  }
}
