package com.example.coach.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.model.Profile;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Profile> profiles;

    public HistoryListAdapter(Context context, ArrayList<Profile> profiles)
    {
        this.context = context;
        this.profiles = profiles;
    }
    @NonNull
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_list_history, parent, false);
        return new HistoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.ViewHolder holder, int position)
    {
        holder.txtListDate.setText("" + position);
        holder.txtListIMG.setText("" + profiles.get(position).getImg());
    }

    @Override
    public int getItemCount()
    {
        return profiles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView txtListDate;
        public final TextView txtListIMG;
        public final ImageButton btnListDel;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtListDate = (TextView) itemView.findViewById(R.id.txtListDate);
            txtListIMG = (TextView) itemView.findViewById(R.id.txtListIMG);
            btnListDel = (ImageButton) itemView.findViewById(R.id.btnListSuppr);
        }
    }
}
