package com.tenhrs.courses.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tenhrs.courses.R;
import com.tenhrs.courses.adapter.LanguagesAdapter;
import com.tenhrs.courses.common.Constants;
import com.tenhrs.courses.common.Utils;
import com.tenhrs.courses.database.CourseDB;
import com.tenhrs.courses.model.Course;

import java.io.File;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    private RecyclerView list;
    private GridLayoutManager lLayout;
    private List<Course> coursesList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_languages_fragment);
        lLayout = new GridLayoutManager(this, 2);

        list = (RecyclerView) findViewById(R.id.grid_all_langs);
        list.setHasFixedSize(true);
        list.setLayoutManager(lLayout);
        prepareData();
        LanguagesAdapter languagesAdapter = new LanguagesAdapter(this, coursesList);
        list.setAdapter(languagesAdapter);

    }

    private void prepareData() {
        CourseDB courseDB = new CourseDB(this);
        coursesList = courseDB.getCourses(0);
        try {
            PackageManager m = getPackageManager();
            String s = getPackageName();
            try {
                PackageInfo p = m.getPackageInfo(s, 0);
                s = p.applicationInfo.dataDir;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("yourtag", "Error Package name not found ", e);
            }

            String outFileName = Environment.getExternalStorageDirectory().getPath() + "/"
                    + Constants.DB_NAME;

            File file = new File(outFileName);
            if (!file.exists()) {
                Utils.copyDataBase(getApplicationContext());
                Log.i("HomeActivity", "db copied.....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
