package model;

import model.Address;

public class Party {
    
    protected String partyOrganisationName;
    protected Address partyAddress;

    public Party() {
    }
    
    public Party(String partyOrganisationName, Address partyAddress) {
        this.partyOrganisationName = partyOrganisationName;
        this.partyAddress = partyAddress;
    }

    public String getPartyOrganisationName() {
        return partyOrganisationName;
    }

    public void setPartyOrganisationName(String partyOrganisationName) {
        this.partyOrganisationName = partyOrganisationName;
    }

    public Address getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(Address partyAddress) {
        this.partyAddress = partyAddress;
    }

    @Override
    public String toString() {
        return "Party{" + "partyOrganisationName=" + partyOrganisationName + ", partyAddress=" + partyAddress + '}';
    }
    
    public String toStringCsv(){
        return partyOrganisationName +"\\"+partyAddress.toStringCsv();
    }
}
