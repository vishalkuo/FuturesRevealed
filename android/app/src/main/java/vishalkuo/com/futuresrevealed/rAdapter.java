package vishalkuo.com.futuresrevealed;

import android.content.Context;
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
public class rAdapter extends RecyclerView.Adapter<rAdapter.ContactViewHolder> {

    private List<rInfo> contactList;
    private static Context c;

    public rAdapter(List<rInfo> contactList, Context c) {
        this.contactList = contactList;
        this.c = c;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        rInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vTitle.setText(ci.name);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView vName;
        private TextView vTitle;
        private Typeface thickFont = Typeface.createFromAsset(c.getAssets(), "Roboto-Regular.ttf");



        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);

            vTitle = (TextView) v.findViewById(R.id.title);
            vName.setTypeface(thickFont);
            vTitle.setTypeface(thickFont);
        }
    }
}