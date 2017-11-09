package pkgCore;

import java.util.UUID;

public class GamePlayerHand {

	private UUID GameID;
	private UUID PlayerID;
	private UUID HandID;
	
	
	public GamePlayerHand(UUID gameID, UUID playerID, UUID handID) {
		super();
		GameID = gameID;
		PlayerID = playerID;
		HandID = handID;
	}
	public UUID getGameID() {
		return GameID;
	}
	public UUID getPlayerID() {
		return PlayerID;
	}
	public UUID getHandID() {
		return HandID;
	}
	
	public String getGPH()
	{
		return GameID.toString() + PlayerID.toString() + HandID.toString();
	}
}
