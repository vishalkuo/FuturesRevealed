package vishalkuo.com.futuresrevealed;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vishalkuo on 15-03-27.
 */
public class rAdapter extends RecyclerView.Adapter<rAdapter.surveyViewHolder> {

    private List<rInfo> surveyList;
    private static Context c;

    public rAdapter(List<rInfo> contactList, Context c) {
        this.surveyList = contactList;
        this.c = c;
    }


    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    @Override
    public void onBindViewHolder(surveyViewHolder sHolder, int i) {
        rInfo ci = surveyList.get(i);
        //sHolder.description.setText(ci.name);
        sHolder.name.setText(ci.name);
    }

    @Override
    public surveyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new surveyViewHolder(itemView);
    }

    public static class surveyViewHolder extends RecyclerView.ViewHolder {

        private TextView description;
        private TextView name;
        private TextView browser;
        private Typeface thickFont = Typeface.createFromAsset(c.getAssets(), "Roboto-Regular.ttf");



        public surveyViewHolder(View v) {
            super(v);
            description =  (TextView) v.findViewById(R.id.description);
            name = (TextView) v.findViewById(R.id.title);
            browser = (TextView) v.findViewById(R.id.browser);
            browser.setTextColor(Color.parseColor("#A0A0A0"));
            description.setTypeface(thickFont);
            name.setTypeface(thickFont);
        }
    }
}