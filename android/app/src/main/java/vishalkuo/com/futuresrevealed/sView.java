package vishalkuo.com.futuresrevealed;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class sView extends Activity {
    private String name;
    private String description;
    private String website;

    private TextView nameView;
    private TextView descripView;
    private TextView linkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_view);
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            name = extras.getString("name");
            description = extras.getString("description");
            website = extras.getString("website");
        }


        nameView = (TextView)findViewById(R.id.nameView);
        descripView = (TextView)findViewById(R.id.descriptionView);
        linkView = (TextView)findViewById(R.id.websiteview);

        nameView.setText(name);
        descripView.setText(description);

        String resultantText = "Press " +
                "<a href = \"" + website + "\">here</a> to open the survey in your browser.";
        linkView.setText(Html.fromHtml(resultantText));
        linkView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_s_view, menu);
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
