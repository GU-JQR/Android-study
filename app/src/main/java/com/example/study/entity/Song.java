package com.example.study.entity;

public class Song {
    private int img;
    private String song;
    private String singer;
    private String lyricist;
    private String time;

    public Song() {
    }

    public Song(int img, String song, String singer, String lyricist, String time) {
        this.img = img;
        this.song = song;
        this.singer = singer;
        this.lyricist = lyricist;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "img='" + img + '\'' +
                ", song='" + song + '\'' +
                ", singer='" + singer + '\'' +
                ", lyricist='" + lyricist + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
