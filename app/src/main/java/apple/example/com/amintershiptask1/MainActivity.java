package apple.example.com.amintershiptask1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import apple.example.com.amintershiptask1.adapter.CallListAdapter;
import apple.example.com.amintershiptask1.content.CallLogContent;
import apple.example.com.amintershiptask1.models.ContactInformation;

public class MainActivity extends AppCompatActivity{

    private static final int REQUEST_CODE = 10;
    private CallListAdapter callsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonContentLog = findViewById(R.id.buttonContentLog);
        buttonContentLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                    if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);
                    } else {
                        List<ContactInformation> personsList = new CallLogContent(MainActivity.this).getCallLogs();
                        setPersonsList(personsList);
                    }
                }
            }
        });

/*        requestAppPermissions(new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS},
                R.string.msg, REQUEST_CODE);*/
    }

/*    @Override
    public void onPermissionsGranted(int requestCode) {

        List<ContactInformation> personsList = new CallLogContent(MainActivity.this).getCallLogs();
        setPersonsList(personsList);
        Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();
    }*/

    private void setPersonsList(List<ContactInformation> personsList){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callsRecyclerAdapter = new CallListAdapter(personsList, this);
        recyclerView.setAdapter(callsRecyclerAdapter);
    }
}


