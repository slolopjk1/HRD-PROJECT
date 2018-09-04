package com.example.usertop.misproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;


import java.util.ArrayList;

public class ItemfomatAdapter2 extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Item> ItemsList;

    public ItemfomatAdapter2(Context context, int layout, ArrayList<Item> ItemsList) {
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
        ImageView 사진R;
        EditText 모델명R,제조업체명R,품명R,규격R,Get_count,취득단가R,부서명R, 위치R,물품목록번호R;;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.모델명R = (EditText) row.findViewById(R.id.모델명R);
            holder.제조업체명R = (EditText) row.findViewById(R.id.제조업체명R);
            holder.품명R = (EditText) row.findViewById(R.id.품명R);
            holder.규격R = (EditText) row.findViewById(R.id.규격R);
            holder.물품목록번호R=row.findViewById(R.id.물품목록번호R);
            //holder.Get_count = (EditText) row.findViewById(R.id.수량R);
            holder.취득단가R = (EditText) row.findViewById(R.id.취득단가R);
            holder.사진R = (ImageView) row.findViewById(R.id.사진R);
            holder.부서명R = row.findViewById(R.id.부서명R);
            holder.위치R = row.findViewById(R.id.위치R);
            row.setTag(holder);

        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = ItemsList.get(position);
        holder.모델명R.setText(item.getModel_name());
        holder.제조업체명R.setText(item.getManufacture ());
        holder.품명R.setText(item.getItem_name ());
        holder.규격R.setText(item.getStandard());
        holder.부서명R.setText(item.getDep_code());
        holder.위치R.setText(item.getUse_where());
        holder.물품목록번호R.setText(item.getSN());
        //holder.Get_count.setText(item.getCount());
        //holder.수량R.setText(item.get수량());
        holder.취득단가R.setText(item.getGet_cost());
        byte[] itemImage = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        holder.사진R.setImageBitmap(bitmap);

        return row;
    }
}

