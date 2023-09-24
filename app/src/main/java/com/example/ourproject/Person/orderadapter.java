package com.example.ourproject.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ourproject.R;
import com.example.ourproject.home.Record;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class orderadapter extends ArrayAdapter<Record> {


    private List<Record> data;
    private Context context;
    private int resourceId;

    public orderadapter(Context context,
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
            vh.ivCtime = view.findViewById(R.id.Ctime);
            vh.ivSeller = view.findViewById(R.id.seller);
            //vh.ivImage = view.findViewById(R.id.iv_image);

            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
//        Log.d("1","23423434234343423243");

//        if (sh.getImageUrlList() != null && sh.getImageUrlList().size() > 0) {
//
//            Glide.with(context)
//                    .load(sh.getImageUrlList().get(0))
//                    .into(vh.ivImage);
//            //Log.d("1","23423434234343423243");
//        }
        vh.ivTitle.setText("详情："+sh.getgoodsDescription());
        vh.ivPrice.setText("价格："+sh.getPrice() + "");
        vh.ivCtime.setText(getCurrentTime(sh.getCreateTime()));
        vh.ivSeller.setText("卖家："+sh.getsellerName());


        return view;
    }

    public String getCurrentTime(long dates) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        String date = sDateFormat.format(new Date(dates));
        return date;
    }

    class ViewHolder {
        TextView ivTitle;
        //ImageView ivImage;
        TextView ivPrice;
        TextView ivSeller;
        TextView ivCtime;

    }

}
