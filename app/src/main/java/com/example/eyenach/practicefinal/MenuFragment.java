package com.example.eyenach.practicefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eyenach.practicefinal.Favorite.FavoriteFragment;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    SharedPreferences sharedPreferences;

    ArrayList<String> menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        menu = new ArrayList<String>();

        menu.add("Favorite");
        menu.add("Sign Out");

        ListView _menuList = getView().findViewById(R.id.menu_list);
        ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menu
        );
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("MENU", "SELECT "+menu.get(position));

            if(menu.get(position).equals("Favorite")){

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FavoriteFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("MENU", "GOTO FAVORITE");

            } else {
                //clear SharedPreference by two ways
//                sharedPreferences.edit().clear().commit();
                sharedPreferences.edit().remove("login").apply();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("MENU", "SIGN OUT");
            }
        }
    });

    }
}
