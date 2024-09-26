package com.example.coach.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.controller.Control;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText txtWeight;
    private EditText txtHeight;
    private EditText txtAge;
    private RadioButton rdMen;
    private TextView lblImg;
    private ImageView imgSmiley;
    private Control control;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtWeight = (EditText) findViewById(R.id.txtPoids);
        txtHeight = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdMen = (RadioButton) findViewById(R.id.rdHomme);
        lblImg = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        control = Control.getInstance();
    }

    @Override public void onClick(View view)
    {
        int weight = 0;
        int height = 0;
        int age = 0;
        int sex = 0;
        try
        {
            weight = Integer.parseInt(txtWeight.getText().toString());
            height = Integer.parseInt(txtHeight.getText().toString());
            age = Integer.parseInt(txtAge.getText().toString());
            sex = rdMen.isChecked() ? 1 : 0;
        }
        catch(Exception e) {}
        if(weight == 0 || height == 0 || age == 0)
        {
            Toast.makeText(MainActivity.this, "Veuillez renseigner tous les champs.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Control.createProfile(weight, age, height, sex);
            float img = Control.getImg();
            String message = Control.getMessage();
            switch(message)
            {
                case "Maigreur.":
                {
                    imgSmiley.setImageResource(R.drawable.maigre);
                    lblImg.setTextColor(Color.RED);
                    break;
                }
                case "Normal.":
                {
                    lblImg.setTextColor(Color.GREEN);
                    imgSmiley.setImageResource(R.drawable.normal);
                    break;
                }
                case "Surcharge.":
                {
                    lblImg.setTextColor(Color.RED);
                    imgSmiley.setImageResource(R.drawable.graisse);
                    break;
                }
                default: break;
            }
            lblImg.setText(message + " IMG : " + img);
        }
    }
}