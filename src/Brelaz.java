import java.util.ArrayList;

public class Brelaz {
    String str;
    ArrayList<Integer> arrayList;
    int degree;
    Brelaz(String str, ArrayList<Integer> arrayList, int degree){
        this.str = str;
        this.arrayList = arrayList;
        this.degree = degree;
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
    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
