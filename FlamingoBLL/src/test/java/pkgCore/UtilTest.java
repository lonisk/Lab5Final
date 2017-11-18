package pkgCore;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class UtilTest {

	@Test
	public void TestRemove() {
		
		LinkedList<Integer> listI = new LinkedList<Integer>();
		listI.add(1);
		listI.add(10);
		listI.add(21);
		listI.add(22);
		listI.add(32);
		
		assertEquals(5,listI.size());
		
		LinkedList<Integer> filteredList = GamePlayBlackJack.ValidScores(listI);
		
		assertEquals(3,filteredList.size());
		
		assertEquals(1,filteredList.getFirst().intValue());
		assertEquals(21,filteredList.getLast().intValue());
		
	}

}
