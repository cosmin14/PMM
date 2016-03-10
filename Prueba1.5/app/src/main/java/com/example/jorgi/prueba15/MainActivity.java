package com.example.jorgi.prueba15;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networking.HttpClient;
import com.networking.OnHttpRequestComplete;
import com.networking.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private ArrayList<Plaza> plazas;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plazas = new ArrayList<Plaza>();

        HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()){
                    Gson gson = new GsonBuilder().create();
                    try {
                        JSONObject jsonO = new JSONObject(status.getResult());
                        JSONArray jsonArray = jsonO.getJSONArray("plazas");
                        for (int i = 0; i < jsonArray.length(); i++){
                            String plaza = jsonArray.getString(i);
                            Plaza p = gson.fromJson(plaza, Plaza.class);
                            plazas.add(p);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });//HttpClient
        cliente.excecute("http://cosmin14.hol.es/jsonReader.php");

        listView = (ListView) findViewById(R.id.listViewPlazas);
        AdaptadorPlaza adaptador = new AdaptadorPlaza(MainActivity.this);
        listView.setAdapter(adaptador);

    }


    class AdaptadorPlaza extends ArrayAdapter<Plaza> {
        public Activity context;
        public AdaptadorPlaza(Activity context) {
            super(context, R.layout.view_plaza, plazas);
            this.context = context;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.view_plaza, null);
            TextView lblZona = (TextView) item.findViewById(R.id.textViewPlaza);
            lblZona.setText("" + plazas.get(position).getCalle());
            return (item);
        }
    }
}
