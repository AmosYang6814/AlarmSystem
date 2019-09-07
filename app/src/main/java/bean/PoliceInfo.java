package bean;

public class PoliceInfo {

    public static final int SERVICE_TYPE_110=0;
    public static final int SERVICE_TYPE_119=1;
    public static final int SERVICE_TYPE_120=2;

    //设备号或警号都存储在PoliceNumber中
    private String policeNumber;
    private int serviceType;
    private String address;
    private String password;
    private String jpushID;
    private float longtitude;  //经度
    private float lng;   //维度


    public float getLong() {
        return longtitude;
    }

    public PoliceInfo setLong(float aLong) {
        longtitude = aLong;
        return this;
    }

    public float getLng() {
        return lng;
    }

    public PoliceInfo setLng(float lng) {
        this.lng = lng;
        return this;
    }


    /**
     * 获取警号或设备号
     * @return
     */
    public String getPoliceNumber() {
        return policeNumber;
    }

    public int getServiceType() {
        return serviceType;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
    public String getJpushID(){ return jpushID; }
    /**
     * 静态builder初始化该对象
     * @return
     */
    public static PoliceInfo builder(){
        PoliceInfo policeInfo=new PoliceInfo();
        return policeInfo;
    }


    public PoliceInfo setPoliceNumber(String PoliceNumber){
        this.policeNumber=PoliceNumber;
        return this;
    }
    public PoliceInfo setServiceType(int ServiceType){
        this.serviceType=ServiceType;
        return this;
    }
    public PoliceInfo setAddress(String Address){
        this.address=Address;
        return this;
    }
    public PoliceInfo setPassword(String Password){
        this.password=Password;
        return this;
    }

    public PoliceInfo setJpushID(String ID){
        this.jpushID=ID;
        return this;
    }


}
