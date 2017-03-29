package com.siliconaroma.modipl.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.siliconaroma.modipl.Data.MatchData;
import com.siliconaroma.modipl.R;

import java.util.ArrayList;

/**
 * Created by sheikb on 8/29/2016.
 */
public class ScheduleAdapter extends BaseAdapter {

    private Context mCtx;
    private ArrayList<MatchData> mData;
    private LayoutInflater mLayInf;
    String teamList1;
    /*String teamList2,gameId;*/
    public ScheduleAdapter(Context context, ArrayList<MatchData> data) {
        mCtx = context;
        mData = data;
        mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder {
        TextView team1;
        TextView team2;
        TextView date;
        TextView time;
        TextView venue;
        CardView game_card;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayInf.inflate(R.layout.list_item_schedule, null);

            viewHolder = new ViewHolder();
            viewHolder.game_card = (CardView) convertView.findViewById(R.id.game_card);
            viewHolder.team1 = (TextView) convertView.findViewById(R.id.team_1);
            viewHolder.team2 = (TextView) convertView.findViewById(R.id.team_2);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.venue = (TextView) convertView.findViewById(R.id.venue);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.team1.setText(mData.get(position).getTEAM1());
        viewHolder.team2.setText(mData.get(position).getTEAM2());
        viewHolder.date.setText(mData.get(position).getDATE());
        viewHolder.time.setText(mData.get(position).getTIME());
        viewHolder.venue.setText(mData.get(position).getVENUE());
        return convertView;
    }


}
