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
import com.tenhrs.courses.database.CourseDB;
import com.tenhrs.courses.model.Course;

import java.util.HashMap;
import java.util.List;

/**
 * Created by icsssd0332 on 9/6/2016.
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder>  {



    private static Context context;
    private List<Course> coursesList;
    private HashMap<Integer,Integer> focusdrawablesMap=new HashMap<>();
    private HashMap<Integer,Integer> nonFocusdrawablesMap=new HashMap<>();
    CourseDB courseDB;

    public LanguagesAdapter(Context context, List<Course> coursesList) {
        this.context = context;
        this.coursesList=coursesList;
        prepareDrawablesData();
        courseDB=new CourseDB(context);
    }

    private void prepareDrawablesData() {
        focusdrawablesMap.put(1,R.drawable.office_focus);
        focusdrawablesMap.put(2,R.drawable.html_focus);
        focusdrawablesMap.put(3,R.drawable.bigdata_focus);
        focusdrawablesMap.put(4,R.drawable.ios_focus);
        focusdrawablesMap.put(5,R.drawable.anjularjs);
        focusdrawablesMap.put(6,R.drawable.android_focus);
        focusdrawablesMap.put(7,R.drawable.pmp_focus);
        focusdrawablesMap.put(8,R.drawable.add_course);

        //non focus
        nonFocusdrawablesMap.put(1,R.drawable.office);
        nonFocusdrawablesMap.put(2,R.drawable.html);
        nonFocusdrawablesMap.put(3,R.drawable.bigdata);
        nonFocusdrawablesMap.put(4,R.drawable.ios);
        nonFocusdrawablesMap.put(5,R.drawable.anjularjs);
        nonFocusdrawablesMap.put(6,R.drawable.android);
        nonFocusdrawablesMap.put(7,R.drawable.pmp);
        nonFocusdrawablesMap.put(8,R.drawable.add_course);

    }

    @Override
    public LanguagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.grid_item_view, null);
        return new LanguagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LanguagesViewHolder holder, final int position) {
        final Course course = coursesList.get(position);
        holder.tvlangName.setText(course.getCourseName());
        int drawableID;
        if(course.getIsActive()==1){
            drawableID=focusdrawablesMap.get(course.getCourseID());
        }else{
            drawableID=nonFocusdrawablesMap.get(course.getCourseID());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawableID));
        } else {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(drawableID));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(course.getIsActive()==1){
                    courseDB.updateCourse(course.getCourseID(),0);
                    course.setIsActive(0);
                }else{
                    courseDB.updateCourse(course.getCourseID(),1);
                    course.setIsActive(1);
                }
                notifyDataSetChanged();


            }
        });


    }

    @Override
    public int getItemCount() {
        return coursesList.size()-1;
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

        }
    }
}
