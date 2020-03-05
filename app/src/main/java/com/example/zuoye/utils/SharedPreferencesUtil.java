package com.example.zuoye.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zuoye.apps.MyApp;

public class SharedPreferencesUtil {

    private static SharedPreferences.Editor editor;
    private static SharedPreferencesUtil instance;
    private SharedPreferences sp;
    public SharedPreferencesUtil(){
        sp = MyApp.app.getSharedPreferences("shop", Context.MODE_PRIVATE);
    }

    //添加ui模式
    public static void addModeUI(Context context, boolean bool){
        editor = context.getSharedPreferences("ModeUI", Context.MODE_PRIVATE).edit();
        editor.putBoolean("mode_ui",bool);
        editor.commit();
    }

    public static void setLogin(Context context, String username,String password){
        editor = context.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }
    public static SharedPreferencesUtil getInstance(){
        if(instance == null){
            synchronized (SharedPreferencesUtil.class){
                if(instance == null){
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 设置数据
     * @param key
     * @param value
     */
    public void setValue(String key,Object value){
        SharedPreferences.Editor editor = sp.edit();
        if(value instanceof String){
            editor.putString(key, (String) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if(value instanceof Long){
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }

    public String getString(String key){
        return sp.getString(key,"");
    }

    public int getInt(String key){
        return sp.getInt(key,0);
    }

    public Boolean getBoolean(String key){
        return sp.getBoolean(key,false);
    }

    public float getFloat(String key){
        return sp.getFloat(key,0);
    }

    public Long getLong(String key){
        return sp.getLong(key,0);
    }

}
