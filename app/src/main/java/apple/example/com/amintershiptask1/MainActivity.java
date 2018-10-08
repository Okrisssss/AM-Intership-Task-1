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
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.buttonContentLog)
    Button buttonContentLog;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private static final int REQUEST_CODE = 10;
    private CallListAdapter callsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        buttonContentLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);
                    } else {
                        setPersonsList();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setPersonsList();
            } else {
                Toast.makeText(this, "Please provide all necessary permissions!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setPersonsList() {
        List<ContactInformation> personList = new CallLogContent(MainActivity.this).getCallLogs();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callsRecyclerAdapter = new CallListAdapter(personList, this);
        recyclerView.setAdapter(callsRecyclerAdapter);
    }
}


