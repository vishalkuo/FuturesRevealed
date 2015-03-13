package vishalkuo.com.futuresrevealed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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


public class MainActivity extends ActionBarActivity {

    private ImageView mainLogo;
    private ImageButton about;
    private ImageButton contact;
    private ImageButton surveys;
    private String url = "http://futuresrevealed.ca";
    private Context c = this;
    private AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLogo = (ImageView)findViewById(R.id.mainlogo);
        mainLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        about = (ImageButton)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), aboutActivity.class);
                startActivity(i);
            }
        });

        contact = (ImageButton)findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), contactActivity.class);
                startActivity(i);
            }
        });

        surveys = (ImageButton)findViewById(R.id.surveys);
        surveys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), surveyActivity.class);
                startActivity(i);
            }
        });

    }

    public void sendEmail(View v){
        LayoutInflater li = LayoutInflater.from(c);
        View promptsView = li.inflate(R.layout.dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptsView);

        final EditText name = (EditText)promptsView.findViewById(R.id.nameInput);
        final EditText email = (EditText)promptsView.findViewById(R.id.emailInput);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setTitle("Sign up!");
        builder.setMessage("Sign up to receive email updates for useful information and upcoming talks." +
                "\nNote: Futures Revealed will never distribute your email. ");
        builder
                .setCancelable(false)
                .setPositiveButton("Sign me up!", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        new AsyncSend(c, name.getText().toString(), email.getText().toString()).execute();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();

        try{
            if (alertDialog != null && alertDialog.isShowing()){
                alertDialog.dismiss();
            }
        }
        catch (Exception e){
            Log.d("lots of problems", e.getMessage());
        }


    }

}