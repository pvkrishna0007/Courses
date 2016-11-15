package com.tenhrs.courses.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenhrs.courses.R;
import com.tenhrs.courses.model.Course;

import java.util.HashMap;
import java.util.List;

/**
 * Created by icsssd0332 on 9/6/2016.
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder>  {



    private static Context context;
    private List<Course> coursesList;
    private HashMap<Integer,Integer> drawablesMap=new HashMap<>();

    public LanguagesAdapter(Context context, List<Course> coursesList) {
        this.context = context;
        this.coursesList=coursesList;
        prepareDrawablesData();
    }

    private void prepareDrawablesData() {
        drawablesMap.put(1,R.drawable.msoffice);
        drawablesMap.put(2,R.drawable.html_selector);
        drawablesMap.put(3,R.drawable.bigdata_selector);
        drawablesMap.put(4,R.drawable.ios_selecter);
        drawablesMap.put(5,R.drawable.angulerjs_selector);
        drawablesMap.put(6,R.drawable.android_selector);
        drawablesMap.put(7,R.drawable.pmp_selector);
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
    public void onBindViewHolder(LanguagesViewHolder holder, int position) {
        Course course = coursesList.get(position);
        holder.tvlangName.setText(course.getCourseName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawablesMap.get(course.getCourseID())));
        } else {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawablesMap.get(course.getCourseID())));
        }

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public static class LanguagesViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvlangName;
        protected ImageView imgLangIcon;
        protected CheckBox checkBox;

        public LanguagesViewHolder(View itemView) {
            super(itemView);

            tvlangName = (TextView) itemView.findViewById(R.id.tv_office);
            imgLangIcon = (ImageView) itemView.findViewById(R.id.img_office);
//            checkBox = (CheckBox) itemView.findViewById(R.id.cb_lang_slection);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }
    }
}
