import java.util.ArrayList;

public class ChessGame {
	
	private ChessPiece[] chessPiece = new ChessPiece[32];
	private ChessPiece markedChessPiece;
	ArrayList<ChessPiece> switchedPawns = new ArrayList<ChessPiece>();
	private boolean checkStatus = false;
	private boolean turn = true;
	
	public ArrayList<ChessPiece> getPawnSwitch() {
		return switchedPawns;
	}
	
	public ChessGame() {
		setup();
	}
	
	public boolean getTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public void setChessPieceType(ChessPiece newPiece, ChessPiece oldPiece) {
		for(int i = 0; i<chessPiece.length; i++) {
			if(oldPiece == chessPiece[i]) {
				chessPiece[i] = newPiece;
			}
		}
	}
	
	public ChessPiece[] getChessPieces() {
		return chessPiece;
	}
	
	public ChessPiece getChessPiece(int i) {
		return chessPiece[i];
	}
	
	
	
	public void defineAction(int x, int y) {
		try { //kjøres om den du har klikket på er en brikke.
			if(markedChessPiece==getChessPiece(x, y) && markedChessPiece.getMarked()) {
			
				markedChessPiece.setMarked(false);  //Ikke den samme og ikke markert
				
			}else if(markedChessPiece==null) {		//ikke null
				markedChessPiece = getChessPiece(x, y);
				markedChessPiece.setMarked(true);
				System.out.println("Du har markert "+markedChessPiece.getType()+" i felt" + markedChessPiece.getPositiontY() + markedChessPiece.getPositiontX());

			}
			else {									//Fjerner markering på den første brikken. prøver å markere den nye.
				markedChessPiece.setMarked(false);
				markedChessPiece = getChessPiece(x, y);  //Vil bare fungere om du trykker på riktig farge i forhold til tur.
				markedChessPiece.setMarked(true);
				System.out.println("Du har markert "+markedChessPiece.getType()+" i felt" + markedChessPiece.getPositiontY() + markedChessPiece.getPositiontX());
			}

		}
		catch(IllegalArgumentException e ){						//Om den posisjonen som klikkes på ikke inneholder brikke av samme typee eller er tom.
			if(markedChessPiece == null) { //kjøresn om du klikker på et felt om ikke inneholde rneo førstee runde.
				System.out.println("Marker brikken du ønsker å flytte først!");
				return;
			}
			try {
				int oldX = markedChessPiece.getPositiontX();
				int oldY = markedChessPiece.getPositiontY();
				System.out.println(y +" "+ x);
				if(markedChessPiece.getType().equals("King") && !markedChessPiece.getMoved() && checkForCastling(x, y)) { // checks for rookade
					System.out.println("rokade forespurt del1");	
					turn = !turn;
					markedChessPiece = null;
					checkStatus = checkCheck();
					return;
				}
				if(checkStatus) {
					markedChessPiece.checkIfValidMove(x, y, piecePositions());
					markedChessPiece.setPosition(x, y);
					checkStatus = checkCheck();
				}//kjøres om du ikke er i sjakk. Flytter brikken din og oppdaterer sjakkstatus
				else {
					markedChessPiece.checkIfValidMove(x, y, piecePositions());
					markedChessPiece.setPosition(x, y);
					checkStatus = checkCheck();
					}
				//Om du fortsatt er i sjakk etter at du har flyttet brikken vil denne kjøre. Denne flytter brikken din tilbake.
				if(checkStatus) {
						markedChessPiece.setPosition(oldX, oldY);
						throw new IllegalArgumentException("Ulovlig trekk, du er i sjakk!");
				}
				//Pga try Catch metoden vil dette bare kjør om du har gjort et lovlig trekk.
				
				if(markedChessPiece.getType().equals("Pawn") && (turn && markedChessPiece.getPositiontY() == 7 || !turn && markedChessPiece.getPositiontY() == 0)) {//checks for pawn switch.
					switchedPawns.add(markedChessPiece);
				}
				
				turn = !turn;
				markedChessPiece = null;
				checkStatus = checkCheck();
				if(checkStatus) {
					checkCheckMate();
				}
				System.out.println("Hvit sin tur = "+ turn);
				getChessPiece(x, y).setAlive(false);
			}
			catch(IllegalArgumentException f) {
				System.out.println(f);
			}
		}
	}
	
	public boolean checkForCastling(int x, int y) throws IllegalArgumentException{
		int kingX = markedChessPiece.getPositiontX();
		int kingY = markedChessPiece.getPositiontY();
		
		if(x==kingX+2 && markedChessPiece.checkAvailability(kingX+1, y, piecePositions())&&markedChessPiece.checkAvailability(kingX+2, y, piecePositions())) { //rookade
			markedChessPiece.setPosition(x, y);
			System.out.println("rokade forespurt");
			markedChessPiece.setMoved(true);
			if(!chessPiece[15].getMoved() && markedChessPiece.getColor().equals("White") && chessPiece[15].getPositiontX()-1==x) {
				chessPiece[15].setPosition(x-1, y);
				checkStatus = checkCheck();
				if(checkStatus) {
					markedChessPiece.setPosition(kingX, kingY);
					chessPiece[15].setPosition(x+1, y);
					throw new IllegalArgumentException("Ulovlig trekk, du er i sjakk!");
				}
			}else if(!chessPiece[31].getMoved() && markedChessPiece.getColor().equals("Black") && chessPiece[31].getPositiontX()-1==x) {
				chessPiece[31].setPosition(x-1, y);
				checkStatus = checkCheck();
				if(checkStatus) {
					markedChessPiece.setPosition(kingX, kingY);
					chessPiece[31].setPosition(x+1, y);
					throw new IllegalArgumentException("Ulovlig trekk, du er i sjakk!");
				}
			}
			return true;
		}
		else if(x==kingX-2 && markedChessPiece.checkAvailability(kingX-1, y, piecePositions()) && markedChessPiece.checkAvailability(kingX-2, y, piecePositions()) && markedChessPiece.checkAvailability(kingX-3, y, piecePositions())) {
			markedChessPiece.setPosition(x, y);
			System.out.println("rokade forespurt2");
			markedChessPiece.setMoved(true);
			
			if(!chessPiece[8].getMoved() && markedChessPiece.getColor().equals("White") && chessPiece[8].getPositiontX()+2==x) {
				chessPiece[8].setPosition(x+1, y);
				checkStatus = checkCheck();
				if(checkStatus) {
					markedChessPiece.setPosition(kingX, kingY);
					chessPiece[8].setPosition(x-1, y);
					throw new IllegalArgumentException("Ulovlig trekk, du er i sjakk!");
				}
			}
			
			else if(!chessPiece[24].getMoved() && markedChessPiece.getColor().equals("Black") && chessPiece[24].getPositiontX()+2==x) {
				chessPiece[24].setPosition(x+1, y);
				checkStatus = checkCheck();
				if(checkStatus) {
					markedChessPiece.setPosition(kingX, kingY);
					chessPiece[24].setPosition(x-1, y);
					throw new IllegalArgumentException("Ulovlig trekk, du er i sjakk!");
				}
			}
			return true;
		}else {return false;}
		
	}
	
	public boolean checkCheckMate() {
		ChessPiece BWKing;
		if(turn) {BWKing = chessPiece[12];}
		else {BWKing = chessPiece[28];}
	
		int x = BWKing.getPositiontX();
		int y = BWKing.getPositiontY();
		int [][] positions = {{x+1, y},{x-1, y},{x+1,y+1},{x-1,y-1},{x, y+1},{x, y-1},{x-1, y+1},{x+1, y-1}};
		
		for(int i = 0; i<8; i++) {
				if(positions[i][0]>=0 && positions[i][1]>=0 && positions[i][0]>=8 && positions[i][1]>=8 && BWKing.checkAvailability(positions[i][0], positions[i][1], piecePositions())) {
					BWKing.setPosition(positions[i][0], positions[i][1]);
					if(!checkCheck()) {
						BWKing.setPosition(x, y);
						System.out.println("Du er i sjakk, men du kan fortsatt plassere kongen i feltet: " + positions[i][0] + positions[i][1]);
						return false;
					}
					BWKing.setPosition(x, y);
				}

			}
		if(butCanSomeoneSacrifice()) {
			return false;
		}
		System.out.println("sjakkmatt!");
		return true;
		}
	
	public boolean butCanSomeoneSacrifice() {
		int length;
		if(turn) {length = 0;}
		else {length = chessPiece.length/2; }
		
		for(int j = 0; j<8; j++) {	
			for(int k = 0; k<8; k++){
				for(int i = 0; i<chessPiece.length/2; i++) {
					try{
						chessPiece[i+length].checkIfValidMove(k, j, piecePositions());
						if(chessPiece[i+length].checkAvailability(k, j, piecePositions())){
							int x = chessPiece[i+length].getPositiontX();
							int y = chessPiece[i+length].getPositiontY();
							chessPiece[i+length].setPosition(k, j);
							if(checkCheck()) {
								chessPiece[i+length].setPosition(x, y);								
							}
							else{
								System.out.println("Du er i sjakk, men du kan flytte " + chessPiece[i+length].getType() + " til posisjon " + getTranselatedXY(k, j));
								chessPiece[i+length].setPosition(x, y);
								return true;
								}
							}
						}
					catch(IllegalArgumentException f) {
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkCheck() throws IllegalArgumentException{
		if(turn==true) {
			for(int i = chessPiece.length/2; i<chessPiece.length; i++) { //sjekker om din konge kan blir drept av en motstander sine brikker.
				try{
					chessPiece[i].checkIfValidMove(chessPiece[12].getPositiontX(), chessPiece[12].getPositiontY(), piecePositions());
					System.out.println("Du er i sjakk!" + chessPiece[i].getColor()+chessPiece[i].getType() +" kan ta deg. Flytt kongen!");
					return true;
					}
				catch(IllegalArgumentException f) {
					continue;
				}
			}
		}
		else {
			for(int i = 0; i<chessPiece.length/2; i++) { //sjekker om din konge kan blir drept av en motstander sine brikker.
				try{chessPiece[i].checkIfValidMove(chessPiece[28].getPositiontX(), chessPiece[28].getPositiontY(), piecePositions());
					System.out.println("Du er i sjakk!" +chessPiece[i].getColor()+ chessPiece[i].getType() + " kan ta deg. Flytt kongen!");
					return true;
					}
				catch(IllegalArgumentException f) {
					continue;
				}
			}
		}
		return false;
	}
	
	
	public int[][] piecePositions() {
		int[][] k = new int[2][chessPiece.length];
		for(int i = 0; i<chessPiece.length; i++) {
			k[0][i]=chessPiece[i].getPositiontX();
			k[1][i]=chessPiece[i].getPositiontY();
		}
		return k;
	}
	
	public ChessPiece getChessPiece(int x, int y) throws IllegalArgumentException{
		if(turn==true) {
			for(int i = 0; i<chessPiece.length/2; i++) {
				if(chessPiece[i].getPositiontX() == x && chessPiece[i].getPositiontY() == y && chessPiece[i].getAlive()) {
					return chessPiece[i];
				}
			}
		}
		if(turn==false) {
			for(int i = chessPiece.length/2; i<chessPiece.length; i++) {
				if(chessPiece[i].getPositiontX() == x && chessPiece[i].getPositiontY() == y && chessPiece[i].getAlive()) {
					return chessPiece[i];
					}
			}
		}throw new IllegalArgumentException("empty field or motstander");
	}
	
	public String getTranselatedXY(int x, int y) {
		String[] list1 = {"A", "B", "C", "D", "E", "F", "G", "H"};
		String[] list2 = {"1", "2", "3", "4", "5", "6", "7", "8"};
		return list1[x] + list2[y];
	}
	
	public void setup() {
		
		chessPiece[8] = new Rook("White", 0, 0, true);
		chessPiece[9] = new Knight("White", 1, 0, true);
		chessPiece[10] = new Bishop("White", 2, 0, true);
		chessPiece[11] = new Queen("White", 3, 0, true);
		chessPiece[12] = new King("White", 4, 0, true);
		chessPiece[13] = new Bishop("White", 5, 0, true);
		chessPiece[14] = new Knight("White", 6, 0, true);
		chessPiece[15] = new Rook("White", 7, 0, true);
		
		chessPiece[24] = new Rook("Black", 0, 7, true);
		chessPiece[25] = new Knight("Black", 1, 7, true);
		chessPiece[26] = new Bishop("Black", 2, 7, true);
		chessPiece[27] = new Queen("Black", 3, 7, true);
		chessPiece[28] = new King("Black", 4, 7, true);
		chessPiece[29] = new Bishop("Black", 5, 7, true);
		chessPiece[30] = new Knight("Black", 6, 7, true);
		chessPiece[31] = new Rook("Black", 7, 7, true);
		
		for(int i = 0; i<8; i++) {
			chessPiece[i] = new Pawn("White", i, 1, true);
			
			chessPiece[16+i] = new Pawn("Black", i, 6, true);

		}
	}


	
}
