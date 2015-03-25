package vishalkuo.com.futuresrevealed;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SplashScreen extends Activity {
    TextView openF;
    private static int _timeOut = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        getActionBar().hide();

        openF = (TextView)findViewById(R.id.openingF);
        Typeface thinFont = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        openF.setTypeface(thinFont);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        openF.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);

                        finish();
                    }
                }, _timeOut);*/

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

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
