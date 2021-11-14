import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SolutionSteps {

    public static int[] stringToPosition(String s) {
        String[] arr = s.split(" ");
        int result[] = new int[2];
        result[0] = Integer.parseInt(arr[0]);
        result[1] = Integer.parseInt(arr[1]);

        return result;
    }

    public static String positionToString(int i, int j) {
        String s = i + " " + j;
        return s;
    }


    public static ArrayList<Integer> availableValuesForThisPosition(int[][] board, int size, int row , int column){

        boolean[] alreadyAssignedValues = new boolean[size];
        Arrays.fill(alreadyAssignedValues, false);

        ArrayList<Integer> available = new ArrayList<>();

        for(int i = 0; i < size; i++){//ei column a ki ki value
            if(board[i][column] != 0){ //1 board a thakle
                alreadyAssignedValues[board[i][column] - 1] = true; // 0 te true boshabo
            }
        }
        for(int i = 0; i < size; i++){//ei column a ki ki value
            if(board[row][i] != 0){ //1 board a thakle
                alreadyAssignedValues[board[row][i] - 1] = true; // 0 te true boshabo
            }
        }
        for (int i = 0; i < size; i++){
            if(!alreadyAssignedValues[i]){ //jodi 10th position a true thake
                available.add(i+1); // tahole 11 add koro available list a
            }
        }
        return available;
    }

    public static String nextPosition(int[][] board, int size){
        for(int col = 0; col < size; col++ ){
            for (int row = 0; row < size; row++){
                if(board[row][col] == 0){
                    String s = positionToString(row,col);
                    if(Main.positionsToFill.get(s) == false){
                        Main.positionsToFill.put(s,true);
                        return s;
                    }
                }
            }
        }
        return "0";
    }
    public static String smallestDomainPosition(int[][] board, int size){
        PriorityQueue<Temp> smallestDomain = new PriorityQueue<>(new Main.DomainComparator());

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] == 0){
                    String s = positionToString(i,j);
                    smallestDomain.add(new Temp(s,Main.domainList.get(s)));
                    //System.out.println("Ekhane ashche");
                }
            }
        }
        if(smallestDomain.size() != 0) {
            Temp t = smallestDomain.poll();
            String s = t.getStr();
            return s;
        }
        return "0";
    }
    public static String domdeg(int[][] board, int size){
        PriorityQueue<Brelaz> domdegMin = new PriorityQueue<>(new Main.DomdegComparator());

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] == 0){
                    String s = positionToString(i,j);
                    domdegMin.add(new Brelaz(s,Main.domainList.get(s),2*(size-1)));
                    //System.out.println("Ekhane ashche");
                }
            }
        }
        if(domdegMin.size() != 0) {
            Brelaz t = domdegMin.poll();
            String s = t.getStr();
            return s;
        }
        return "0";
    }
    public static String domddeg(int[][] board, int size){
        PriorityQueue<Brelaz> domddegMin = new PriorityQueue<>(new Main.DomDdegComparator());

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] == 0){
                    String s = positionToString(i,j);
                    domddegMin.add(new Brelaz(s,Main.domainList.get(s),dynamicDegree(board,size,s)));
                    //System.out.println("Ekhane ashche");
                }
            }
        }
        if(domddegMin.size() != 0) {
            Brelaz t = domddegMin.poll();
            String s = t.getStr();
            return s;
        }
        return "0";
    }
    public static String brelaz(int[][] board, int size){
        PriorityQueue<Brelaz> smallestDomainMaxDegree = new PriorityQueue<>(new Main.BrelazComparator());

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] == 0){
                    String s = positionToString(i,j);
                    smallestDomainMaxDegree.add(new Brelaz(s,Main.domainList.get(s),dynamicDegree(board,size,s)));
                    //System.out.println("Ekhane ashche");
                }
            }
        }
        if(smallestDomainMaxDegree.size() != 0) {
            Brelaz t = smallestDomainMaxDegree.poll();
            String s = t.getStr();
            return s;
        }
        return "0";
    }


    public static int dynamicDegree(int[][] board, int size, String current){
        int degree =0;
        int[] positions = stringToPosition(current);
        int i = positions[0];
        int j = positions[1];

        for(int row = 0; row < size; row++){
            if(row != i){
                if(board[row][j] == 0){
                    degree+=1;
                }
            }
        }
        for(int col = 0; col < size; col++){
            if(col != j){
                if(board[i][col] == 0){
                    degree+=1;
                }
            }
        }

        return degree;
    }

    public static void domainListRearrange(int[][] board, int size, String position, int value){
        int[] positions = stringToPosition(position);
        for(int row = 0; row < size; row++){
            if(row != positions[0]){
                if(board[row][positions[1]] == 0){
                    ArrayList<Integer> domain = availableValuesForThisPosition(board,size,row, positions[1]);
                    Main.domainList.put(positionToString(row, positions[1]), domain);
                }
            }
        }
        for(int col = 0; col < size; col++){
            if(col != positions[1]){
                if(board[positions[0]][col] == 0){
                    ArrayList<Integer> domain = availableValuesForThisPosition(board,size,positions[0], col);
                    Main.domainList.put(positionToString(positions[0], col), domain);
                }
            }
        }
    }




}
