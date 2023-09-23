package com.example.ourproject;


import android.provider.BaseColumns;

public final class shopConstants {
    private shopConstants() {}

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_news";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}

