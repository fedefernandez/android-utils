package com.projectsexception.util;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class ParcelableUtilTest {

    public static final String STRING_VALUE_1 = "stringValue1";
    public static final String STRING_VALUE_2 = "stringValue2";
    public static final Float FLOAT_VALUE_1 = 0.1f;
    public static final Float FLOAT_VALUE_2 = 1.1f;
    public static final Date DATE_VALUE_1 = new Date();
    public static final Date DATE_VALUE_2 = new Date(DATE_VALUE_1.getTime() + (1000 * 60 * 60 * 24));
    public static final String[] STRING_ARRAY_VALUE_1 = new String[]{"A", "B", "C"};
    public static final String[] STRING_ARRAY_VALUE_2 = new String[]{};

    Parcel mParcel;

    @Before
    public void setup() {
        mParcel = Parcel.obtain();
    }

    @Test
    public void testString() {
        ParcelableUtil.writeString(mParcel, STRING_VALUE_1);
        assertEquals(STRING_VALUE_1, ParcelableUtil.readString(mParcel));
    }

    @Test
    public void testStringNull() {
        ParcelableUtil.writeString(mParcel, null);
        assertNull(ParcelableUtil.readString(mParcel));
    }

    @Test
    public void testStringAll() {
        ParcelableUtil.writeString(mParcel, STRING_VALUE_1);
        ParcelableUtil.writeString(mParcel, STRING_VALUE_2);
        ParcelableUtil.writeString(mParcel, null);

        assertEquals(STRING_VALUE_1, ParcelableUtil.readString(mParcel));
        assertEquals(STRING_VALUE_2, ParcelableUtil.readString(mParcel));
        assertNull(ParcelableUtil.readString(mParcel));
    }

    @Test
    public void testFloat() {
        ParcelableUtil.writeFloat(mParcel, FLOAT_VALUE_1);
        assertEquals(FLOAT_VALUE_1, ParcelableUtil.readFloat(mParcel));
    }

    @Test
    public void testFloatNull() {
        ParcelableUtil.writeFloat(mParcel, null);
        assertNull(ParcelableUtil.readFloat(mParcel));
    }

    @Test
    public void testFloatAll() {
        ParcelableUtil.writeFloat(mParcel, FLOAT_VALUE_1);
        ParcelableUtil.writeFloat(mParcel, FLOAT_VALUE_2);
        ParcelableUtil.writeFloat(mParcel, null);

        assertEquals(FLOAT_VALUE_1, ParcelableUtil.readFloat(mParcel));
        assertEquals(FLOAT_VALUE_2, ParcelableUtil.readFloat(mParcel));
        assertNull(ParcelableUtil.readFloat(mParcel));
    }

    @Test
    public void testDate() {
        ParcelableUtil.writeDate(mParcel, DATE_VALUE_1);
        assertEquals(DATE_VALUE_1, ParcelableUtil.readDate(mParcel));
    }

    @Test
    public void testDateNull() {
        ParcelableUtil.writeDate(mParcel, null);
        assertNull(ParcelableUtil.readDate(mParcel));
    }

    @Test
    public void testDateAll() {
        ParcelableUtil.writeDate(mParcel, DATE_VALUE_1);
        ParcelableUtil.writeDate(mParcel, DATE_VALUE_2);
        ParcelableUtil.writeDate(mParcel, null);

        assertEquals(DATE_VALUE_1, ParcelableUtil.readDate(mParcel));
        assertEquals(DATE_VALUE_2, ParcelableUtil.readDate(mParcel));
        assertNull(ParcelableUtil.readDate(mParcel));
    }

    @Test
    public void testStringArrayAll() {
        ParcelableUtil.writeStringArray(mParcel, STRING_ARRAY_VALUE_1);
        ParcelableUtil.writeStringArray(mParcel, STRING_ARRAY_VALUE_2);
        ParcelableUtil.writeStringArray(mParcel, null);

        assertArrayEquals(STRING_ARRAY_VALUE_1, ParcelableUtil.readStringArray(mParcel));
        assertArrayEquals(STRING_ARRAY_VALUE_2, ParcelableUtil.readStringArray(mParcel));
        assertNull(ParcelableUtil.readStringArray(mParcel));
    }

}
