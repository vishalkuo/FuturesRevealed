package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vishalkuo on 15-03-12.
 */
public class AsyncReceive extends AsyncTask<String, String, JSONArray> {

    private String result = "";
    private Context c;
    private TextView name;
    private TextView description;
    private TextView website;
    private ProgressBar prog;
    private int status = 0;
    private InputStream in;
    private JSONArray jarr = null;
    private ListView listview;
    private final static String _NAME = "name";
    private final static String _DESCRIPTION = "description";
    private final static String _WEBSITE = "website";
    JSONArray res = null;
    ArrayList<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();


    public AsyncReceive(ProgressBar prog, Context c, ListView l,
                        TextView n, TextView d,TextView w){
        this.prog = prog;
        this.c = c;
        this.listview = l;
        this.name = n;
        this.description = d;
        this.website = w;
        resList = new ArrayList<HashMap<String, String>>();
    }

    @Override
    protected void onPostExecute(JSONArray s) {
        super.onPostExecute(s);
        prog.setVisibility(View.GONE);
        if (status == 0){
            Toast.makeText(c, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                res = jarr;
                for (int i = 0; i < res.length(); i++){
                    JSONObject j = res.getJSONObject(i);

                    String _n = j.getString(_NAME);
                    String _d = j.getString(_DESCRIPTION);
                    String _w = j.getString("url");
                    Log.d("TEST", _w);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(_NAME, _n);
                    map.put(_DESCRIPTION, _d);
                    map.put(_WEBSITE, _w);

                    resList.add(map);
                    ListAdapter adapter = new SimpleAdapter(c, resList, R.layout.listview, new String[]{_NAME, _DESCRIPTION, _WEBSITE},
                            new int[]{R.id.name, R.id.description, R.id.url});

                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(c, "You Clicked on "+resList.get(position).get("name"), Toast.LENGTH_SHORT).show();
                        }
                    });

                    listview.setVisibility(View.VISIBLE);

                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        prog.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        try{
            String postUrl = "http://www.vishalkuo.com/phpGet.php";


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
            jarr = new JSONArray(result);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return jarr;
    }


}
