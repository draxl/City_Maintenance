package citymaintenance.technikum_wien.at.city_maintenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private List<Issues> issueList = new ArrayList<>();

    private RecyclerView recyclerView;
    private IssueAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
      mAdapter = new IssueAdapter(issueList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();


    }

    public String FillList(){
        String rfl="";
        try {
            Log.v("fillspinner1", "x");
            DataCons Dc = new DataCons(this, "", "",null,null, 2);
            rfl= Dc.execute().get().toString();
            Log.v("fillspinner2", "gg");
            Log.v("fillspinner2", rfl);
        }
        catch(Exception e){
            Log.v("fillspinner1", e.toString());

        }

        return rfl;
    }

    private void prepareMovieData() {

        String r=FillList();

        Log.v("conversation_err", r);
        if (r!=null){
            String[] items = r.split("!");

            for (String o : items) {
                Log.v("conversation_err", o);
                String[] e = o.split(";");

                try {


                    issueList.add(new Issues(e[0],e[1],e[2],e[3]));
                } catch (Exception x) {
                    // Log.v("conversation_err", e.toString());
                }
            }

        }

       mAdapter.notifyDataSetChanged();
    }
}
