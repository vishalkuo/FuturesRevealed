package vishalkuo.com.futuresrevealed;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class surveyActivity extends Activity {
    private ListView listView;
    private ProgressBar spinner;
    private Context c = this;
    private TextView name;
    private TextView description;
    private TextView website;
    private TextView nothingfound;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        listView = (ListView)findViewById(R.id.SurveyList);

        spinner = (ProgressBar)findViewById(R.id.progBar);
        spinner.setVisibility(View.GONE);


        //name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        //website = (TextView)findViewById(R.id.url);
        nothingfound = (TextView)findViewById(R.id.nothingfound);

        //new AsyncReceive(spinner, c, listView, name, description, website, nothingfound).execute();

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }
}
