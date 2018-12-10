package com.example.eyenach.practicefinal.Favorite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.eyenach.practicefinal.FileFragment;
import com.example.eyenach.practicefinal.MenuFragment;
import com.example.eyenach.practicefinal.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    SQLiteDatabase db;
    Cursor cursor;

    SharedPreferences sharedPreferences;

    ArrayList<Favorite> favorites = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView _favList = getView().findViewById(R.id.favorite_list);
        final FavoriteAdapter _favAdapter = new FavoriteAdapter(getActivity(), R.layout.fragment_favorite_item, favorites);

        //open to use DataBase and create it.
        db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY AUTOINCREMENT, author VARCHAR(20), title VARCHAR(50), data VARCHAR(200))");

        cursor = db.rawQuery("SELECT * FROM favorites", null);

        _favAdapter.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String author = cursor.getString(1);
            String title = cursor.getString(2);
            String data = cursor.getString(3);

            favorites.add(new Favorite(author, title, data));

            Log.d("FAVORITE", "ID : " + id + " title : " + title);
        }

        _favList.setAdapter(_favAdapter);

        _favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("FAVORITE", "Click on item = "+position);

                //set bundle
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);

                FileFragment fileFragment = new FileFragment();
                fileFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, fileFragment)
                        .addToBackStack(null)
                        .commit();
                Log.d("FAVORITE", "GOTO FILE");
            }
        });

        initAddBtn();
        initBackBtn();
    }

    void initAddBtn(){
        Button _addBtn = getView().findViewById(R.id.favorite_add_btn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FavoriteFormFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("FAVORITE", "GOTO FORM");
            }
        });
    }

    void initBackBtn(){
        Button _backBtn = getView().findViewById(R.id.favorite_back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("FAVORITE", "BACKTO MENU");
            }
        });
    }
}
