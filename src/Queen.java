public class Queen extends ChessPiece{

	public Queen(String color, int x, int y, boolean alive) throws IllegalArgumentException {
		super(color, x, y, alive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkIfValidMove(int x, int y, int[][] k) {
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
		else if(differenceX>0 && differenceY>0 && differenceY==differenceX) {
			for(int i = differenceY-1; i>0; i--) {
				System.out.println(i);
				if(!checkAvailability(x+i, y+i, k)) {
					System.out.println("break1!");
					throw new IllegalArgumentException("ulovlig input");
				}
			}
		}
		else if(differenceX<0 && differenceY<0 && differenceY==differenceX) {
			for(int i = differenceY+1; i<0; i++) {
				if(!checkAvailability(x+i, y+i, k)) {
					System.out.println("break2!");
					throw new IllegalArgumentException("ulovlig input");
				}
			}
		}
		else if(differenceY<0 && differenceX>0 && differenceX+differenceY == 0) {
			for(int i = differenceX-1; i>0; i--) {
				if(!checkAvailability(x+i, y-i, k)) {
					System.out.println("break3!");
					throw new IllegalArgumentException("ulovlig input");
				}
			}
		}
		else if(differenceY>0 && differenceX<0 && differenceY+differenceX == 0) {
			for(int i = differenceX+1; i<0; i++) {
				if(!checkAvailability(x+i, y-i, k)) {
					System.out.println("break4!");
					throw new IllegalArgumentException("ulovlig input");
				}
			}
		}
		else{throw new IllegalArgumentException("ulovlig input");}		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Queen";
	}

}
