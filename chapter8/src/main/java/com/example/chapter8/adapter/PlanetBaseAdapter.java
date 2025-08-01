package com.example.chapter8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter8.R;
import com.example.chapter8.Util.ToastUtil;
import com.example.chapter8.bean.Planet;

import java.util.List;

public class PlanetBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<Planet> mPlanetList;

    public PlanetBaseAdapter(Context context, List<Planet> planetList) {
        mContext = context;
        mPlanetList = planetList;
    }
    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_button, null);
        holder.ivIcon = convertView.findViewById(R.id.iv_icon);
        holder.tv_name = convertView.findViewById(R.id.tv_name);
        holder.tv_desc = convertView.findViewById(R.id.tv_desc);
        holder.btn_oper = convertView.findViewById(R.id.btn_oper);
        convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();

        }

        Planet planet = mPlanetList.get(position);
        holder.ivIcon.setImageResource(planet.image);
        holder.tv_name.setText(planet.name);
        holder.tv_desc.setText(planet.desc);
        holder.btn_oper.setOnClickListener(v ->{
            ToastUtil.show(mContext,"按钮被点击了！："+planet.name);
        });

        return convertView ;
    }

    public final class  ViewHolder {
        public ImageView ivIcon;
        public TextView tv_name;
        public TextView tv_desc;
        public Button btn_oper;

    }
}
