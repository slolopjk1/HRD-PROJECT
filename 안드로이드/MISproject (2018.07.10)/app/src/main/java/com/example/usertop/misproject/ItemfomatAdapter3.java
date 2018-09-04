package com.example.usertop.misproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class ItemfomatAdapter3 extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Item> ItemsList;

    public ItemfomatAdapter3(Context context, int layout, ArrayList<Item> ItemsList) {
        this.context = context;
        this.layout = layout;
        this.ItemsList = ItemsList;
    }

    @Override
    public int getCount() {
        return ItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return ItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        EditText Item_num, Get_date,Pro_date ;
        CheckBox checkBox = null;

    }
    public void checkedConfirm(int position) {
        Log.e("onItemClick", "클릭->"+position);
        Item item = ItemsList.get(position);
        item.setisChecked(!item.getisChecked());
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.Item_num = (EditText) row.findViewById(R.id.item_num);
            holder.Get_date = (EditText) row.findViewById(R.id.get_date);
            holder.Pro_date = (EditText) row.findViewById(R.id.pro_date);
            holder.checkBox = row.findViewById(R.id.checkbox);

            row.setTag(holder);

        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = ItemsList.get(position);
        holder.Item_num.setText(item.getItem_num());
        holder.Get_date.setText(item.getGet_date());
        holder.Pro_date.setText(item.getPro_date());
        holder.checkBox.setClickable(false);
        holder.checkBox.setFocusable(false);

        holder.checkBox.setChecked(item.getisChecked());
        return row;

    }
}




