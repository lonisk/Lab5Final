package pkgCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import pkgEnum.eRank;
import pkgEnum.eSuit;

public class GamePlayBlackJackTest {

	@Test
	public void TestPlayerWinning() {
		Table t=new Table();
		Player p1=new Player("Mike",1);
		Player p2=new Player("John",2);
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		Deck d=new Deck();
		GamePlayBlackJack gpBJ=new GamePlayBlackJack(t.getHmTablePlayer(),d);
		Iterator it=gpBJ.getHmGameHands().entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair=(Map.Entry)it.next();
			HandBlackJack hBJ=(HandBlackJack) pair.getValue();
			GamePlayerHand GPH=(GamePlayerHand)pair.getKey();
		}
	}

	@Test
	public void TestPlayerLosing() {
		
	}
	
	@Test
	public void TestPlayerPush() {
		
	}
	
	@Test
	public void TestTwoPlayersWinning()
	{
		
	}
}
