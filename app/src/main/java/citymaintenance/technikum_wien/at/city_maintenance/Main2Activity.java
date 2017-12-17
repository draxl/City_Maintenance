package citymaintenance.technikum_wien.at.city_maintenance;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    int catId=0;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        c=this.getBaseContext();

        Intent intent = getIntent();
        Bundle bundle= getIntent().getExtras();
        final String message=bundle.getString("category");
        this.catId=bundle.getInt("categoryid");

        TextView textView = (TextView) findViewById(R.id.tv2);
        textView.setText(message);

        Button button = (Button) findViewById(R.id.b2);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Log.v("click", message);


                 insert_php();
            }

        });
    }



    public void insert_php(){
        try {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.v("click", Double.toString(loc.getLatitude()));
            TextView textView1 = (TextView) findViewById(R.id.tv2);
            String category =(String)textView1.getText();
            EditText textview2 =(EditText) findViewById(R.id.tv3);
            String description = textview2.getText().toString();
            DataCons Dc=new DataCons(this, Integer.toString(this.catId),description,loc.getLatitude(), loc.getLongitude(), 0);
            Dc.execute();
        }
        catch(SecurityException e){
            Log.v("locationerror", e.toString());
        }
        catch(Exception e){
            Log.v("derror", e.toString());
        }





    }
}
