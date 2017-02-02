package com.definityfirst.jesusgonzalez.fragmentsdemoapp;

/**
 * Created by jesus.gonzalez on 02/02/2017.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import org.apache.commons.io.FilenameUtils;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Belal on 18/09/16.
 */


public class TextFragment extends Fragment {
    static ArrayList<String> t = new ArrayList<String>();
    static ArrayList<String> tn = new ArrayList<String>();// list of file paths
    static File[] listText;
    private ListView listView;
    ArrayAdapter<String> arrayAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.textfragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getNamesFromSdcard());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                try {
                    builder.setMessage(readFile(listText[position].toString()))
                            .setCancelable(true)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Archivos de Texto");
        Log.d("Archivos de Texto: ",getFromSdcard().toString());
    }

    public static ArrayList<String> getFromSdcard() {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"MediaText");

        if (file.isDirectory())
        {
            listText = file.listFiles();
            t.clear();


            for (int i = 0; i < listText.length; i++)
            {

                t.add("file://"+listText[i].getAbsolutePath());
                Log.d("file", listText[i].getName());
        }
        }
        return t;
    }

    public static ArrayList<String> getNamesFromSdcard() {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"MediaText");

        if (file.isDirectory())
        {
            listText = file.listFiles();
            tn.clear();


            for (int i = 0; i < listText.length; i++)
            {
                String basename = FilenameUtils.getBaseName(listText[i].toString());
                tn.add(basename);
            }
        }
        return tn;
    }
    private static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            stream.close();
        }
    }

}


