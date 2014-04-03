package com.projectsexception.util;

import android.os.Parcel;

import java.util.Date;

public final class ParcelableUtil {

    private ParcelableUtil() {

    }

    public static void writeString(Parcel dest, String value) {
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(value);
        }
    }

    public static String readString(Parcel in) {
        String value = null;
        if (in.readByte() == 1) {
            value = in.readString();
        }
        return value;
    }

    public static void writeFloat(Parcel dest, Float value) {
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(value);
        }
    }

    public static Float readFloat(Parcel in) {
        Float value = null;
        if (in.readByte() == 1) {
            value = in.readFloat();
        }
        return value;
    }

    public static void writeDate(Parcel dest, Date value) {
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(value.getTime());
        }
    }

    public static Date readDate(Parcel in) {
        Date value = null;
        if (in.readByte() == 1) {
            value = new Date(in.readLong());
        }
        return value;
    }

    public static void writeStringArray(Parcel dest, String[] value) {
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeStringArray(value);
        }
    }

    public static String[] readStringArray(Parcel in) {
        String[] value = null;
        if (in.readByte() == 1) {
            value = in.createStringArray();
        }
        return value;
    }
}
