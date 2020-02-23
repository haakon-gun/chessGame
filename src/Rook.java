public class Rook extends ChessPiece{
	
	boolean moved = false;

	public Rook(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		super(color, x, y, alive);
	}

	@Override
	public void checkIfValidMove(int x, int y, int[][] k) throws IllegalArgumentException{
		int differenceX = this.x-x;
		int differenceY = this.y-y;
		System.out.println(differenceX);
		System.out.println(differenceY);
		if(differenceX==0 && differenceY>0) {
			for(int i = differenceY-1; i>0; i--) {
				System.out.println(i);
				if(!checkAvailability(x, y+i, k)) {
					System.out.println("break1!");
					throw new IllegalArgumentException("ulovlig trekk");
				}
			}
		}
		else if(differenceX==0 && differenceY<0) {
			for(int i = differenceY+1; i<0; i++) {
				if(!checkAvailability(x, y+i, k)) {
					System.out.println("break2!");
					throw new IllegalArgumentException("ulovlig trekk");
				}
			}
		}
		else if(differenceY==0 && differenceX>0) {
			for(int i = differenceX-1; i>0; i--) {
				if(!checkAvailability(x+i, y, k)) {
					System.out.println("break3!");
					throw new IllegalArgumentException("ulovlig trekk");
				}
			}
		}
		else if(differenceY==0 && differenceX<0) {
			for(int i = differenceX+1; i<0; i++) {
				if(!checkAvailability(x+i, y, k)) {
					System.out.println("break4!");
					throw new IllegalArgumentException("ulovlig trekk");
				}
			}
		}
		else{throw new IllegalArgumentException("ulovlig trekk");}
		moved = true;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Rook";
	}

}
