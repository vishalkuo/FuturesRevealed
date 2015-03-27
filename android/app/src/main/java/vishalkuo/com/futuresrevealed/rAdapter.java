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
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
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
        private TextView vSurname;
        private TextView vEmail;
        private TextView vTitle;
        private Typeface thickFont = Typeface.createFromAsset(c.getAssets(), "Roboto-Regular.ttf");



        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
            vName.setTypeface(thickFont);
            vSurname.setTypeface(thickFont);
            vEmail.setTypeface(thickFont);
            vTitle.setTypeface(thickFont);
        }
    }
}