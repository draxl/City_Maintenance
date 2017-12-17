package citymaintenance.technikum_wien.at.city_maintenance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    String EXTRA_MESSAGE="";
    public static String dataFromAsyncTask="";
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton button1 = (ImageButton) findViewById(R.id.b1);
        Button button2 = (Button) findViewById(R.id.b2);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               SendMessage();
            }

        });


        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Listprev();
            }

        });




        List<Category> spinnerArray =  new ArrayList<Category>();

        String r=FillSpinner();

        Log.v("conversation_err", r);
        if (r!=null){
            String[] items = r.split("!");

            for (String o : items) {
                Log.v("conversation_err", o);
                String[] e = o.split(";");

                try {

                    int i = Integer.parseInt(e[0]);
                    spinnerArray.add(new Category(i, e[1]));
                } catch (Exception x) {
                   // Log.v("conversation_err", e.toString());
                }
            }

        }



      ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.cat_spinner);
        sItems.setAdapter(adapter);




    }




    public void Listprev(){

        Intent intent = new Intent(this, Main3Activity.class);
          startActivity(intent);
    }


    public void SendMessage(){


        Intent intent = new Intent(this, Main2Activity.class);
        Spinner spinner = (Spinner)findViewById(R.id.cat_spinner);
        Category cat=(Category)spinner.getSelectedItem();
        int catId=cat.getId();
        String text = spinner.getSelectedItem().toString();
        intent.putExtra("category", text);
        intent.putExtra("categoryid", catId);
        startActivity(intent);
    }

    public String FillSpinner(){
        String rfs="";
        try {
            Log.v("fillspinner", "x");
            DataCons Dc = new DataCons(this, "", "",null,null, 1);
            rfs= Dc.execute().get().toString();
        }
        catch(Exception e){}

      return rfs;
    }
}
