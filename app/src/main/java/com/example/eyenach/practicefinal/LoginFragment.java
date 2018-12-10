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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    EditText _user, _pwd;
    String _userStr, _pwdStr;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        Boolean check = sharedPreferences.getBoolean("login", false);

        if(check){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new MenuFragment())
                    .addToBackStack(null)
                    .commit();
        }

        initLoginBtn();
    }

    void initLoginBtn(){
        Button _login = getView().findViewById(R.id.login_login_btn);
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _user = getView().findViewById(R.id.login_user);
                _pwd = getView().findViewById(R.id.login_pwd);

                _userStr = _user.getText().toString();
                _pwdStr = _pwd.getText().toString();

                Log.d("LOGIN", "CLICK LOGIN");

                if(_userStr.equals("admin") && _pwdStr.equals("admin")){

                    //set SharedPreference to chk Login
                    sharedPreferences.edit().putBoolean("login", true).commit();

                    //set SharedPreference is Username
                    sharedPreferences.edit().putString("user", _userStr).commit();

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new MenuFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("LOGIN", "GOTO MENU");
                } else {
                    Toast.makeText(getActivity(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
