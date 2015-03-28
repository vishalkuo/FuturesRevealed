package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
import java.util.List;

/**
 * Created by vishalkuo on 15-03-12.
 */
public class AsyncReceive extends AsyncTask<String, String, JSONArray> {

    private String result = "";
    private Context c;
    private TextView nothingfound;
    private ProgressBar prog;
    private boolean validData = false;
    private int status = 0;
    private InputStream in;
    private rAdapter ra;
    private JSONArray jarr = null;
    private RecyclerView recyclerView;
    private final static String _NAME = "name";
    private final static String _DESCRIPTION = "description";
    private final static String _WEBSITE = "website";
    JSONArray res = null;
    private List<rInfo> surveyData = new ArrayList<rInfo>();



    public AsyncReceive(ProgressBar prog, Context c, RecyclerView r,TextView not, rAdapter ra){
        this.prog = prog;
        this.c = c;
        this.recyclerView = r;
        this.nothingfound = not;
        this.ra = ra;
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
                if (jarr == null){
                    nothingfound.setText("No surveys found! Check back later.");
                    return;
                }
                for (int i = 0; i < res.length(); i++){
                    JSONObject j = res.getJSONObject(i);
                    rInfo ri = new rInfo();
                    ri.name = j.getString(_NAME);
                    ri.description = j.getString(_DESCRIPTION);
                    ri.website = j.getString("url");

                    surveyData.add(ri);
                    ra = new rAdapter(surveyData, c);
                    recyclerView.setAdapter(ra);
                    recyclerView.setVisibility(View.VISIBLE);

                    final GestureDetector _GDETECT = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener(){
                        @Override public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                            if (child != null && _GDETECT.onTouchEvent(e)){
                                int i= recyclerView.getChildPosition(child);
                                String url = surveyData.get(i).website;
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                c.startActivity(intent);
                                return true;
                            };
                            return false;
                        }

                        @Override
                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                        }
                    });


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
            validData = true;
        }
        catch(IOException e){
            e.printStackTrace();
            validData = false;
        }

        try{
            jarr = new JSONArray(result);
        }catch(JSONException e){
            e.printStackTrace();
            validData = false;
        }

        return jarr;
    }


}
