package apple.example.com.amintershiptask1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import java.util.List;

import apple.example.com.amintershiptask1.R;
import apple.example.com.amintershiptask1.content.CallLogContent;
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
            if (person.getName() == null){
            holder.textName.setText("Unknown number");
            } else  {
            holder.textName.setText(person.getName());
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

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog editDialog = new Dialog(context);
                    editDialog.setContentView(R.layout.edit_dialog);
                    Button editItem = editDialog.findViewById(R.id.editItem);
                    Button deleteItem = editDialog.findViewById(R.id.deleteItem);
                    final EditText editName = editDialog.findViewById(R.id.editName);
                    final EditText editNumber = editDialog.findViewById(R.id.editNumber);
                    final EditText editDuration = editDialog.findViewById(R.id.editDuration);
                    final EditText editType = editDialog.findViewById(R.id.editType);
                    editName.setText(person.getName());
                    editNumber.setText(person.getNumber());
                    editDuration.setText(person.getDuration());
                    editType.setText(person.getType());
                    editItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CallLogContent callLogContent = new CallLogContent(context);
                            callLogContent.editCall(new ContactInformation(person.getId(), editName.getText().toString(), editDuration.getText().toString(), editType.getText().toString(), editNumber.getText().toString()));
                            personsList = callLogContent.getCallLogs();
                            notifyDataSetChanged();
                            editDialog.hide();
                            editDialog.dismiss();
                        }
                    });
                    deleteItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CallLogContent callLogContent = new CallLogContent(context);
                            callLogContent.deleteCall(person.getId());
                            personsList = callLogContent.getCallLogs();
                            notifyDataSetChanged();
                            editDialog.hide();
                            editDialog.dismiss();
                        }
                    });
                    editDialog.show();
                }
            });
        }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}
