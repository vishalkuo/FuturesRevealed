package vishalkuo.com.futuresrevealed;

import android.content.Context;
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


public class surveyActivity extends ActionBarActivity {
    private ListView listView;
    private ProgressBar spinner;
    private Context c = this;
    private TextView name;
    private TextView description;
    private TextView website;
    private TextView nothingfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        listView = (ListView)findViewById(R.id.SurveyList);

        spinner = (ProgressBar)findViewById(R.id.progBar);
        spinner.setVisibility(View.GONE);


        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        website = (TextView)findViewById(R.id.url);
        nothingfound = (TextView)findViewById(R.id.nothingfound);

        new AsyncReceive(spinner, c, listView, name, description, website, nothingfound).execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
