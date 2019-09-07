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
    public static final String KEY_POLICE_JPUSHID="JpushID";


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
        editor.putString(KEY_POLICE_JPUSHID,police.getJpushID());
        editor.commit();
    }

    /**
     * 取出用户信息
     */

    public static User getUserInformation(Context context){
        User user=User.Builder();
        SharedPreferences sharedPreferences= context.getSharedPreferences(KEY_USER_DATA,Context.MODE_PRIVATE);
        user.setName(sharedPreferences.getString(KEY_USER_NAME,"用户"))
                .setAdress(sharedPreferences.getString(KEY_USER_ADDRESS,"默认地址"))
                .setPhone(sharedPreferences.getString(KEY_USER_PHONE,"000-0000"));
        return user;
    }

    /**
     * 取出警方信息
     */

    public static PoliceInfo getPoliceInformation(Context context){
        PoliceInfo policeInfo=PoliceInfo.builder();
        SharedPreferences sharedPreferences= context.getSharedPreferences(KEY_POLICE_DATA,Context.MODE_PRIVATE);
        //对密码进行对称解密
        String Password= DESUtil.getDecryptString(sharedPreferences.getString(KEY_POLICE_PASSWORD,"000"));
        policeInfo.setAddress(sharedPreferences.getString(KEY_POLICE_ADDRESS,"未知地址"))
            .setPoliceNumber(sharedPreferences.getString(KEY_POLICE_NUMBER,"000000"))
                .setPassword(Password)
                .setServiceType(sharedPreferences.getInt(KEY_POLICE_SERVICE_TYPE,0))
                .setJpushID(sharedPreferences.getString(KEY_POLICE_JPUSHID,"000"));
        return policeInfo;
    }
}
