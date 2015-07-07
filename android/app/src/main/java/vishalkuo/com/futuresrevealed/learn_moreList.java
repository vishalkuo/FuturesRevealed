package vishalkuo.com.futuresrevealed;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;


public class learn_moreList extends Activity {
    private Drawable actionbarBG;
    private customScroll cs;
    private ActionBar actionBar;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more_list);


        actionbarBG = getResources().getDrawable(R.drawable.abar_bg);
        actionbarBG.setAlpha(0);

        getActionBar().setBackgroundDrawable(actionbarBG);

        cs = (customScroll)findViewById(R.id.cs);
        cs.setOnScrollChangedListener(new customScroll.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView sv, int a, int b, int c, int d) {
                final int headerHeight = findViewById(R.id.header).getHeight() - getActionBar().getHeight();
                final float ratio = (float) Math.min(Math.max(b, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                actionbarBG.setAlpha(newAlpha);
            }
        });

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        tv = (TextView)findViewById(R.id.webText);
        tv.setText(Html.fromHtml("Visit our <a href = \"http://www.futuresrevealed.ca\">website</a> for more information"));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_more_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
