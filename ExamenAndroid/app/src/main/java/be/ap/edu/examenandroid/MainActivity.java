package be.ap.edu.examenandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MapsActivity.class);
        final ListView listview = (ListView) findViewById(R.id.velostations);

        // Create ListView
        List<String> velostations = new ArrayList();
        JSONArray jsonArray = get_json();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                velostations.add(obj.getString("naam"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,  android.R.layout.simple_list_item_1, velostations);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                try {
                    JSONArray jsonArray = get_json();
                    JSONObject obj = jsonArray.getJSONObject(position);
                    intent.putExtra("longtitude", obj.getDouble("point_lng"));
                    intent.putExtra("latitude", obj.getDouble("point_lat"));
                    startActivityForResult(intent, REQUEST_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public JSONArray get_json() {
        String json;
        JSONArray jsonArray = new JSONArray();
        try {
            InputStream is = getAssets().open("velostation.json");
            int size = is.available();
            byte[] buffer  = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            jsonArray = new JSONArray(json);

            /*for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
