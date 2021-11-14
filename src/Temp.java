import java.util.ArrayList;

public class Temp {
    String str;
    ArrayList<Integer> arrayList;
    Temp(String str, ArrayList<Integer> arrayList){
        this.str = str;
        this.arrayList = arrayList;
    }
    public String getStr(){
        return str;
    }
    public ArrayList<Integer> getArrayList(){
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
