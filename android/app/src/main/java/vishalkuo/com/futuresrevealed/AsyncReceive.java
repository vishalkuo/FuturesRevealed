package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vishalkuo on 15-03-12.
 */
public class AsyncReceive extends AsyncTask<String, String, String> {


    private Context c;
    private ProgressBar prog;
    private int status = 0;


    public AsyncReceive(ProgressBar prog, Context c){
        this.prog = prog;
        this.c = c;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        prog.setVisibility(View.GONE);
        if (status == 0){
            Toast.makeText(c, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        prog.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            String getUrl = "http://www.vishalkuo.com/phpGet";
            Log.v("Attempt Conn: ", getUrl);


            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getUrl);

            HttpResponse response = client.execute(httpGet);
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (response.getEntity().getContent()));

            status = 1;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }


}
