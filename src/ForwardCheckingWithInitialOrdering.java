import java.util.ArrayList;

public class ForwardCheckingWithInitialOrdering {

    public boolean solve(int[][] board, int size, String positionToFillUp){
        Main.numberOfNodes += 1;
        if(positionToFillUp.equalsIgnoreCase("0")){
            System.out.println("Found answer");
            Main.printMatrix(board,size);
            return true;
        }
        int[] positions = SolutionSteps.stringToPosition(positionToFillUp);
        ArrayList<Integer> available = new ArrayList<>();
        available = SolutionSteps.availableValuesForThisPosition(board,size,positions[0], positions[1]);

        if(available.size() == 0){
            Main.numberOfBackTracks+=1;
            return false;
        }

        for(int i = 0; i < available.size(); i++){

            board[positions[0]][positions[1]] = available.get(i);

            String nextPosition = SolutionSteps.nextPosition(board,size);
            Main.positionsToFill.put(nextPosition,true);

            boolean returnVal = solve(board, size, nextPosition);

            if(!returnVal){
                board[positions[0]][positions[1]] = 0;
                Main.positionsToFill.put(nextPosition, false);
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
