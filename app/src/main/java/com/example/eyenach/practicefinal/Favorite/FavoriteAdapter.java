package com.example.eyenach.practicefinal.Favorite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.eyenach.practicefinal.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<Favorite>{

    Context context;
    List<Favorite> _favList = new ArrayList<>();

    TextView title, author, data;

    public FavoriteAdapter(@NonNull Context context, int resource, @NonNull List<Favorite> objects) {
        super(context, resource, objects);
        this.context = context;
        _favList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _favItem = LayoutInflater.from(context).inflate(R.layout.fragment_favorite_item, parent, false);

        title = _favItem.findViewById(R.id.favorite_item_title);
        author = _favItem.findViewById(R.id.favorite_item_author);
        data = _favItem.findViewById(R.id.favorite_item_data);

        Favorite _row = _favList.get(position);
        title.setText(_row.getTitle());
        author.setText("by " + _row.getAuthor());
        data.setText(_row.getData());

        return _favItem;
    }
}
