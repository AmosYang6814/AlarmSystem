package bean;

import com.example.amosyang.R;
import com.google.gson.Gson;


import BluetoothModel.BluetoothConstant;

public class User {
    public static final int ALARM_110=0;
    public static final int ALARM_119=1;
    public static final int ALARM_120=2;



    private String Name;
    private String Address;
    private String phoneNumber;
    private String RamarkFor110;
    private String RamarkFor119;
    private String RamarkFor120;
    private float longtitude;  //经度
    private float lng;   //维度
    private int status;        //0.110 ,,1,119,   2,120

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getLong() {
        return longtitude;
    }

    public void setLong(float aLong) {
        longtitude = aLong;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRamarkFor110() {
        return RamarkFor110;
    }

    public String getRamarkFor119() {
        return RamarkFor119;
    }

    public String getRamarkFor120() {
        return RamarkFor120;
    }




    public static User Builder(){
        User user=new User();
        return user;
    }
    public User setName(String Name){
        this.Name=Name;
        return this;
    }
    public User setAdress(String Address){
        this.Address=Address;
        return this;
    }
    public User setPhone(String phoneNumber){
        this.phoneNumber=phoneNumber;
        return this;
    }
    public User RamarkFor110(String Remark){
        this.RamarkFor110=Remark;
        return this;
    }
    public User RamarkFor119(String Remark){
        this.RamarkFor119=Remark;
        return this;
    }
    public User RamarkFor120(String Remark){
        this.RamarkFor120=Remark;
        return this;
    }



    //编码
    public String Ecoder(User user){
        return new Gson().toJson(user);
    }

    /**
     * 解码
     * @param s
     * @return
     */
    public static User Decoder(String s){
        return new Gson().fromJson(s,User.class);
    }



}
