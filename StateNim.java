/*
 *  every time only allow take at most 3 coins and at least 1
 *  total 13 coins on the table
 *  the player who take the last coin will lose the game.
 */

public class StateNim extends State {

	public int coinsLOT;

	//default 1 = human 0 = computer and total number of coins is 13.
	public StateNim(){
		coinsLOT = 13;
		player = 1;

	}

	//make copy of the state.
	public StateNim(StateNim state){
		coinsLOT = state.coinsLOT;
		player = state.player;
	}

	//print out the message total coins left on table.
	public String toString(){
		return "Coins Left On Table: " + coinsLOT + "\n";
	}
}
