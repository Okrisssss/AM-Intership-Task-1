package apple.example.com.amintershiptask1.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;



import java.util.ArrayList;
import java.util.List;

import apple.example.com.amintershiptask1.models.ContactInformation;


public class CallLogContent {

    private Context context;

    public CallLogContent(Context context){
        this.context = context;
    }

        public List<ContactInformation> getCallLogs(){
        List<ContactInformation> contacts = new ArrayList<>();
        int id = 0;
        Uri allCalls = Uri.parse("content://call_log/calls");
        Cursor cursor = context.getContentResolver().query(allCalls, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
            String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            contacts.add(new ContactInformation(id,name,duration,type, number));
            id++;
        }
        return contacts;

    }
}
