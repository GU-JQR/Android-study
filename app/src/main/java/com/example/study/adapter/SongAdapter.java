package com.example.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.study.R;
import com.example.study.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends BaseAdapter {
    List<Song> songs = new ArrayList<>();
    private Context context;

    public SongAdapter(List<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_music, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_img.setImageResource(songs.get(position).getImg());
//        Picasso.get().load(songs.get(position).getImg()).into(viewHolder.tv_img);
        viewHolder.tv_song.setText(songs.get(position).getSong());
        viewHolder.tv_singer.setText(songs.get(position).getSinger());
        viewHolder.tv_lyricist.setText(songs.get(position).getLyricist());
        viewHolder.tv_time.setText(songs.get(position).getTime());

        return convertView;
    }

    final class ViewHolder {
        private TextView tv_song, tv_singer, tv_lyricist, tv_time;
        private ImageView tv_img;

        public ViewHolder(View v) {
            this.tv_img = v.findViewById(R.id.item_img);
            this.tv_song = v.findViewById(R.id.item_song);
            this.tv_singer = v.findViewById(R.id.item_Singer);
            this.tv_lyricist = v.findViewById(R.id.item_lyricist);
            this.tv_time = v.findViewById(R.id.item_time);
        }
    }
}
