
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {

	int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;

    public GameNim(){
    	currentState = new StateNim();
    }

	public boolean isWinState(State state){
		StateNim tstate = (StateNim) state;

		if(tstate.coinsLOT == 0){
			return true;
		}

		return false;
	}

	public boolean isStuckState(State state){
		return false;
	}

	public Set<State> getSuccessors(State state){

		//if the game is winning will return null finish the game.
		if(isWinState(state)){
			return null;
		}

		Set<State> succesors = new HashSet<State>();
		StateNim tstate = (StateNim) state;
		StateNim succesor_state;

		for(int i = 1; i <= 3; i++){
			if(tstate.coinsLOT > 1){
				succesor_state = new StateNim(tstate);
				succesor_state.player = tstate.player == 0 ? 1:0;
				succesor_state.coinsLOT -= i;
				if(checkstatus(succesor_state)){
					succesors.add(succesor_state);
				}
			}
		}

		return succesors;
	}

	public boolean checkstatus(StateNim state){
		if(state.coinsLOT < 0){
			return false;
		}

		return true;
	}

	public double eval(State state){

		if(isWinState(state)){
			int previous_player = state.player == 0 ? 1:0;

			if(previous_player == 0){
				return WinningScore;
			}else{
				return LosingScore;
			}

		}

		return NeutralScore;
	}

	public static void main(String[] args) throws Exception{
		Game game = new GameNim();
		Search search = new Search(game);
		int depth = 12;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while(true){

			StateNim nextState = null;

			switch(game.currentState.player){

			case 0://computer
				nextState = (StateNim) search.bestSuccessorState(depth);
				nextState.player = 0;
				System.out.println("Computer: \n" + nextState);
				break;

			case 1://human
				System.out.print("Enter your *valid(1, 2, or 3)* coins remove> ");
                int remove = Integer.parseInt( in.readLine() );

                nextState = new StateNim((StateNim)game.currentState);
                nextState.player = 1;

                nextState.coinsLOT -= remove;
                System.out.println("Human: \n" + nextState);
                break;
			}

			game.currentState = nextState;
			game.currentState.player = game.currentState.player == 0 ? 1:0;

			if(game.isWinState(game.currentState)){
				if(game.currentState.player == 0){
					System.out.println("Computer Wins!");
				}else{
					System.out.println("You Win!");
				}
				break;
			}

		}


	}

}
