package vishalkuo.com.futuresrevealed;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreen extends Activity {
    private TextView openF;
    private TextView part2;
    private TextView part3;
    private ImageView bg;
    private Button survey;
    private Button eList;
    private Button learnMore;
    private static int _timeOut = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        getActionBar().hide();

        openF = (TextView)findViewById(R.id.openingF);
        Typeface thinFont = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        Typeface thickFont = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        openF.setTypeface(thinFont);

        part2 = (TextView)findViewById(R.id.pt2);
        part2.setTypeface(thinFont);
        part3 = (TextView)findViewById(R.id.pt3);
        part3.setTypeface(thickFont);

        bg = (ImageView)findViewById(R.id.bg);
        survey = (Button)findViewById(R.id.surveys);
        survey.setTypeface(thickFont);

        eList = (Button)findViewById(R.id.eList);
        eList.setTypeface(thickFont);
        learnMore = (Button)findViewById(R.id.learnMore);

        Animation scale = AnimationUtils.loadAnimation(this, R.anim.translate);
        Animation scale2 = AnimationUtils.loadAnimation(this, R.anim.translate2);
        Animation scale3 = AnimationUtils.loadAnimation(this, R.anim.translate3);
        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.fade);
        Animation btn1 = AnimationUtils.loadAnimation(this, R.anim.btn1);


        openF.startAnimation(scale);
        part2.startAnimation(scale2);
        part3.startAnimation(scale3);
        bg.startAnimation(alpha);
        survey.startAnimation(btn1);
        eList.startAnimation(btn1);
        learnMore.startAnimation(btn1);


        /*scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);

                        finish();
                    }
                }, _timeOut);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/

        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), surveyActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spalsh_screen, menu);
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