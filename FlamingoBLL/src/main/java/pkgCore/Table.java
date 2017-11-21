package pkgCore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class Table implements Serializable{

	private UUID TableID;
	private HashMap<UUID, Player> hmTablePlayer = new HashMap<UUID,Player>();
	
	public Table() {
		super();
		this.TableID = UUID.randomUUID();
	}
	
	public void AddPlayerToTable(Player plyer)
	{
		hmTablePlayer.put(plyer.getPlayerID(), plyer);
	}
	public void RemovePlayerFromTable(Player plyer)
	{
		hmTablePlayer.remove(plyer.getPlayerID());
	}
	
	public Player GetPlayerFromTable(Player plyer)
	{
		return (Player)hmTablePlayer.get(plyer.getPlayerID());
	}

	public HashMap<UUID, Player> getHmTablePlayer() {
		return hmTablePlayer;
	}
}