package com.example.jorgi.ejercicio_layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class relativeLayout_activity extends Activity {

    Button btnTable;
    Button btnLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        btnTable = (Button)findViewById(R.id.btnTableR);
        btnLinear = (Button)findViewById(R.id.btnLinearR);

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(relativeLayout_activity.this, tableLayout_activity.class);
                startActivity(miIntent);
            }
        });

        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(relativeLayout_activity.this, linearLayout_activity.class);
                startActivity(miIntent);
            }
        });

    }

}
