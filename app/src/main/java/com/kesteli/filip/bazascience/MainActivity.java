package com.kesteli.filip.bazascience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvID;
    private TextView tvSITE;
    private TextView tvHISTORY;
    private TextView tvFAVORITE;
    private TextView tvEUREKA;
    private Button btnADD;
    private Button btnHISTORY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        tvID = (TextView) findViewById(R.id.tvID);
        tvID = (TextView) findViewById(R.id.tvSITE);
        tvID = (TextView) findViewById(R.id.tvHISTORY);
        tvID = (TextView) findViewById(R.id.tvFAVORITE);
        tvID = (TextView) findViewById(R.id.tvEUREKA);
        btnADD = (Button) findViewById(R.id.btnADD);
        btnHISTORY = (Button) findViewById(R.id.btnHISTORY);
    }

    private void setupListeners() {
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                String stranicaSITE = "www.google.com";
                int stranicaHISTORY = 1;
                int stranicaFAVORITE = 0;
                int stranicaEUREKA = 0;
                Stranica stranica = new Stranica(stranicaSITE, stranicaHISTORY, stranicaFAVORITE, stranicaEUREKA);
                myDBHandler.addStranica(stranica);
            }
        });
        btnHISTORY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                Stranica stranica = myDBHandler.findStranicaZaSite("www.google.com");
                if (stranica != null) {
                    tvID.setText(String.valueOf(stranica.get_id()));
                    tvSITE.setText(String.valueOf(stranica.get_site()));
                    tvHISTORY.setText(String.valueOf(stranica.get_history()));
                    tvFAVORITE.setText(String.valueOf(stranica.get_favorite()));
                    tvEUREKA.setText(String.valueOf(stranica.get_eureka()));
                }
            }
        });
    }
}
