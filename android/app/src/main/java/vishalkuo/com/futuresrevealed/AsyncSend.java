package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalkuo on 15-03-04.
 */
public class AsyncSend extends AsyncTask<String, String, String> {
    private Context c;
    private int didGetSent = 0;
    private String responseStr = "";
    private String name = "";
    private String email = "";


    @Override
    protected String doInBackground(String... strings) {
        try{
            // url where the data will be posted
            String postReceiverUrl = "http://vishalkuo.com/phptest.php";
            Log.v("postURL: ", postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("firstname", name));
            nameValuePairs.add(new BasicNameValuePair("email", email));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                responseStr = EntityUtils.toString(resEntity).trim();
                didGetSent = 1;


                }
            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


     }

    public AsyncSend(Context c, String name, String email){
        this.c = c;
        this.name = name;
        this.email = email;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (didGetSent == 1) {
            Toast.makeText(c, "Got it, thanks " + name + "!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(c, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }
}
