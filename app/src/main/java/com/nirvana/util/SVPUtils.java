package com.nirvana.util;

import android.content.Context;

import com.bigkoo.svprogresshud.SVProgressHUD;

/**
 * Created by Nirvana on 2016/6/24.
 */
public class SVPUtils {
    private static Context context;
    private static SVProgressHUD progressHUD;

    public static synchronized SVProgressHUD getInstance(Context context) {
        if(progressHUD == null || getContext() != context) {
            setContext(context);
            progressHUD = new SVProgressHUD(context);
        }

        return progressHUD;
    }

    public static void tipInfo(Context context, String message) {
        SVProgressHUD svp = getInstance(context);
        svp.showInfoWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public static void tipOk(Context context, String message) {
        SVProgressHUD svp = getInstance(context);
        svp.showSuccessWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public static void tipError(Context context, String message) {
        SVProgressHUD svp = getInstance(context);
        svp.showErrorWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        SVPUtils.context = context;
    }
}
