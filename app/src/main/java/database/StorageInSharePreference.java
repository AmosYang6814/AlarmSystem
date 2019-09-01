package database;

import android.content.Context;
import android.content.SharedPreferences;

import Util.DESUtil;
import bean.PoliceInfo;
import bean.User;

public class StorageInSharePreference {
    public static final String KEY_USER_DATA="UserData";
    public static final String KEY_POLICE_DATA="PoliceData";

    public static final String KEY_USER_NAME="Name";
    public static final String KEY_USER_PHONE="phone";
    public static final String KEY_USER_ADDRESS="Adrss";
    public static final String KEY_USER_REKARK_FOR_110="Remark110";
    public static final String KEY_USER_REKARK_FOR_119="Remark119";
    public static final String KEY_USER_REKARK_FOR_120="Remark120";

    public static final String KEY_POLICE_NUMBER="PoliceNumber";
    public static final String KEY_POLICE_PASSWORD="Password";
    public static final String KEY_POLICE_ADDRESS="PoliceAddrss";
    public static final String KEY_POLICE_SERVICE_TYPE="ServiceType";


    //存储用户信息
    public static void StorageUserData(Context context, User user){
        SharedPreferences sharedPreferences= context.getSharedPreferences(KEY_USER_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_USER_NAME,user.getName());
        editor.putString(KEY_POLICE_ADDRESS,user.getAddress());
        editor.putString(KEY_USER_PHONE,user.getPhoneNumber());
        editor.putString(KEY_USER_REKARK_FOR_110,user.getRamarkFor110());
        editor.putString(KEY_USER_REKARK_FOR_119,user.getRamarkFor119());
        editor.putString(KEY_USER_REKARK_FOR_120,user.getRamarkFor120());
        editor.commit();
    }

    //存储警方信息
    public static void StoragePoliceData(Context context, PoliceInfo police){
        SharedPreferences sharedPreferences= context.getSharedPreferences(KEY_USER_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        //对密码进行对称加密
        String Password= DESUtil.getEncryptString(police.getPassword());
        editor.putString(KEY_POLICE_NUMBER,police.getPoliceNumber());
        editor.putString(KEY_POLICE_PASSWORD,Password);
        editor.putString(KEY_POLICE_ADDRESS,police.getAddress());
        editor.putInt(KEY_POLICE_SERVICE_TYPE,police.getServiceType());
        editor.commit();
    }

    /**
     * 取出用户信息
     */

}
