package com.example.listviewp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Person> personList;

    public PersonAdapter(Context context, int layout, List<Person> personList) {
        this.context = context;
        this.layout = layout;
        this.personList = personList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //view Holder
    private class ViewHolder{
        ImageView imgHinh;
        TextView tvName;
        TextView tvPhone;
        TextView tvDescribe;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout, null);

            //ánh xạ
            holder = new ViewHolder();
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.avt);
            holder.tvName = (TextView) convertView.findViewById(R.id.name);
            holder.tvPhone = (TextView)convertView.findViewById(R.id.phone);
            holder.tvDescribe = (TextView) convertView.findViewById(R.id.describe);

            convertView.setTag(holder);//sét tag cho nó
        }else {
            //là đã đc ánh xạ rồi thì vào else
            holder = (ViewHolder) convertView.getTag(); //bên trên sét tag r chỉ cần lấy ra thôi
        }

        //gán dữ liệu
        Person a = personList.get(position);
        holder.imgHinh.setImageResource(a.getAvt());
        holder.tvName.setText(a.getName());
        holder.tvPhone.setText(a.getPhone());
        holder.tvDescribe.setText(a.getDescribe());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_list);
        convertView.startAnimation(animation);

        return convertView;
    }
}
