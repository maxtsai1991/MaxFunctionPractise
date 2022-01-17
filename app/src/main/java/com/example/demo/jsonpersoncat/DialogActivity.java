package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.DialogInterface;
import android.os.Bundle;

public class DialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        AlertDialog.Builder myDlg = new AlertDialog.Builder(this); // MainActivity是情況改為你的Activity類別名稱

        myDlg.setTitle(R.string.StartStation).setItems(R.array.select_station_spinner_list, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

                // The 'which' argument contains the index position
                // of the selected item
            }
        });

        myDlg.show();
    }
}