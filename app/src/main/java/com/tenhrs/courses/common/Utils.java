package com.tenhrs.courses.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by SIVA on 06-10-2016.
 */
public class Utils {
    public static void copyDataBase(Context myContext) throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(Constants.DB_NAME);
        // Path to the just created empty db
        PackageManager m = myContext.getPackageManager();
        String s = myContext.getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("yourtag", "Error Package name not found ", e);
        }

        String outFileName = s
               + Constants.DB_NAME;
        File file=new File(outFileName);
        if(!file.exists()){
            file.createNewFile();
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);

            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }

    }
}
