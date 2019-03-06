package model;

public class BuyerParty extends Party {
    
    private String buyerPartyIdentifier;

    public BuyerParty() {
        super();
    }

    public BuyerParty(String buyerPartyIdentifier, String buyerOrganisationName, Address buyerPostalAddressDetails) {
        super();
        this.buyerPartyIdentifier = buyerPartyIdentifier;
    }
   
    public String getBuyerPartyIdentifier() {
        return buyerPartyIdentifier;
    }

    public void setBuyerPartyIdentifier(String buyerPartyIdentifier) {
        this.buyerPartyIdentifier = buyerPartyIdentifier;
    }

    @Override
    public String toString() {
        return super.toString() + " BuyerPartyIdentifier " + buyerPartyIdentifier;
    }
    
}
