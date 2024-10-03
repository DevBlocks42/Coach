package com.example.coach.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.controller.Control;
import com.example.coach.model.Profile;

import java.util.ArrayList;
import android.util.Log;

public class HistoryActivity extends AppCompatActivity
{
    private Control control;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        control = Control.getInstance();
        createList();
    }
    private void createList()
    {
        ArrayList<Profile> profiles = control.getProfiles();
        if(profiles != null)
        {
            Log.d("PROFILES", String.valueOf(profiles.size()));
            RecyclerView history = (RecyclerView) findViewById(R.id.lstHistory);
            HistoryListAdapter adapter = new HistoryListAdapter(HistoryActivity.this, profiles);
            history.setAdapter(adapter);
            history.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

        }
    }

}