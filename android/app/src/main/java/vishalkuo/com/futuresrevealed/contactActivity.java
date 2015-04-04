package vishalkuo.com.futuresrevealed;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


public class contactActivity extends Activity {
    private ImageButton facebook;
    private ImageButton twitter;
    private ImageButton linkedin;
    private Button email;
    private Button web;

    private ActionBar actionBar;
    private Drawable actionbarBG;
    private customScroll cs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact2);


        actionbarBG = getResources().getDrawable(R.drawable.abar_bg);
        actionbarBG.setAlpha(0);

        getActionBar().setBackgroundDrawable(actionbarBG);

        cs = (customScroll)findViewById(R.id.cslearn);
        cs.setOnScrollChangedListener(new customScroll.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView sv, int a, int b, int c, int d) {
                final int headerHeight = findViewById(R.id.headimg).getHeight() - getActionBar().getHeight();
                final float ratio = (float) Math.min(Math.max(b, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                actionbarBG.setAlpha(newAlpha);
            }
        });

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        facebook = (ImageButton)findViewById(R.id.facebook);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getOpenFacebookIntent(getApplicationContext());
                startActivity(i);

            }
        });

        twitter = (ImageButton)findViewById(R.id.twitter);

        twitter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = getOpenTwitterIntent(getApplicationContext());
                startActivity(i);
            }
        });

        linkedin = (ImageButton)findViewById(R.id.linkedin);

        linkedin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = getOpenLinkedIntent(getApplicationContext());
                startActivity(i);
            }
        });

        email = (Button)findViewById(R.id.email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getOpenEmailIntent(getApplicationContext());
                startActivity(i);
            }
        });

        web= (Button)findViewById(R.id.website);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.futuresrevealed.ca"));
                startActivity(i);
            }
        });
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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


    private static Intent getOpenFacebookIntent(Context c){
        try{
            c.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,  Uri.parse("fb://page/1382959188665784"));
        }
        catch(Exception e){
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/futuresrevealed"));
        }
    }

    private static Intent getOpenTwitterIntent(Context c){
        try{
            c.getPackageManager().getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=futuresrevealed"));
        }catch(Exception e){
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/futuresrevealed"));
        }
    }

    private static Intent getOpenLinkedIntent(Context c){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(
                "https://www.linkedin.com/company/9257128?trk=tyah&trkInfo=tarId%3A1421712447867%2Ctas%3Afutures%20revealed%2Cidx%3A1-1-1"));
        return i;
    }

    private static Intent getOpenEmailIntent(Context c){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@futuresrevealed.com"});
        return i;
    }
}


