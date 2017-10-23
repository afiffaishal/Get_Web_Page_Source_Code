package com.crocodic.get_web_page_source_code;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.crocodic.get_web_page_source_code.R.id.url;

public class MainActivity extends AppCompatActivity {
    public String html = "";


    TextView sourc ;

    private Spinner gret;
    private EditText txtDescription;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gret = (Spinner) findViewById(R.id.web);
        sourc = (TextView) findViewById(R.id.code);


       // Button btSpinner = (Button) findViewById(R.id.get);
        txtDescription =
                (EditText) findViewById(R.id.url);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        Button btget = (Button) findViewById(R.id.get) ;
        btget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = gret.getSelectedItem().toString();
                String string = txtDescription.getText().toString();
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(str+string);
                HttpResponse response = null;
                try {
                    response = client.execute(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String html = "";
                InputStream in = null;
                try {
                    in = response.getEntity().getContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder str = new StringBuilder();
                String line = null;
                try {
                    while((line = reader.readLine()) != null)
                    {
                        str.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                html = str.toString();
                sourc.setText(html);
                //Toast.makeText(MainActivity.this, html, Toast.LENGTH_SHORT).show();
            }
        });





    }
}
