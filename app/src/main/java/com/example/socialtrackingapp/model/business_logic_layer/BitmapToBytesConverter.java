package com.example.socialtrackingapp.model.business_logic_layer;

import android.graphics.Bitmap;

import java.nio.ByteBuffer;

public class BitmapToBytesConverter {

    public static byte[] convert( Bitmap bitmap ) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer buffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(buffer);
        return buffer.array();
    }
}
