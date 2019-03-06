package model;

public class Address {
    
    private String streetName;
    private String townName;
    private String postCodeIdentifier;
    private String countryCode;

    public Address() {
    }

    public Address(String streetName, String townName, String postCodeIdentifier, String countryCode) {
        this.streetName = streetName;
        this.townName = townName;
        this.postCodeIdentifier = postCodeIdentifier;
        this.countryCode = countryCode;
    }
    
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getPostCodeIdentifier() {
        return postCodeIdentifier;
    }

    public void setPostCodeIdentifier(String postCodeIdentifier) {
        this.postCodeIdentifier = postCodeIdentifier;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Address{" + "streetName=" + streetName + ", townName=" + townName + ", postCodeIdentifier=" + postCodeIdentifier + ", countryCode=" + countryCode + '}';
    }
    
    public String toStringCsv(){
        return streetName +"\\"+postCodeIdentifier+"\\"+townName+"\\"+countryCode;
    }
}
