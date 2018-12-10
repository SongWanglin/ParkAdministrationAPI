package edu.iit.cs445.StateParking.Objects;

import edu.iit.cs445.StateParking.ObjectsEnum.CardType;

public class CreditCard {
	private String CardNumber;
	private CardType type;
	private String NameOnCard;
	private String ExpirationData;
	private String zip;
	
	public CreditCard (String Number, String name, String exp, String Zip) {
		
		this.CardNumber = Number;
		this.type = CardType.detect(Number);
		this.NameOnCard = name;
		this.ExpirationData=exp;
		this.zip = Zip;
	}
	
	public String getCardNumber() {
		return CardNumber;
	}

	public String getName_on_card() {
		return NameOnCard;
	}

	public CardType getType() {
		return type;
	}


	public String getExpirationData() {
		return ExpirationData;
	}

	public String getZip() {
		return zip;
	}
	
}
