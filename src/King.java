public class King extends ChessPiece{

	boolean moved = false;
	
	public King(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		super(color, x, y, alive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkIfValidMove(int x, int y, int[][] k) throws IllegalArgumentException{
		if(x == this.x && y == this.y+1 ||
			x == this.x && y == this.y-1 ||
			x == this.x+1 && y == this.y ||
			x == this.x-1 && y == this.y ||
			x == this.x+1 && y == this.y+1 ||
			x == this.x+1 && y == this.y-1 ||
			x == this.x-1 && y == this.y+1 ||
			x == this.x-1 && y == this.y-1) {}
		else {throw new IllegalArgumentException("ulovlig trekk");}
		moved = true;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "King";
	}

}
