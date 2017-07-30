package com.ll.sqlite.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 读写文件存储数据
 * Created by LiLei on 2017/7/17.Go.
 */
public class Utils {

    /**
     * 保存信息作为文件到手机
     *
     * @param context
     * @param name
     * @param pwd
     * @return
     */
    public static boolean saveUserInfo(Context context, String name, String pwd) {
        try {
          /*  String path = "/data/data/com.ll.save/user.txt";*/
            //绝对路径
            File file = context.getFilesDir();
            File f = new File(file, "user.txt");
            FileOutputStream fos = new FileOutputStream(f);
            //编写字节
            String data = name + "##" + pwd;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取手机文件中用户名和密码
     *
     * @param context
     * @return
     */
    public static Map<String, String> getUserInfo(Context context) {
        try {
           /* String path = "/data/data/com.ll.save/user.txt";*/
            File file = context.getFilesDir();
            File f = new File(file, "user.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String text = reader.readLine();
            if (!TextUtils.isEmpty(text)) {
                String[] data = text.split("##");
                Map<String, String> userInfoMap = new HashMap<String, String>();
                userInfoMap.put("name", data[0]);
                userInfoMap.put("pwd", data[1]);

                return userInfoMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存信息作为文件到手机SD卡
     *
     * @param name
     * @param pwd
     * @return
     */
    public static boolean saveUserInfoSDCard(String name, String pwd) {
        try {
            //判断sd卡状态
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                //已有挂载SD卡
                return false;
            }

            //绝对路径
            File sdCardFile = Environment.getExternalStorageDirectory();
            File f = new File(sdCardFile, "user.txt");
            FileOutputStream fos = new FileOutputStream(f);
            //编写字节
            String data = name + "##" + pwd;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取SD卡的文件中用户名和密码
     *
     * @return
     */
    public static Map<String, String> getUserInfoSDCard() {
        try {
            //判断sd卡状态
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                //已有挂载SD卡
                return null;
            }

            File sdCardFile = Environment.getExternalStorageDirectory();
            File f = new File(sdCardFile, "user.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String text = reader.readLine();
            if (!TextUtils.isEmpty(text)) {
                String[] data = text.split("##");
                Map<String, String> userInfoMap = new HashMap<String, String>();
                userInfoMap.put("name", data[0]);
                userInfoMap.put("pwd", data[1]);

                return userInfoMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用SharedPreferences保存信息作为文件到手机
     *
     * @param name
     * @param pwd
     * @return
     */
    public static boolean saveUserInfoSP(Context context, String name, String pwd) {
        try {
            //SharedPreferences的存储路径:/data/data/包名/shared_prefs/user
            SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);

            Editor edit = sp.edit();
            //存数据
            edit.putString("name", name);
            edit.putString("pwd", pwd);
            //真正存储
            edit.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取SharedPreferences中用户名和密码
     *
     * @return
     */
    public static Map<String, String> getUserInfoSP(Context context) {

        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        String name = sp.getString("name", null);
        String pwd = sp.getString("pwd", null);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            Map<String, String> userInfoMap = new HashMap<String, String>();
            userInfoMap.put("name", name);
            userInfoMap.put("pwd", pwd);
            return userInfoMap;
        }
        return null;
    }
}
