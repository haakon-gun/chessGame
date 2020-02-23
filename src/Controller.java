import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Controller implements Initializable {
	
	@FXML private GridPane gridPaneID;
	@FXML private HBox switchPawnOption;
	@FXML private Button btnQueen;
	@FXML private Button btnBishop;
	@FXML private Button btnKnight;
	@FXML private Button btnRook;
	private ChessGame game = new ChessGame();
	private ChessPiece newPiece;
	
	
	

	public void updateMap() {
		removeOldImg();
		if(checkForSwitchesPawns()) {
			return;
		}
		for(int i = 0; i<game.getChessPieces().length; i++) {
			if(game.getChessPiece(i).getAlive()) {
			ImageView piece = new ImageView();
			piece.setImage(new Image(getClass().getResource("resources/" + game.getChessPiece(i).getColor() +game.getChessPiece(i).getType() + ".png").toString(), true));
			piece.setFitWidth(35);
			piece.setPreserveRatio(true);
			piece.setCursor(Cursor.HAND);
			gridPaneID.add(piece, game.getChessPiece(i).getPositiontY(), game.getChessPiece(i).getPositiontX());
			GridPane.setHalignment(piece, HPos.CENTER);
			}
		}
		gridPaneID.getChildren().forEach(child -> {						//Går gjennom alle nodes og legger inn en mouseevent. 
            child.setOnMouseClicked(new EventHandler<MouseEvent>() {
            		
	            	public void handle(MouseEvent event) {					//Denne kjølres når noe en celle klikkes på.
	            		Node source = (Node)event.getSource();
	            		int colIndex = GridPane.getColumnIndex(source);
	            		int rowIndex = GridPane.getRowIndex(source);
	            		System.out.println("col: "+colIndex +"row: "+ rowIndex);
	            		boolean turn = game.getTurn();
	            		game.defineAction(rowIndex, colIndex);
	            		if(!turn == game.getTurn())	{
	            			updateMap();
	            		}
	            	}
            	});
		});
	}
	
	int switchedPawnCounter = 0;
	
	private boolean checkForSwitchesPawns() {
		if(game.getPawnSwitch().size()>switchedPawnCounter) {			
			switchPawnOption.setVisible(true);
			switchPawnOption.setDisable(false);
			return true;
		}
		return false;
	}
	
	@FXML
	public void buttonOnAction(ActionEvent event) {
		switchPawnOption.setVisible(false);
		switchPawnOption.setDisable(true);
		System.out.println("ole");
		int x = game.getPawnSwitch().get(switchedPawnCounter).getPositiontX();
		int y = game.getPawnSwitch().get(switchedPawnCounter).getPositiontY();
		String color = game.getPawnSwitch().get(switchedPawnCounter).getColor();
		ChessPiece oldPiece = game.getPawnSwitch().get(switchedPawnCounter);
		
		if(event.getSource() == btnQueen) {
			newPiece = new Queen(color, x, y, true);
		}else if(event.getSource() == btnBishop) {
			newPiece = new Bishop(color, x, y, true);
		}else if(event.getSource() == btnKnight) {
			newPiece = new Knight(color, x, y, true);
		}else if(event.getSource() == btnRook) {
			newPiece = new Rook(color, x, y, true);
		}
		game.setChessPieceType(newPiece, oldPiece);
		switchedPawnCounter +=1;
		updateMap();
	}
	
	private void removeOldImg() {
		List<Node> deleteChildren = new ArrayList<Node>();
	      gridPaneID.getChildren().forEach(child -> {						//Går gjennom alle nodes og legger inn en mouseevent. 
	            if (child instanceof ImageView) {
				    deleteChildren.add(child);
				}
	            });
	      gridPaneID.getChildren().removeAll(deleteChildren);
	      }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateMap();
	}
}
