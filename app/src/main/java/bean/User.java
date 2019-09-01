package bean;

import com.example.amosyang.R;
import com.google.gson.Gson;


import BluetoothModel.BluetoothConstant;

public class User {



    private String Name;
    private String Address;
    private String phoneNumber;
    private String RamarkFor110;
    private String RamarkFor119;
    private String RamarkFor120;


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
