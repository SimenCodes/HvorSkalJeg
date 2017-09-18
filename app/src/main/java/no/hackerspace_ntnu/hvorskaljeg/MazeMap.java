package no.hackerspace_ntnu.hvorskaljeg;

import android.net.Uri;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class MazeMap {

    private static OkHttpClient httpClient = new OkHttpClient();

    /**
     * MazeMap doesn't open the search result page reliably when we send a link.
     * Instead, we do a "fake" search for our room, and find MazeMap's internal ID for the room.
     * Then, we return a Map containing a user-facing description of the room, and the
     * corresponding direct room link
     */
    static Map<String, Uri> getMapLink(String roomName) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.mazemap.com/search/equery/?q=" + roomName + "&rows=5&withpois=true&campusid=1")
                .build();
        Response response = httpClient.newCall(request).execute();
        JSONObject json = new JSONObject(response.body().string());
        JSONArray rooms = json.getJSONArray("result");

        SortedMap<String, Uri> results = new TreeMap<>();
        for (int i = 0; i < rooms.length(); i++) {
            // Some ugly code to decipher what kind of thing MazeMap found.
            JSONObject roomInfo = rooms.getJSONObject(i);

            String displayName = Html.fromHtml(roomInfo.optString("title")).toString();
            String floor = roomInfo.optString("zName");
            String buildingName = roomInfo.optJSONArray("dispBldNames").optString(0);
            String typeName = roomInfo.optJSONArray("dispTypeNames").optString(0);
            if (typeName.length() > 0) typeName = "(\"" + typeName + "\")";
            String roomDescription = displayName + " i " + buildingName + ", etg " + floor + " " + typeName;

            String roomId = roomInfo.getString("poiId");
            Uri link = Uri.parse("https://use.mazemap.com/?v=1&sharepoitype=poi&sharepoi=" + roomId);

            results.put(roomDescription, link);
        }

        return results;
    }
}
