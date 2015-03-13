package vishalkuo.com.futuresrevealed;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by vishalkuo on 15-03-12.
 */
public class AsyncRecieve extends AsyncTask<String, String, String> {
    private ProgressBar prog;

    public AsyncRecieve(ProgressBar prog){
        prog.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }


}
