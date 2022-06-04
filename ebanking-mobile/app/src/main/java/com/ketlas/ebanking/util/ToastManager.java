package com.ketlas.ebanking.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.ketlas.ebanking.R;


public class ToastManager {

    public static void error(Context context, ViewGroup parent, String msg){
        show(context,parent,msg, R.drawable.bg_tast_error);

    }
    public static void success(Context context, ViewGroup parent, String msg){
        show(context,parent,msg,R.drawable.bg_tast_success);
    }
    private static void show(Context context, ViewGroup parent, String msg, int RDrawable){
        View v = LayoutInflater.from(context).inflate(R.layout.toast_item,parent,false);
        TextView msg_tv  = v.findViewById(R.id.toast_msg);
        LinearLayout ll  = v.findViewById(R.id.toast_parent);
        ll.setBackground(ContextCompat.getDrawable(context,RDrawable));
        msg_tv.setText(msg);
        Toast t = new Toast(context);
        t.setView(v);
        t.setDuration(Toast.LENGTH_LONG);
        t.show();

    }
}
