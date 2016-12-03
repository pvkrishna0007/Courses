package com.tenhrs.courses.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenhrs.courses.R;
import com.tenhrs.courses.activity.HomeScreen;
import com.tenhrs.courses.activity.WeeklyCourseActivity;
import com.tenhrs.courses.common.Constants;
import com.tenhrs.courses.model.Course;

import java.util.HashMap;
import java.util.List;

/**
 * Created by icsssd0332 on 9/6/2016.
 */
public class SelectedLanguagesListAdapter extends RecyclerView.Adapter<SelectedLanguagesListAdapter.LanguagesViewHolder>  {



    private Activity context;
    private List<Course> coursesList;
    private HashMap<Integer,Integer> drawablesMap=new HashMap<>();

    public SelectedLanguagesListAdapter(Activity context, List<Course> coursesList) {
        this.context = context;
        this.coursesList=coursesList;
        prepareDrawablesData();
    }

    private void prepareDrawablesData() {
        drawablesMap.put(1,R.drawable.office_selected);
        drawablesMap.put(2,R.drawable.html_css_js_selected);
        drawablesMap.put(3,R.drawable.bigdata_selected);
        drawablesMap.put(4,R.drawable.ios_selected);
        drawablesMap.put(5,R.drawable.anjularjs);
        drawablesMap.put(6,R.drawable.android_selected);
        drawablesMap.put(7,R.drawable.pmp_selected);
        drawablesMap.put(8,R.drawable.add_course);

    }

    @Override
    public LanguagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.grid_item_view, null);
        return new LanguagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LanguagesViewHolder holder, final int position) {
        Course course = coursesList.get(position);
        holder.tvlangName.setText(course.getCourseName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawablesMap.get(course.getCourseID())));
        } else {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawablesMap.get(course.getCourseID())));
        }

        holder.rlCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == getItemCount()-1){
                    // last item
                    Intent intent=new Intent(context, HomeScreen.class);
                    context.startActivityForResult(intent,Constants.RELOAD_REQ);
                }else{
                    Intent intent=new Intent(context, WeeklyCourseActivity.class);
                    intent.putExtra(Constants.COURSEID,coursesList.get(position).getCourseID());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public static class LanguagesViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView tvlangName;
        protected ImageView imgLangIcon;
        protected CheckBox checkBox;
        protected View rlCourses;

        public LanguagesViewHolder(View itemView) {
            super(itemView);

            tvlangName = (TextView) itemView.findViewById(R.id.tv_office);
            imgLangIcon = (ImageView) itemView.findViewById(R.id.img_office);
            rlCourses = itemView.findViewById(R.id.rlCourses);
//            checkBox = (CheckBox) itemView.findViewById(R.id.cb_lang_slection);
        }


    }
}
