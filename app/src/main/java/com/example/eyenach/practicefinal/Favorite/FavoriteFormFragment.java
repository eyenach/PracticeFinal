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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyenach.practicefinal.R;

public class FavoriteFormFragment extends Fragment {

    SharedPreferences sharedPreferences;

    SQLiteDatabase db;

    EditText title, data;
    String userStr, titleStr, dataStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get Username
        sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        userStr = sharedPreferences.getString("user", null);

        //open to use DataBase and create it.
        db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY AUTOINCREMENT, author VARCHAR(20), title VARCHAR(50), data VARCHAR(200))");

        initBackBtn();
        initSaveBtn();
    }

    void initSaveBtn(){
        Button _saveBtn = getView().findViewById(R.id.fav_form_save_btn);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = getView().findViewById(R.id.fav_form_title);
                data = getView().findViewById(R.id.fav_form_data);

                titleStr = title.getText().toString();
                dataStr = data.getText().toString();

                Favorite favorite = new Favorite(userStr, titleStr, dataStr);
                db.insert("favorites", null, favorite.getRow());

                Toast.makeText(getActivity(), "SAVE COMPLETE", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FavoriteFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("FAVORITE", "SAVE COMPLETE");
            }
        });
    }

    void initBackBtn(){
        Button _backBtn = getView().findViewById(R.id.fav_form_back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FavoriteFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("FAVORITE", "BACKTO FAVORITE");
            }
        });
    }
}
