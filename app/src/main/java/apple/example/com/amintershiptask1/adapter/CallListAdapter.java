package apple.example.com.amintershiptask1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import apple.example.com.amintershiptask1.R;
import apple.example.com.amintershiptask1.models.ContactInformation;

public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.PersonsViewHolder>{

    List<ContactInformation> personsList;
    Context context;


    class PersonsViewHolder extends RecyclerView.ViewHolder{

        TextView textType;
        TextView textName;
        TextView textNumber;
        TextView textDuration;
        View view;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textType = itemView.findViewById(R.id.textType);
            textName = itemView.findViewById(R.id.textName);
            textDuration = itemView.findViewById(R.id.textDuration);
            textNumber = itemView.findViewById(R.id.textNumber);
        }
    }

    public CallListAdapter(List<ContactInformation> personsList, Context context) {
        this.personsList = personsList;
        this.context = context;
    }


    @Override
    public PersonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_layout, parent, false);
        return new PersonsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonsViewHolder holder, int position) {
        final ContactInformation person = personsList.get(position);
        switch(person.getType()){
            case "1": {
                holder.textType.setText("Incoming");
                holder.textType.setTextColor(Color.parseColor("#FF85F460"));
            } break;
            case "2": {
                holder.textType.setText("Outgoing");
                holder.textType.setTextColor(Color.parseColor("#FFFFD940"));
            } break;
            case "3": {
                holder.textType.setText("Missing");
                holder.textType.setTextColor(Color.parseColor("#FFF74044"));
            } break;
        }

            holder.textNumber.setText(person.getNumber());

            int timeInSeconds = Integer.parseInt(person.getDuration());
            int minutes = timeInSeconds / 60;
            int seconds = timeInSeconds - (60*minutes);
            String min = "" + minutes;
            String sec = "" + seconds;
            if(min.length() == 1){
                min = "0" + min;
            }
            if(sec.length() == 1){
                sec = "0" + sec;
            }
            holder.textDuration.setText(min + ":" + sec);
        }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}
