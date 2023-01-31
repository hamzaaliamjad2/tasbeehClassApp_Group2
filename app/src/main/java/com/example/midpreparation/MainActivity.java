package com.example.midpreparation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText daroodCount, kalmaCount,astaghfarCount,dateText;

    Button btnSave, btnEdit, btnShowData;
    String date;
    DBHandler db;
    ListView listView;

    DBHandler myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase= new DBHandler(this);
        daroodCount= findViewById(R.id.Darood);
        kalmaCount= findViewById(R.id.kalma);
        astaghfarCount= findViewById(R.id.AstaghfarCount);
        dateText=findViewById(R.id.editTextDate);
        btnSave = findViewById(R.id.btn_save);
        btnShowData=findViewById(R.id.ShowData);
        db = new DBHandler(this);
        listView = findViewById(R.id.list_view);

        RefreshGrid();
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( dateText.getText().toString().equals(""))
                {
                    //Do nothing
                }
                else
                {
                    String dateView= dateText.getText().toString();
                    RefreshGrid(dateView);
                }

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int darood,ast,kalma;
                if(daroodCount.getText().toString().equals(""))
                {
                    darood=0;
                }
                else
                {
                    darood = Integer.parseInt(daroodCount.getText().toString());
                }
                if(astaghfarCount.getText().toString().equals(""))
                {
                    ast=0;
                }
                else
                {
                    ast = Integer.parseInt(astaghfarCount.getText().toString());
                }
                if(kalmaCount.getText().toString().equals(""))
                {
                    kalma=0;
                }
                else
                {
                    kalma= Integer.parseInt(kalmaCount.getText().toString());
                }
                date= LocalDate.now().toString();

                UserData user = new UserData(date, darood,kalma,ast);
                db.insertStudent(user);
                RefreshGrid();
            }
        });


    }
    public void RefreshGrid(){
       List<UserData> users = db.selectAllUsers();
//        ArrayAdapter<UserData> arrayAdapter = new ArrayAdapter<UserData>(MainActivity.this, android.R.layout.simple_list_item_1,students);
//        listView.setAdapter(arrayAdapter);
       ArrayAdapter<UserData> adapter = new ArrayAdapter<UserData>(this, android.R.layout.simple_list_item_1, users){
            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText("Date: "+ users.get(position).getDate()+"\n \n \n"+"Astaghfar: "+users.get(position).getCountAstaghfar()+"\n"+"Kalma: "+users.get(position).getCountKalma()+"\n"+
                        "Darood: "+users.get(position).getCountDarood()+"\n \n");
              return view;
          }
        };
      listView.setAdapter(adapter);
    }
    public void RefreshGrid(String date){
        List<UserData> users = db.selectAllUsers(date);
//        ArrayAdapter<UserData> arrayAdapter = new ArrayAdapter<UserData>(MainActivity.this, android.R.layout.simple_list_item_1,students);
//        listView.setAdapter(arrayAdapter);
        ArrayAdapter<UserData> adapter = new ArrayAdapter<UserData>(this, android.R.layout.simple_list_item_1, users){
            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText("Date: "+ users.get(position).getDate()+"\n \n \n"+"Astaghfar: "+users.get(position).getCountAstaghfar()+"\n"+"Kalma: "+users.get(position).getCountKalma()+"\n"+
                        "Darood: "+users.get(position).getCountDarood()+"\n \n");
                return view;
            }
        };
        listView.setAdapter(adapter);
    }
}