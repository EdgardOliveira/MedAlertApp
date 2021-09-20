package br.com.technologies.venom.medalertapp.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class Conversor {
    @TypeConverter
    public static Date paraDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long paraTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
