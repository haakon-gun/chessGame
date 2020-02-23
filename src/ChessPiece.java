public abstract class ChessPiece {
	
	protected int x;
	protected int y;
	protected boolean moved;
	protected String color;
	private boolean marked;
	private boolean alive;
	
	
	public ChessPiece(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		if(x>=0 && y>=0) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.marked = false;
			this.alive = alive;
		}
		else {throw new IllegalArgumentException("ulovlig input");}
	}
	
	public abstract void checkIfValidMove(int x, int y, int[][] k);
	public abstract String getType();
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean b) {
		if(!b) {
			this.x = 99;
			this.y = 99;
		}
		alive = b;
	}
	
	public boolean getMoved() {
		return moved;
	}
	
	public void setMoved(boolean k) {
		moved = k;
	}
	
	public boolean getMarked() {
		return marked;
	}
	
	public void setMarked(boolean b) {
		marked = b;
	}

	public boolean checkAvailability(int x, int y, int[][] k) { //returnerer true om plassen er tilgjengelig.
		for(int i = 0; i<k[0].length; i++) {
			if(k[0][i] == x && k[1][i] == y) {
				return false;
			}
		}
		return true;
	}
	
	public int getPositiontX() {
		return x;
	}
	
	public int getPositiontY() {
		return y;
	}
	public String getColor() {
		return color;
	}

}
