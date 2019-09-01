package bean;

public class PoliceInfo {

    public static final int SERVICE_TYPE_110=110;
    public static final int SERVICE_TYPE_119=119;
    public static final int SERVICE_TYPE_120=120;

    //设备号或警号都存储在PoliceNumber中
    private String PoliceNumber;
    private int ServiceType;
    private String Address;
    private String Password;


    /**
     * 获取警号或设备号
     * @return
     */
    public String getPoliceNumber() {
        return PoliceNumber;
    }

    public int getServiceType() {
        return ServiceType;
    }

    public String getAddress() {
        return Address;
    }

    public String getPassword() {
        return Password;
    }

    /**
     * 静态builder初始化该对象
     * @return
     */
    public static PoliceInfo builder(){
        PoliceInfo policeInfo=new PoliceInfo();
        return policeInfo;
    }

    /**
     * 设置警号属性
     * @param PoliceNumber
     * @return
     */
    public PoliceInfo setPoliceNumber(String PoliceNumber){
        this.PoliceNumber=PoliceNumber;
        return this;
    }
    public PoliceInfo setServiceType(int ServiceType){
        this.ServiceType=ServiceType;
        return this;
    }
    public PoliceInfo setAddress(String Address){
        this.Address=Address;
        return this;
    }
    public PoliceInfo setPassword(String Password){
        this.Password=Password;
        return this;
    }


}
