package edu.northeastern.numad23sp_yingjiehuang;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView url;
    private LinkAdapter adapter;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        url = itemView.findViewById(R.id.url);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW);
                String website = url.getText().toString();
                browser.setData(Uri.parse(website));
                view.getContext().startActivity(browser);
            }
        });
    }

    public LinkViewHolder binAdapter(LinkAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
