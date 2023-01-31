package edu.northeastern.numad23sp_yingjiehuang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.annotation.NonNull;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.content.res.Configuration;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LinksActivity extends AppCompatActivity {
    RecyclerView linkRecyclerView;
    FloatingActionButton fab;
    List<Link> linkList;
    LinkAdapter linkAdapter;

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        linkList = new ArrayList<>();

        linkAdapter = new LinkAdapter(linkList, this);
        linkRecyclerView = findViewById(R.id.link_recycler_view);
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkRecyclerView.setAdapter(linkAdapter);
        new ItemTouchHelper(itemtouch).attachToRecyclerView(linkRecyclerView);

        fab = findViewById(R.id.new_link);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

    }

    private void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);

        EditText name = dialog.findViewById(R.id.name);
        EditText url = dialog.findViewById(R.id.url);
        Button save = dialog.findViewById(R.id.save);
        save.setOnClickListener((view) -> {
            if(!URLUtil.isValidUrl(url.getText().toString()) || name.getText().toString().length() == 0) {
                Snackbar fail = Snackbar.make(this.linkRecyclerView, "Invaid entry", Snackbar.LENGTH_LONG);
                fail.show();
            } else {
                Link newLink = new Link(name.getText().toString(), url.getText().toString(), linkList.size() + 1);
                linkList.add(newLink);
                Snackbar succeed = Snackbar.make(this.linkRecyclerView, "Link added", Snackbar.LENGTH_LONG);
                succeed.show();
                linkAdapter.notifyDataSetChanged();
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    ItemTouchHelper.SimpleCallback itemtouch = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView linkRecyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            linkList.remove(viewHolder.getAdapterPosition());
            linkAdapter.notifyDataSetChanged();
            Snackbar delete = Snackbar.make
                    (linkRecyclerView, "Link deleted", Snackbar.LENGTH_LONG);
            delete.show();
        }
    };
}
