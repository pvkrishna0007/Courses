package com.tenhrs.courses.common;

import android.os.Environment;

/**
 * Created by SIVA on 06-10-2016.
 */
public class Constants {

    public static final String DB_NAME = "courses" +
            ".sqlite";
    public static final String DB_PATH = Environment.getExternalStorageDirectory().getPath() + "/CoursesDB/Database" + "/" + DB_NAME;
}

