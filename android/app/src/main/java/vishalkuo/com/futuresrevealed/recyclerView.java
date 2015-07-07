package vishalkuo.com.futuresrevealed;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;


public class recyclerView extends Activity {

    private ActionBar actionBar;
    private ProgressBar spinner;
    private Context c;
    private rAdapter ra;
    private TextView nothingfound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        c = this;


        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        nothingfound = (TextView)findViewById(R.id.nothingfound);
        spinner = (ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        ConnectivityManager cm = (ConnectivityManager)c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        recList.setLayoutManager(llm);


        if (isConnected){
            new AsyncReceive(spinner, c, recList, nothingfound, ra).execute();
        }else{
            nothingfound.setVisibility(View.VISIBLE);
            nothingfound.setText("No internet connection available");
        }



        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //actionBar.setTitle(Html.fromHtml("<i>Surveys</i>"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }
}