package com.deepbay.cameraapp;

import android.graphics.Matrix;
import android.util.Log;

import androidx.exifinterface.media.ExifInterface;

public class ExifUtils {
    /**
     * Transforms rotation and mirroring information into one of the [ExifInterface] constants
     */
    public static int computeExifOrientation(int rotationDegrees, boolean mirrored) {
        switch (rotationDegrees) {
            case 0:
                if (!mirrored) {
                    return ExifInterface.ORIENTATION_NORMAL;
                } else {
                    return ExifInterface.ORIENTATION_FLIP_HORIZONTAL;
                }
            case 90:
                if (!mirrored) {
                    return ExifInterface.ORIENTATION_ROTATE_90;
                } else {
                    return ExifInterface.ORIENTATION_TRANSPOSE;
                }
            case 180:
                if (!mirrored) {
                    return ExifInterface.ORIENTATION_ROTATE_180;
                } else {
                    return ExifInterface.ORIENTATION_FLIP_VERTICAL;
                }
            case 270:
                if (!mirrored) {
                    return ExifInterface.ORIENTATION_TRANSVERSE;
                } else {
                    return ExifInterface.ORIENTATION_ROTATE_270;
                }
            default:
                return ExifInterface.ORIENTATION_UNDEFINED;
        }
    }

    public static Matrix decodeExifOrientation(int exifOrientation) {
        Matrix matrix = new Matrix();

        // Apply transformation corresponding to declared EXIF orientation
        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_NORMAL:
            case ExifInterface.ORIENTATION_UNDEFINED:
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90F);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180F);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270F);
                break;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.postScale(-1F, 1F);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.postScale(1F, -1F);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.postScale(-1F, 1F);
                matrix.postRotate(270F);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.postScale(-1F, 1F);
                matrix.postRotate(90F);
                break;
            default:
                Log.e("ExifUtils", "Invalid orientation: $exifOrientation");
        }

        // Return the resulting matrix
        return matrix;
    }
}
