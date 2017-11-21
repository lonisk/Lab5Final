package pkgCore;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class UtilTest {

	@Test
	public void TestRemove() {
		
		LinkedList<Integer> LIST = new LinkedList<Integer>();
				LIST.add(1);
				LIST.add(10);
				LIST.add(21);
				LIST.add(22);
				LIST.add(32);
		
		assertEquals(5,LIST.size());
		LinkedList<Integer> filLIST = GamePlayBlackJack.ValidScores(LIST);
		assertEquals(3,filLIST.size());
		assertEquals(1,filLIST.getFirst().intValue());
		assertEquals(21,filLIST.getLast().intValue());
	}
}