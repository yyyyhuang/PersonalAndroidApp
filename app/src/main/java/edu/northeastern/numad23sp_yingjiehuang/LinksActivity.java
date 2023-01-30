package edu.northeastern.numad23sp_yingjiehuang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LinksActivity extends AppCompatActivity {
    RecyclerView linkRecyclerView;

    List<Link> linkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        linkList = new ArrayList<>();

        linkRecyclerView = findViewById(R.id.link_recycler_view);

        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        linkRecyclerView.setAdapter(new LinkAdapter(linkList, this));

    }

}
