package com.tejasdadhe.pickachu.sample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Tejas on 02/12/15.
 */
public class PermissionProviderMarshmellow {

    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int REQUEST_CALL_PHONE = 1;
    public static final int INTERNET_PERMISSION = 16;
    public static final int READ_STORAGE = 26;
    public static final int WRITE_STORAGE = 36;
    public static final int H = 16;
    public static Snackbar snackbar;
    public static final String date_format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    Activity activity;

    public PermissionProviderMarshmellow(Activity activity) {
        this.activity = activity;
    }


    public static void checkForInternetPermission(Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.INTERNET};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity,
                    permission,
                    INTERNET_PERMISSION);
        }
    }


    public static void checkForWritingPermission(final Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity, permission, WRITE_STORAGE);
            if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(thisActivity, permission, READ_STORAGE);
            }
        }
    }


    public static void checkForReadingPermission(final Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity, permission, READ_STORAGE);
        }
    }


    public static void checkForCameraPermission(final Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.CAMERA};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity, permission, READ_STORAGE);
        }

    }


    public static void checkForContactsPermission(final Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.READ_CONTACTS};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(thisActivity,
                    permission,
                    WRITE_STORAGE);
        }
    }

    public static void checkForPhoneCall(final Activity thisActivity){
        String[] permission = new String[]{Manifest.permission.CALL_PHONE};
        //int checkPermission = ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.CALL_PHONE);
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    thisActivity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        }
    }

    public static void checkForLocationAccessPermission(final Activity thisActivity) {
        String[] permission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {ActivityCompat.requestPermissions(thisActivity,permission,WRITE_STORAGE);
        }
    }

    public static boolean checkPermissionForCamera(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestPermissionForExternalStorage(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE);
        }
    }


    public static void requestPermissionForCamera(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }


    public static boolean checkPermissionForExternalStorage(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean checkPermissionForCall(Activity activity) {
        int checkPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestPermissionForCall(Activity activity) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
            Toast.makeText(activity, "call permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        }
    }



}
