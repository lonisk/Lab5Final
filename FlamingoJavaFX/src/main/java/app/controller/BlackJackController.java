package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Flamingo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;

public class BlackJackController implements Initializable {
	private Flamingo FlamingoGame;
	
	@FXML
	private Label lblNameP1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void setMainApp(Flamingo FlamingoGame)
	{
		this.FlamingoGame = FlamingoGame;
	}

	
	@FXML
	public void btnStartGame_Click(ActionEvent event)
	{
		Button btn = (Button)event.getSource();
		
		System.out.println(btn.getText());
		
		System.out.println("Start clicked");
	}
	
	
	
	
	
	
	public void btnSitLeave_Click(ActionEvent event)
	{

		Button btn = (Button)event.getSource();
		System.out.println("Button was clicked");	
		
		Action a = new Action();
		
		if (btn.getText() == "Sit")
		{
			a.setAction(eAction.Sit);
		}
		else if (btn.getText() == "Leave")
		{
			a.setAction(eAction.Leave);
			
		}
		
		a.setPlayer(this.FlamingoGame.getAppPlayer());
		
		this.FlamingoGame.messageSend(a);
		
		
	}
	
	
	
	public void HandleTableState(Table t) {

		Platform.runLater(() -> {		
			
			lblNameP1.setText(FlamingoGame.getAppPlayer().getPlayerName());
			
		});
	}

	public void HandleGameState(GamePlay gp) {

		//		Coming Soon....!
	}

}
