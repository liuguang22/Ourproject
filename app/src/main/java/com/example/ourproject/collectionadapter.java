package com.example.ourproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class collectionadapter extends ArrayAdapter<Record> {


    private List<Record> data;
    private Context context;
    private int resourceId;

    public collectionadapter(Context context,
                             int resourceId, List<Record> data) {
        super(context, resourceId, data);

        this.context = context;
        this.data = data;
        this.resourceId = resourceId;

    }


    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Record sh = getItem(position);
        View view;

        final ViewHolder vh;

        if (convertView == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(resourceId, parent, false);

            vh = new ViewHolder();
            vh.ivPrice = view.findViewById(R.id.tv_price);
            vh.ivTitle = view.findViewById(R.id.tv_title);
            vh.ivImage = view.findViewById(R.id.iv_image);

            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
//        if (sh.getImageUrlList() != null ) {
//            Glide.with(context)
//                    .load(sh.getImageUrlList())
//                    .into(vh.ivImage);
//        }

        if (sh.getImageUrlList() != null && sh.getImageUrlList().size() > 0) {

            Glide.with(context)
                    .load(sh.getImageUrlList().get(0))
                    .into(vh.ivImage);
        }
        vh.ivTitle.setText(sh.getContent());
        vh.ivPrice.setText(sh.getPrice() + "");

        return view;
    }

    class ViewHolder {
        TextView ivTitle;
        ImageView ivImage;
        TextView ivPrice;

    }


}


