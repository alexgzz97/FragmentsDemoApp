package com.definityfirst.jesusgonzalez.fragmentsdemoapp;

/**
 * Created by jesus.gonzalez on 02/02/2017.
 */

import java.io.File;
import java.util.ArrayList;


/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {

    static ArrayList<String> f = new ArrayList<String>();// list of file paths
    static ArrayList<String> v = new ArrayList<String>();// list of file paths
    static File[] listFile;
    static File[] listFiles;

    public static final String[] IMAGES = (String[]) getFromSdcard().toArray(new String[0]);
    public static final String[] VIDEOS = (String[]) getVideosFromSdcard().toArray(new String[0]);

    private Constants() {
    }

    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }

    public static class Extra {
        public static final String FRAGMENT_INDEX = "FRAGMENT_INDEX";
        public static final String IMAGE_POSITION = "IMAGE_POSITION";
    }
    public static ArrayList<String> getFromSdcard() {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"MediaImages");

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                f.add("file://"+listFile[i].getAbsolutePath());

            }
        }
        return f;
    }
    public static ArrayList<String> getVideosFromSdcard() {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"MediaVideos");

        if (file.isDirectory())
        {
            listFiles = file.listFiles();


            for (int i = 0; i < listFiles.length; i++)
            {

                v.add(listFiles[i].getAbsolutePath());

            }
        }
        return v;
    }
}
