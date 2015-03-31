package vishalkuo.com.futuresrevealed;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;


public class learn_moreList extends Activity {
    private Drawable actionbarBG;
    private customScroll cs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more_list);

        actionbarBG = getResources().getDrawable(R.drawable.abar_bg);
        actionbarBG.setAlpha(0);

        getActionBar().setBackgroundDrawable(actionbarBG);

        cs = (customScroll)findViewById(R.id.cs);

        Log.d("XH", String.valueOf(cs));

        cs.setOnScrollChangedListener(new customScroll.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView sv, int a, int b, int c, int d) {
                final int headerHeight = findViewById(R.id.header).getHeight() - getActionBar().getHeight();
                final float ratio = (float) Math.min(Math.max(b, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                actionbarBG.setAlpha(newAlpha);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_more_list, menu);
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
