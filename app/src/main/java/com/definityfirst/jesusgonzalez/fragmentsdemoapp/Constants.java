package com.definityfirst.jesusgonzalez.fragmentsdemoapp;

/**
 * Created by jesus.gonzalez on 02/02/2017.
 */

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {
    static Bitmap thumb;

    static ArrayList<String> f = new ArrayList<String>();// list of file paths
    static ArrayList<String> v = new ArrayList<String>();// list of file paths
    static File[] listFile;
    static File[] listFiles;
    static File[] listFilesth;

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
        File fileth= new File(android.os.Environment.getExternalStorageDirectory(),"MediaVideosThumbs");

        if (file.isDirectory())
        {
            listFiles = file.listFiles();
            listFilesth = fileth.listFiles();

            for (int i = 0; i < listFiles.length; i++)
            {
                v.add("file://"+listFiles[i].getAbsolutePath());

            }

        }
        return v;
    }

}
