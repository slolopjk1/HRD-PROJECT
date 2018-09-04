package com.example.usertop.misproject;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemfomatAdapter extends BaseAdapter {
    String count;
    private Context context;
    private  int layout;
    private ArrayList<Item> ItemsList;

    public ItemfomatAdapter(Context context, int layout, ArrayList<Item> ItemsList) {
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
        ImageView Image;
        TextView Model_name,Manufacture,Item_name,Standard,Get_cost,Get_count ;
        Spinner Dep_code, Use_where;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.Model_name = (TextView) row.findViewById(R.id.모델명);
            holder.Manufacture = (TextView) row.findViewById(R.id.제조업체명);
            holder.Item_name = (TextView) row.findViewById(R.id.품명);
            holder.Standard = (TextView) row.findViewById(R.id.규격);
            holder.Get_count = (TextView) row.findViewById(R.id.수량);
            holder.Get_cost = (TextView) row.findViewById(R.id.취득단가);
            holder.Image = (ImageView) row.findViewById(R.id.사진);
            holder.Dep_code = row.findViewById(R.id.sp_btn1);
            holder.Use_where = row.findViewById(R.id.sp_btn2);
            row.setTag(holder);

        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = ItemsList.get(position);
        holder.Model_name.setText(item.getModel_name());
        holder.Manufacture.setText(item.getManufacture());
        holder.Item_name.setText(item.getItem_name());
        holder.Standard.setText(item.getStandard());
        holder.Get_count.setText(item.getCount());
        holder.Get_cost.setText(item.getGet_cost());
        byte[] itemImage = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        holder.Image.setImageBitmap(bitmap);

        return row;
    }
}

