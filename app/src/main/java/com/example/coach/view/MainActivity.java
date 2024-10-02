package com.example.coach.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;

public class MainActivity extends AppCompatActivity
{
    private ImageButton btnIMG;
    private ImageButton btnHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnIMG = findViewById(R.id.btnMonIMG);
        btnHistory = findViewById(R.id.btnMonHistorique);
    }
    public void onClick(View view)
    {
        ImageButton buttonSource = (ImageButton) view;
        if(buttonSource.getId() == btnIMG.getId())
        {
            Intent intent = new Intent(MainActivity.this, CalculActivity.class);
            startActivity(intent);
        }
        else if(buttonSource.getId() == btnHistory.getId())
        {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        }
    }
}