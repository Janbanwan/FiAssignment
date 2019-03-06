package model;

import java.util.ArrayList;

public class FinVoice {
    private BuyerParty buyerParty;
    private Party deliveryParty;
    private InvoiceDetails invoiceDetails;
    private ArrayList<Article> invoiceArticles;

    public FinVoice() {
        this.invoiceArticles = new ArrayList<Article>();
    }

    public FinVoice(BuyerParty buyerParty, Party deliveryParty, InvoiceDetails invoiceDetails, ArrayList<Article> invoiceArticles) {
        this.buyerParty = buyerParty;
        this.deliveryParty = deliveryParty;
        this.invoiceDetails = invoiceDetails;
        this.invoiceArticles = invoiceArticles;
    }

    public BuyerParty getBuyerParty() {
        return buyerParty;
    }

    public void setBuyerParty(BuyerParty buyerParty) {
        this.buyerParty = buyerParty;
    }

    public Party getDeliveryParty() {
        return deliveryParty;
    }

    public void setDeliveryParty(Party deliveryParty) {
        this.deliveryParty = deliveryParty;
    }

    public InvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public ArrayList<Article> getInvoiceArticles() {
        return invoiceArticles;
    }

    public void setInvoiceArticles(ArrayList<Article> invoiceArticles) {
        this.invoiceArticles = invoiceArticles;
    }
    
    
    public void addArticle(Article article){
        this.invoiceArticles.add(article);
    }

    @Override
    public String toString() {
        return "FinVoice{" + "buyerParty=" + buyerParty + ", deliveryParty=" + deliveryParty + ", invoiceDetails=" + invoiceDetails + ", invoiceArticles=" + invoiceArticles + '}';
    }
    
    private static String switchCommaType(double dbl){
        return String.valueOf(dbl).replace(".", ",");
    }
    
    public String toStringCsv(){
        
        /**
         * This toString statement specifies the format required in the CSV file and is used to print the CSV file out.
         * The commas are somewhat ugly and non-descriptive but I found just inserting the commas a better solution than creating 
         * variables for all the fields that are not present in the XML file and initializing them as empty.
         */
        
        String paymentOverDueFinePercent = switchCommaType(invoiceDetails.getPaymentTermsDetails().getPaymentOverDueFinePercent());
        
        String toString = 
                invoiceDetails.getInvoiceTypeCode() + ";" + 
                invoiceDetails.getCurrencyIdentifier() + ";" + ";" + ";" + 
                buyerParty.getBuyerPartyIdentifier() + ";" + ";" + 
                deliveryParty.getPartyOrganisationName() +";" + ";" + ";" + ";" + ";" + 
                paymentOverDueFinePercent + ";" + 
                invoiceDetails.getInvoiceDate() + ";" + ";" + ";" + ";" + 
                deliveryParty.toStringCsv() + ";" + 
                deliveryParty.toStringCsv() + ";" + 
                invoiceDetails.getInvoiceFreeText();
     
        for(Article a:invoiceArticles){
            toString += a.toStringCsv();
        }
        
        return toString;
    }    
}
