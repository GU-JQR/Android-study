package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.study.adapter.SongAdapter;
import com.example.study.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    List<Song> songs = new ArrayList<>();

    SongAdapter adapter;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        for (int i = 0; i < 100; i++) {
            Song s = new Song(R.drawable.zhoujielun, "song" + i, "singer" + i, "lyricist" + i, "12:23");
            songs.add(s);
        }
        lv = findViewById(R.id.lv);
        adapter = new SongAdapter(songs, this);
        lv.setAdapter(adapter);
    }
}