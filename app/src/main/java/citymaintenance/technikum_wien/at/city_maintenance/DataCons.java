package citymaintenance.technikum_wien.at.city_maintenance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DataCons  extends AsyncTask{
    private TextView statusField,roleField;
    private Context context;
    private String result="";
    private String category="";
    private String description="";
    private Double latitude;
    private Double longitude;
    private int flag=0;

    public DataCons(Context context, String category, String description, Double latitude, Double longitude, int flag) {
        this.context = context;
        this.category = category;
        this.description = description;
        this.longitude = longitude;
        this.latitude=latitude;
        this.flag = flag;
    }



    protected void onPreExecute(){
    }

    @Override
    protected Object doInBackground(Object[] arg0) {
        StringBuilder sb=null;
        BufferedReader reader=null;
        String serverResponse=null;
        String link="";
        //means by Get Method

            try{
//                String password = (String)arg0[1];
                switch(flag){
                    case 0:
                        String slongitude="";
                        String slatitude="";
                        slongitude=this.longitude.toString();
                        slatitude=this.latitude.toString();
                         link= "http://city.draxl.eu/insert.php?cn=\""+this.category+"\"&lat="+slatitude+"&lon="+longitude+"&desc=\""+this.description+"\"&st=\""+"reported"+"\"";
                        break;
                    case 1:
                        link= "http://city.draxl.eu/select_category.php";

                        break;
                    case 2:
                        link= "http://city.draxl.eu/select_issues.php";

                        break;
                }


                //  String link = "http://myphpmysqlweb.hostei.com/login.php?username="+username+"& password="+password;
                Log.v("datacos52", link);
                URL url = new URL(link);
               // URL url = new URL("http://192.168.2.106/?Lichterkette=1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                int statusCode = connection.getResponseCode();
                Log.v("dataconsstatusCode", "" + Integer.toString(statusCode));
                if (statusCode == 200) {
                    sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                }

                connection.disconnect();
                if (sb!=null)
                   serverResponse=sb.toString();
                this.result=serverResponse;
                Log.v("datacons78", serverResponse);



            } catch(Exception e){
               Log.v("datacons83", e.toString());
            }


       return this.result;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        switch(flag){
            case 0:
                   break;
            case 1:
                MainActivity.dataFromAsyncTask=o.toString();

                break;
        }



    }
}