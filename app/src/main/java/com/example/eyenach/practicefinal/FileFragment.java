package com.example.eyenach.practicefinal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileFragment extends Fragment {

    TextView fileData;

    int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle == null){
            Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_SHORT).show();
        } else {
            index = bundle.getInt("position");

            fileStream();
            Log.d("FILE", "index = "+index);
        }
    }

    void fileStream(){
        try {
            String fileName = "fileTest.txt";

            FileOutputStream fos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write("Data in".getBytes());
            fos.close();

            FileInputStream fis = getContext().openFileInput(fileName);
            String content = "";
            byte[] readByte = new byte[fis.available()];

            while(fis.read(readByte) != -1){
                content = new String(readByte);
            }
            fis.close();

            fileData = getView().findViewById(R.id.file_data);
            fileData.setText(content);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
