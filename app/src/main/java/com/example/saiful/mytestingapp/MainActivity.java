package com.example.saiful.mytestingapp;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSetValue;
    private TextView txtHelloworld;
    private ArrayList<String> names;
    private DelayAsync delayAsync;
    ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHelloworld = findViewById(R.id.tv_text);
        btnSetValue = findViewById(R.id.btn_set_value);
        btnSetValue.setOnClickListener(this);


        names = new ArrayList<>();
        names.add("Saiful Anam");
        names.add("Khotibul Umam");
        names.add("Maskumambang");


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_set_value) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < names.size(); i++) {
                name.append(names.get(i)).append("\n");
            }
            txtHelloworld.setText(name.toString());

            delayAsync = new DelayAsync();
            delayAsync.execute();


        }

    }

    private static class DelayAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000000);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            return null;
        }
     @Override
     protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
         Log.d("DellayAsync", "Done");
        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
            Log.d("DellayAsync", "Cancelled");
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(delayAsync != null){
           if(delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)){
               delayAsync.cancel(true);
           }
        }
    }
    }
