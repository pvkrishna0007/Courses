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

import java.util.List;

/**
 * Created by icsssd0332 on 9/6/2016.
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder>  {



    private static Context context;
    private List<Course> coursesList;

    public LanguagesAdapter(Context context, List<Course> coursesList) {
        this.context = context;
        this.coursesList=coursesList;
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

        holder.tvlangName.setText(coursesList.get(position).getCourseName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_dialog_email));
        } else {
            holder.imgLangIcon.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_dialog_email));
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

            tvlangName = (TextView) itemView.findViewById(R.id.tv_lang_name);
            imgLangIcon = (ImageView) itemView.findViewById(R.id.img_lang_icon);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_lang_slection);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }
    }
}
