import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static HashMap<String, Boolean> positionsToFill;
    public static HashMap<String, ArrayList<Integer> > domainList;
    public static int numberOfNodes;
    public static int numberOfBackTracks;
    public static PriorityQueue<Temp> priorityQueue;
    public static PriorityQueue<Brelaz> brelazQueue;
    public static PriorityQueue<Brelaz> domddegQueue;
    public static PriorityQueue<Brelaz> domdegQueue;
    public static void main(String[] args) throws FileNotFoundException {


        //File data = new File("data/d-10-01.txt.txt");
        //File data = new File("data/d-10-06.txt.txt");
        //File data = new File("data/d-10-07.txt.txt");
        //File data = new File("data/d-10-08.txt.txt");
        //File data = new File("data/d-10-09.txt.txt");
        File data = new File("data/d-15-01.txt.txt");
        //File data = new File("data/example.txt");
        Scanner file = new Scanner(data);
        String firstLine = file.nextLine(); //N=10;
        String[] temp = firstLine.split(";");//N=10 ;
        String[] temp2 = temp[0].split("=");//N 10
        int size = Integer.parseInt(temp2[1]);//10

        int[][] latinSquare = new int[size][size];
        for(int i = 0; i < 2; i++){
            String s = file.nextLine();
        }

        for(int i = 0; i < size; i++){
            String line = file.nextLine();
            line = line.replace('|',',');
            line = line.replaceAll(" ", "");
            String[] row = line.split(",");
            for(int j = 0; j < size; j++){
                latinSquare[i][j] = Integer.parseInt(row[j]);
            }
        }


        //maintain a hashmap for positions to fill up
        positionsToFill = new HashMap<>();
        domainList = new HashMap<>();
        priorityQueue = new PriorityQueue<>(new DomainComparator());
        brelazQueue = new PriorityQueue<>(new BrelazComparator());
        domddegQueue = new PriorityQueue<>(new DomDdegComparator());
        domdegQueue = new PriorityQueue<>(new DomdegComparator());

        int k = 0;
        String firstEmptyPosition = "";


        for(int i = 0; i < size; i++){
            for(int j =0; j < size; j++){
                if(latinSquare[j][i] == 0){
                    String s = SolutionSteps.positionToString(j,i);
                    ArrayList<Integer> domain = SolutionSteps.availableValuesForThisPosition(latinSquare,size,j,i);
                    domainList.put(s,domain);
                    Temp obj = new Temp(s,domain);
                    int degree = SolutionSteps.dynamicDegree(latinSquare,size,s);
                    priorityQueue.add(obj);
                    brelazQueue.add(new Brelaz(s,domain,degree));
                    domddegQueue.add(new Brelaz(s,domain,degree));
                    domdegQueue.add(new Brelaz(s,domain,2*(size-1)));
                    if(k == 0){
                        firstEmptyPosition = s;
                        positionsToFill.put(s,true);
                        k = 1;
                    }else {
                        positionsToFill.put(s, false);
                    }
                }
            }
        }


        int[][] copy = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                copy[i][j] = latinSquare[i][j];
            }
        }

        numberOfNodes = 0;
        numberOfBackTracks = 0;



//        ForwardCheckingWithInitialOrdering solution = new ForwardCheckingWithInitialOrdering();
//        System.out.println("***************Forward Checking With Initial Ordering****************");
//        System.out.println("Initial : ");
//        printMatrix(copy,size);
//        boolean isSolvable = solution.solve(copy, size, firstEmptyPosition);
//        if(!isSolvable){
//            System.out.println("This matrix can't be a latin square");
//        }else{
//            System.out.println("Number of nodes : " + numberOfNodes);
//            System.out.println("Number of fails : " + numberOfBackTracks);
//        }


//        System.out.println("***************Forward Checking****************");
//        System.out.println("Initial : ");
//        printMatrix(copy,size);
//        ForwardChecking forwardChecking = new ForwardChecking();
//        boolean isSolvable = forwardChecking.solve(copy, size, firstEmptyPosition, 0);
//        if(!isSolvable){
//            System.out.println("This matrix can't be a latin square");
//        }else{
//            System.out.println("Number of nodes : " + numberOfNodes);
//            System.out.println("Number of fails : " + numberOfBackTracks);
//        }



//        System.out.println("***************Forward Checking with SDF****************");
//        System.out.println("Initial : ");
//        printMatrix(copy,size);
//        ForwardChecking forwardChecking = new ForwardChecking();
//        Temp t = priorityQueue.poll();
//        boolean isSolvable = forwardChecking.solve(copy, size, t.getStr(), 1);
//        if(!isSolvable){
//            System.out.println("This matrix can't be a latin square");
//        }else{
//            System.out.println("Number of nodes : " + numberOfNodes);
//            System.out.println("Number of fails : " + numberOfBackTracks);
//        }



//        System.out.println("***************Forward Checking with Brelaz****************");
//        System.out.println("Initial : ");
//        printMatrix(copy,size);
//        ForwardChecking forwardChecking = new ForwardChecking();
//        Brelaz t = brelazQueue.poll();
//        boolean isSolvable = forwardChecking.solve(copy, size, t.getStr(), 2);
//        if(!isSolvable){
//            System.out.println("This matrix can't be a latin square");
//        }else{
//            System.out.println("Number of nodes : " + numberOfNodes);
//            System.out.println("Number of fails : " + numberOfBackTracks);
//        }


        System.out.println("***************Forward Checking with DomDdeg****************");
        System.out.println("Initial : ");
        printMatrix(copy,size);
        ForwardChecking forwardChecking = new ForwardChecking();
        Brelaz t = domddegQueue.poll();
        boolean isSolvable = forwardChecking.solve(copy, size, t.getStr(), 3);
        if(!isSolvable){
            System.out.println("This matrix can't be a latin square");
        }else{
            System.out.println("Number of nodes : " + numberOfNodes);
            System.out.println("Number of fails : " + numberOfBackTracks);
        }


//        System.out.println("***************Forward Checking with Domdeg****************");
//        System.out.println("Initial : ");
//        printMatrix(copy,size);
//        ForwardChecking forwardChecking = new ForwardChecking();
//        Brelaz t = domdegQueue.poll();
//        boolean isSolvable = forwardChecking.solve(copy, size, t.getStr(), 4);
//        if(!isSolvable){
//            System.out.println("This matrix can't be a latin square");
//        }else{
//            System.out.println("Number of nodes : " + numberOfNodes);
//            System.out.println("Number of fails : " + numberOfBackTracks);
//        }



    }



    public static void printMatrix(int[][] matrix, int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class DomainComparator implements Comparator<Temp> {

        @Override
        public int compare(Temp o1, Temp o2) {
            //ascending order
            //return o1.time > o2.time ? 1 : -1;
            return o1.getArrayList().size() > o2.getArrayList().size() ? 1 : -1;
        }
    }



    static class BrelazComparator implements Comparator<Brelaz> {

        @Override
        public int compare(Brelaz o1, Brelaz o2) {
            //ascending order
            //return o1.time > o2.time ? 1 : -1;
            if(o1.getArrayList().size() == o2.getArrayList().size()){
                //descending order degree
                return o1.getDegree() > o2.getDegree() ? -1 : 1;
            }
            return o1.getArrayList().size() > o2.getArrayList().size() ? 1 : -1;
        }
    }

    static class DomDdegComparator implements Comparator<Brelaz> {

        @Override
        public int compare(Brelaz o1, Brelaz o2) {
            //ascending order
            //return o1.time > o2.time ? 1 : -1;
            if(o1.getDegree() == 0 || o2.getDegree() == 0){
                return o1.getArrayList().size() > o2.getArrayList().size() ? 1 : -1;
            }
            double f1 = (o1.getArrayList().size() * 1.00) / (o1.getDegree() * 1.00);
            double f2 = (o2.getArrayList().size() * 1.00) / (o2.getDegree() * 1.00);

            return f1 > f2 ? 1 : -1;
        }
    }

    static class DomdegComparator implements Comparator<Brelaz> {

        @Override
        public int compare(Brelaz o1, Brelaz o2) {
            //ascending order
            //return o1.time > o2.time ? 1 : -1;
            double f1 = (o1.getArrayList().size() * 1.00) / (o1.getDegree() * 1.00);
            double f2 = (o2.getArrayList().size() * 1.00) / (o2.getDegree() * 1.00);

            return f1  > f2  ? 1 : -1;
        }
    }





}
