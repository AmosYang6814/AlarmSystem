package Util;

public class utilTools {

    //产生指定数组
    public static int[] Getrandomarray(int number,int start, int field){
        int[] ran=new int[number];
        for(int i=0;i<number;i++){
            ran[i]=(int)(field*Math.random()+start);
        }
        return ran;
    }
}
