package com.alphawallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.alphawallet.app.ui.HomeActivity;
import com.alphawallet.app.ui.boc.MainActivity;

public class HomeRouter {
    public void open(Context context, boolean isClearStack) {
//        Intent intent = new Intent(context, HomeActivity.class);
        Intent intent = new Intent(context, MainActivity.class);
        if (isClearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
    }
}
