package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vishalkuo on 15-03-12.
 */
public class AsyncReceive extends AsyncTask<String, String, String> {

    private String result = "";
    private Context c;
    private ProgressBar prog;
    private int status = 0;
    private InputStream in;

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
            String postUrl = "http://www.vishalkuo.com/phpGet";
            Log.v("Attempt Conn: ", postUrl);


            HttpClient client = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(postUrl);

            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();


            in = entity.getContent();

            status = 1;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            in.close();

            result = sb.toString();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try{
            JSONArray jarr = new JSONArray(result);
            for (int i = 0; i < jarr.length(); i++){
                JSONObject jdata = jarr.getJSONObject(i);
                Log.i("logging", "name: " + jdata.getString("name") +  "description: " + jdata.getString("description"));

            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }


}
