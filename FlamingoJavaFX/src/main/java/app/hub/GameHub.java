package app.hub;

import java.io.IOException;

import netgame.common.Hub;
import pkgCore.Action;
import pkgCore.Table;
import pkgEnum.eAction;

public class GameHub extends Hub {

	Table HubPokerTable = null;

	public GameHub(int port) throws IOException {
		super(port);
		this.setAutoreset(true);
	}

	@Override
	protected void messageReceived(int playerID, Object message) {

		System.out.println("Action received from the hub");
		
		if (HubPokerTable == null)
			HubPokerTable = new Table();
		
		if (message instanceof Action) {
			
			Action a = (Action)message;
				
			
			

			switch (a.getAction()) {
			case Sit:
				HubPokerTable.AddPlayerToTable(a.getPlayer());
				sendToAll(HubPokerTable);				
				break;
			case Leave:
				HubPokerTable.RemovePlayerFromTable(a.getPlayer());
				sendToAll(HubPokerTable);
				break;
			}


		}
	}

}
