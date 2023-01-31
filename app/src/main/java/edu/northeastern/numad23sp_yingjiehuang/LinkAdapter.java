package edu.northeastern.numad23sp_yingjiehuang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkViewHolder> {
    private final List<Link> link;
    private final Context context;

    public LinkAdapter(List<Link> link, Context context) {
        this.link = link;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinkViewHolder(LayoutInflater.from(context).inflate(R.layout.item_link, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        holder.name.setText(link.get(position).getName());
        holder.url.setText(String.valueOf(link.get(position).getUrl()));

    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return link.size();
    }
}
