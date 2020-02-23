public class Knight extends ChessPiece{

	public Knight(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		super(color, x, y, alive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkIfValidMove(int x, int y, int[][] k) {
		
		if(this.x+2 == x && this.y+1 == y ||
			this.x+2 == x && this.y-1 == y ||
			this.x-2 == x && this.y+1 == y ||
			this.x-2 == x && this.y-1 == y ||
			this.x+1 == x && this.y+2 == y ||
			this.x+1 == x && this.y-2 == y ||
			this.x-1 == x && this.y+2 == y ||
			this.x-1 == x && this.y-2 == y) {}
		
		else {throw new IllegalArgumentException("ulovlig trekk");}
				
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Knight";
	}
	
	

}
