package com.adintech.farmersguide.Util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.preference.AppPreferencesManager;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * This class is used to define the common functions to be used in the application
 *
 * @author Ritesh Pandhurkar
 * @author Shivam Jamaiwar
 * @version 1.7
 */
public class Utility {

    Context context;
    private int mYear, mMonth, mDay, mHour, mMinute;

    public static final String ENGLISH_LANG_CODE = "en";
    public static final String HINDI_LANG_CODE = "hi";
    public static final String MARATHI_LANG_CODE = "mr";

    public Utility(Context context) {
        this.context = context;
    }

    /**
     * print any data to log
     *
     * @param key  key for identification
     * @param data data to show in logcat
     */
    public static void printLogs(String key, String data) {
        Log.e(key, data);
    }

    /**
     * Show toaster.
     *
     * @param context Current activity context.
     * @param message Message to be show on toaster.
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show snackbar.
     *
     * @param view    Current view.
     * @param message Message to be show on snackbar.
     * @param length  Showing length.
     * @return Snackbar instance.
     */
    public static Snackbar showErrorSnackBar(View view, String message, int length) {
        Snackbar s = Snackbar
                .make(view, message, length);
        View mview = s.getView();
        mview.setBackgroundColor(Color.RED);
        return s;
    }

    /**
     * Convert date from one date format to another date format.
     *
     * @param date           the date we want to change
     * @param currentFormat  current date format
     * @param requiredFormat date format which you want to convert
     * @return convertable date
     */
    public static String convertDate(String date, String currentFormat, String requiredFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(currentFormat);
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat(requiredFormat);
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * convert string date to Date object
     *
     * @param dateString date in the form of string
     * @param dateFormat format of date which you want to convert
     * @return converted date object
     */
    public static Date convertStringToDate(String dateString, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateInString = dateString;
        Date date = null;
        try {
            date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert date object to string in given date format.
     *
     * @param date       Object of Date, Which we have to convert.
     * @param dateFormat Date format.
     * @return String date in given date format.
     */
    public static String convertDateToString(Date date, String dateFormat) {
        DateFormat dateFormatPattern = new SimpleDateFormat(dateFormat);
        String strDate = dateFormatPattern.format(date);
        return strDate;
    }

    /**
     * Calculate days between two dates.
     *
     * @param d1 1st date
     * @param d2 2nd date
     * @return no. of days between given dates.
     */
    public static long getDifferenceBetweenTwoDate(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Calculate age from given date of birth.
     *
     * @param year  Year of given date.
     * @param month Month of given date.
     * @param day   Day of given date.
     * @return
     */
    public static int getAge(int year, int month, int day) {

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, noofyears;

        y = cal.get(Calendar.YEAR);// current year ,
        m = cal.get(Calendar.MONTH);// current month
        d = cal.get(Calendar.DAY_OF_MONTH);//current day
        cal.set(year, month, day);// here ur date
        noofyears = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --noofyears;
        }
        if (noofyears < 0)
            throw new IllegalArgumentException("age < 0");
        System.out.println(noofyears);
        return noofyears;
    }

    /**
     * check is network connectivity is available or not.
     *
     * @return boolean value "true" if network available.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean value = activeNetworkInfo != null && activeNetworkInfo.isConnected() || mWifi != null && mWifi.isConnected();

        if (!value) {
            Utility.showToast(context, "Please check internet connection");
        }
        return value;
    }

    /**
     * Check location permission.
     *
     * @param activity
     * @return
     */
    public static boolean doCheckLocationPermission(Activity activity, boolean isCallForRequest) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (isCallForRequest) {
                doRequestForLocationPermission(activity);
            }
            return false;
        } else {
            return true;
        }
    }

    public static void doRequestForLocationPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConstants.RUNTIME_PERMISSION_REQUEST_CODE.LOCATION_PERMISSION_REQUEST);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConstants.RUNTIME_PERMISSION_REQUEST_CODE.LOCATION_PERMISSION_REQUEST);
        }
    }

    /**
     * make spannable from item value and return spannable text
     *
     * @param filter    text to be filter
     * @param itemValue item which you want to filter
     * @return spannable text
     */
    public static Spannable highlightSearchText(String filter, String itemValue) {

        if (!filter.equals("")) {
            // find all occurrences forward
            Spannable spannable = new SpannableString(itemValue);
            int n = itemValue.indexOf(filter, -1 + 1);
            for (int i = 0; (i = itemValue.toLowerCase().indexOf(filter.toLowerCase(), i)) != -1; i++) {
                int startPos = i;
                int endPos = startPos + filter.length();

                if (startPos != -1) // This should always be true, just a sanity check
                {
                    ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.rgb(33, 150, 243)});
                    TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

                    spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
            }
            return spannable;
        }
        return null;
    }

    /**
     * get metioned json file data in single string variable and return that string
     *
     * @param fileName json file name
     * @return json file data into single variable
     */
    public static String loadJSONFromAsset(Context context, String fileName) {
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }

    /**
     * check entered mail id is correct or not
     *
     * @param email entered mail id
     * @return boolean value, if entered mail id is correct return true otherwise return false
     */
    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * check entered contact no is correct or not
     *
     * @param contact entered contact no.
     * @return
     */
    public static boolean checkContactNo(String contact) {
        if (contact == null) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN = "^[0-9]{10}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    public static boolean checkValidPassword(String password) {
        if (password == null) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkValidUserName(String username) {
        if (username == null) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String USERNAME_PATTERN = "^[a-z0-9_-]{6,14}$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * Minutes convert into days and hours.
     *
     * @param time time in minutes.
     * @return Converted string.
     */
    public static String timeConvert(long time) {

        long days = time / 24 / 60;
        long hours = time / 60 % 24;
        long minutes = time % 60;

        if (hours == 0) {
            return minutes + " min";
        } else if (days == 0) {
            return hours + " hrs " + minutes + " min";
        } else {
            if (days > 1) {
                return days + " days " + hours + " hrs " + minutes + " min";
            } else {
                return days + " day " + hours + " hrs " + minutes + " min";
            }
        }
    }

    // 1 minute = 60 seconds
    // 1 hour = 60 x 60 = 3600
    // 1 day = 3600 x 24 = 86400
    public static String getsplitTime(long time) {
        String result = null;

        //milliseconds
        long different = time;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if (elapsedDays > 0) {
            result = elapsedDays + " day " + elapsedHours + "hour " + elapsedMinutes + " min";
        } else if (elapsedHours > 0) {
            result = elapsedHours + "h " + elapsedMinutes + "m";
        } else if (elapsedMinutes > 0) {
            result = elapsedMinutes + "m";
        }

        return result;
    }

    //-------------------------------------------------------------------------------

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getTimeAgo(Date date) {

        long time = date.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = getCurrentTime();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "Just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a min ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " min ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {

            String requiredDateFormat = "Yesterday"
                    + " at "
                    + convertDate(calendar.getTime().toString(), AppConstants.TIMESTAMP_DATE_FORMAT, AppConstants.TIME_FORMAT);
            return requiredDateFormat;
        } else {
            if (diff / DAY_MILLIS <= 3) {

                String requiredDateFormat = diff / DAY_MILLIS + " days ago"
                        + " at "
                        + convertDate(calendar.getTime().toString(), AppConstants.TIMESTAMP_DATE_FORMAT, AppConstants.TIME_FORMAT);
                return requiredDateFormat;
            } else {
                String requiredDateFormat = convertDate(calendar.getTime().toString(), AppConstants.TIMESTAMP_DATE_FORMAT, "dd MMM")
                        + " at "
                        + convertDate(calendar.getTime().toString(), AppConstants.TIMESTAMP_DATE_FORMAT, AppConstants.TIME_FORMAT);
                return requiredDateFormat;
            }
        }
    }

    private static long getCurrentTime() {
        Date currentDate = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentDate);

        return cal1.getTime().getTime();
    }

    private static long changeTimezoneFromUTCtoIST(Date serverTimeStamp) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(serverTimeStamp);
        calendar.add(Calendar.HOUR_OF_DAY, 5);
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime().getTime();
    }

    //-------------------------------------------------------------------------------

    /**
     * Get real path from given Uri.
     *
     * @param context    Current activity content.
     * @param contentUri Uri of file.
     * @return Path in string.
     */
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    /**
     * SDF to generate a unique name for our compress file.
     */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyymmddhhmmss", Locale.getDefault());

    /**
     * compress the file/photo from @param <b>path</b> to a private location on the current device and return the compressed file.
     *
     * @param context Current android Context
     * @param path    The original image path
     * @return
     * @throws IOException
     */
    public static File getCompressed(Context context, String path) throws IOException {

        if (context == null)
            throw new NullPointerException("Context must not be null.");
        //getting device external cache directory, might not be available on some devices,
        // so our code fall back to internal storage cache directory, which is always available but in smaller quantity
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null)
            //fall back
            cacheDir = context.getCacheDir();

        String rootDir = cacheDir.getAbsolutePath() + "/ImageCompressor";
        File root = new File(rootDir);

        //Create ImageCompressor folder if it doesnt already exists.
        if (!root.exists())
            root.mkdirs();

        //decode and resize the original bitmap from @param path.
        Bitmap bitmap = decodeImageFromFiles(path, /* your desired width*/300, /*your desired height*/ 300);

        //create placeholder for the compressed image file
        File compressed = new File(root, SDF.format(new Date()) + ".jpg" /*Your desired format*/);

        //convert the decoded bitmap to stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        /*compress bitmap into byteArrayOutputStream
            Bitmap.compress(Format, Quality, OutputStream)
            Where Quality ranges from 1 - 100.
         */
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        /*
        Right now, we have our bitmap inside byteArrayOutputStream Object, all we need next is to write it to the compressed file we created earlier,
        java.io.FileOutputStream can help us do just That!
         */
        FileOutputStream fileOutputStream = new FileOutputStream(compressed);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();

        fileOutputStream.close();

        //File written, return to the caller. Done!
        return compressed;
    }

    public static Bitmap decodeImageFromFiles(String path, int width, int height) {
        BitmapFactory.Options scaleOptions = new BitmapFactory.Options();
        scaleOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, scaleOptions);
        int scale = 1;
        while (scaleOptions.outWidth / scale / 2 >= width
                && scaleOptions.outHeight / scale / 2 >= height) {
            scale *= 2;
        }
        // decode with the sample size
        BitmapFactory.Options outOptions = new BitmapFactory.Options();
        outOptions.inSampleSize = scale;
        return BitmapFactory.decodeFile(path, outOptions);
    }

    /**
     * Check given service name running or not.
     *
     * @param serviceName Service name.
     * @param context     Current android Context
     * @return boolean value "true" if given service is running in foreground.
     */
    public static boolean isServiceRunning(String serviceName, Context context) {
        boolean serviceRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningServiceInfo runningServiceInfo = i
                    .next();

            if (runningServiceInfo.service.getClassName().equalsIgnoreCase(serviceName)) {

                if (runningServiceInfo.foreground) {
                    //service run in foreground
                    serviceRunning = true;
                    break;
                }
            }
        }
        return serviceRunning;
    }

    /**
     * check phone state permission.
     *
     * @param activity
     * @return
     */
    public static boolean checkPhoneStatePermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, AppConstants.RUNTIME_PERMISSION_REQUEST_CODE.PHONE_STATE_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, AppConstants.RUNTIME_PERMISSION_REQUEST_CODE.PHONE_STATE_PERMISSION_REQUEST);
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * get device id(IMEI number) for android version below Q and get android id  for android version Q and above.
     *
     * @param activity
     * @return
     */
    public static String getDeviceId(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String androidId = Settings.Secure.getString(activity.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            return androidId;
        } else if (Utility.checkPhoneStatePermission(activity)) {
            TelephonyManager telephonyManager;
            telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

            /*
             * getDeviceId() returns the unique device ID.
             * For example,the IMEI for GSM and the MEID or ESN for CDMA phones.
             */
            String deviceId = telephonyManager.getDeviceId();
            return deviceId;
        } else {
            return null;
        }
    }

    public static void setLocale(Context context, String localeCode) {
        try {
            Resources resources = context.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(new Locale(localeCode.toLowerCase()));
            resources.updateConfiguration(configuration, displayMetrics);
            configuration.locale = new Locale(localeCode.toLowerCase());
            resources.updateConfiguration(configuration, displayMetrics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentLocale(Context context) {
        String current_code = ENGLISH_LANG_CODE;
        if (AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, context) != null) {
            current_code = AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, context);
        } else {
            current_code = AppConstants.ENGLISH_LANG_CODE;
        }

        return current_code;
    }
}
