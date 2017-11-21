package pkgCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import pkgException.DeckException;
import pkgException.HandException;

public abstract class Hand {

	private UUID HandID;
	private ArrayList<Card> cards;
	private HandScore HS;

	public Hand() {
		this.HandID = UUID.randomUUID();
		cards = new ArrayList<Card>();
	}

	public UUID getHandID() {
		return HandID;
	}

	protected ArrayList<Card> getCards() {
		return cards;
	}

	public Card Draw(Deck d) throws DeckException {
		Card crd = d.Draw();
		cards.add(crd);
		return crd;
	}
	
	protected HandScore getHS() {
		return HS;
	}

	protected void setHS(HandScore hS) {
		HS = hS;
	}

	public abstract HandScore ScoreHand() throws HandException;

	protected void AddCard(Card crd) {
		cards.add(crd);
	}
}
