package be.ap.edu.examenandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MapsActivity extends Activity {

    Intent intent;
    Double longtitude;
    Double latitude;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.maps_layout);

        TextView text = (TextView) findViewById(R.id.testText);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        longtitude = extras.getDouble("longtitude");
        latitude = extras.getDouble("latitude");

        if (longtitude != null) {

        }

        /*
            Button button1 = (Button) findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent = new Intent();
                    intent.putExtra("returnValue1", text1.getText().toString());
                    intent.putExtra("returnValue2", text2.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        */
    }

}
