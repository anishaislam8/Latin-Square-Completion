import java.util.ArrayList;

public class ForwardChecking {
    public boolean solve(int[][] board, int size, String positionToFillUp, int heuristic){
        Main.numberOfNodes += 1;
        if(positionToFillUp.equalsIgnoreCase("0")){
            System.out.println("Found answer");
            Main.printMatrix(board,size);
            return true;
        }
        int[] positions = SolutionSteps.stringToPosition(positionToFillUp);

        ArrayList<Integer> available = Main.domainList.get(positionToFillUp);
        if(available.size() == 0){
            Main.numberOfBackTracks+=1;
//            System.out.println("No available value for : ");
//            Main.printMatrix(board,size);
            return false;
        }

        for(int i = 0; i < available.size(); i++){
            String nextPosition="";
            board[positions[0]][positions[1]] = available.get(i);
            //System.out.println("Assigning " + positions[0] + "," + positions[1] + " " + available.get(i));
            //remove this value from other blanks domain list
            SolutionSteps.domainListRearrange(board, size, positionToFillUp, available.get(i));
            if(heuristic == 0) { //initial ordering
                nextPosition = SolutionSteps.nextPosition(board, size);
            }else if(heuristic == 1){ // smallest domain
                nextPosition = SolutionSteps.smallestDomainPosition(board, size);
            }else if(heuristic == 2){ //brelaz
                nextPosition = SolutionSteps.brelaz(board, size);
            }else if(heuristic == 3){ //domddeg
                nextPosition = SolutionSteps.domddeg(board, size);
            }else if(heuristic == 4){ //domdeg
                nextPosition = SolutionSteps.domdeg(board, size);
            }


            boolean returnVal = solve(board, size, nextPosition,heuristic);

            if(!returnVal){
                board[positions[0]][positions[1]] = 0;
                //System.out.println("Reverting : Assigning " + positions[0] + "," + positions[1] + " 0 from  " + available.get(i));
                SolutionSteps.domainListRearrange(board, size, positionToFillUp, available.get(i));
                if(heuristic == 0) {
                    Main.positionsToFill.put(nextPosition, false);
                }
                if(i == available.size() - 1){
                    return false;
                }
            }else {
                break;
            }
        }

        return true;

    }

}
