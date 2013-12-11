/**
 * 
 */
package com.funzio.pure2D.utils.bitmap;

import java.io.FileDescriptor;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * @author sajjadtabib
 *
 */
public class BitmapUtils {

    public static BitmapFactory.Options getBitmapOptionsForSubSampling(final InputStream is) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeStream(is, null, options);
        return options;
    }

    public static Bitmap getSubSampledBitmap(final InputStream is, final int reqWidth, final int reqHeight, final Options options) {
        int sampleSize = BitmapUtils.calculateSampleSize(reqWidth, reqHeight, options);

        options.inSampleSize = sampleSize; //use sample size
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        return BitmapFactory.decodeStream(is, null, options);
    }

    public static Bitmap getSubSampledBitmap(final Resources res, final int resourceId, final int reqWidth, final int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeResource(res, resourceId, options);

        int sampleSize = BitmapUtils.calculateSampleSize(reqWidth, reqHeight, options);

        options.inSampleSize = sampleSize; //use sample size
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resourceId, options);
    }

    public static Bitmap getSubSampledBitmap(final String fileName, final int reqWidth, final int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFile(fileName, options);

        int sampleSize = BitmapUtils.calculateSampleSize(reqWidth, reqHeight, options);

        options.inSampleSize = sampleSize; //use sample size
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(fileName, options);
    }

    public static Bitmap getSubSampledBitmap(final FileDescriptor fd, final int reqWidth, final int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);

        int sampleSize = BitmapUtils.calculateSampleSize(reqWidth, reqHeight, options);

        options.inSampleSize = sampleSize; //use sample size
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    public static Bitmap getSubSampledBitmap(final InputStream is, final int reqWidth, final int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeStream(is, null, options);

        int sampleSize = BitmapUtils.calculateSampleSize(reqWidth, reqHeight, options);

        options.inSampleSize = sampleSize; //use sample size
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeStream(is, null, options);
    }

    private static int calculateSampleSize(final int reqWidth, final int reqHeight, final BitmapFactory.Options options) {

        int actualImageHeight = options.outHeight;
        int actualImageWidth = options.outWidth;

        int sampleSize = 1;

        if (reqWidth < actualImageWidth || reqHeight < actualImageHeight) {
            if (actualImageWidth > actualImageHeight) {
                sampleSize = Math.round((float) actualImageHeight / (float) reqHeight);
            } else {
                sampleSize = Math.round((float) actualImageWidth / (float) reqWidth);
            }
        }
        return sampleSize;
    }
}