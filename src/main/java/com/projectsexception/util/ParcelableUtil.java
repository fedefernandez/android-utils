package com.projectsexception.util;

import android.os.Parcel;

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
}
