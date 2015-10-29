package com.example.jorgi.ejercicio_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    CheckBox checkCycling;
    CheckBox checkTeaching;
    CheckBox checkBloggin;
    Button boton;
    TextView hobbis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUISetup();

    }

    public void initialUISetup() {
        checkCycling = (CheckBox)findViewById(R.id.cycling);
        checkTeaching = (CheckBox)findViewById(R.id.teaching);
        checkBloggin = (CheckBox)findViewById(R.id.bloggin);
        boton = (Button)findViewById(R.id.btnHobby);
        hobbis = (TextView)findViewById(R.id.hobby);

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getHobbyClick(v);
            }
        });
    }

    public void getHobbyClick(View v)
    {
        String strMessage = "";
        if(checkCycling.isChecked()){
            strMessage+="Cycling ";
        }
        if(checkTeaching.isChecked()){
            strMessage+="Teaching ";
        }
        if(checkBloggin.isChecked()){
            strMessage+="Blogging ";
        }
        showTextNotification(strMessage);
    }

    public void showTextNotification(String msgToDisplay)
    {
        hobbis.setText(msgToDisplay);
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }

}
