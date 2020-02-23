public class Pawn extends ChessPiece{
	
	boolean moved = false;

	public Pawn(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		super(color, x, y, alive);
	}

	@Override
	public void checkIfValidMove(int x, int y, int[][] k) throws IllegalArgumentException{
		if(x == this.x && y == this.y+1 && getColor().equals("White") && checkAvailability(x, y, k)) {
		
		}else if(((x == this.x+1)|| x == this.x-1) && y == this.y+1 && getColor().equals("White") && !checkAvailability(x, y, k)) { //attacks

		}else if(x == this.x && y == this.y-1 && getColor().equals("Black") && checkAvailability(x, y, k)) {

		}else if(((x == this.x-1)|| x == this.x+1) && y == this.y-1 && getColor().equals("Black") && !checkAvailability(x, y, k)) { //attacks

		}else if(!moved && x == this.x && y == this.y+2 && getColor().equals("White") && checkAvailability(x, y, k)) {

		}else if(!moved && x == this.x && y == this.y-2 && getColor().equals("Black") && checkAvailability(x, y, k)) {
		}
		else {throw new IllegalArgumentException("Ulovlig trekk");}
		moved = true;
	}

	@Override
	public String getType() {
		return "Pawn";
	}
	
}


