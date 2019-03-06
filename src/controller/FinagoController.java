package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

import model.Party;
import model.BuyerParty;
import model.PaymentTerm;
import model.FinVoice;
import model.InvoiceDetails;
import model.Address;
import model.Article;

public class FinagoController {
    
    public static void run(){
        
            String filePath = "exampleInvoice.xml";
            
            NodeList nl = loadXml(filePath);
            
            FinVoice finVoice = new FinVoice();
            finVoice = extractInvoice(nl, finVoice);
            
            try(PrintWriter writer = new PrintWriter(new File("invoice.csv"))){
                writer.write(finVoice.toStringCsv());
            }catch(FileNotFoundException e){
                System.out.println(e);
            }
    }
    
    private static NodeList loadXml(String filePath){
        
        Element mainElement = null;
        
        try {
            File file = new File(filePath);
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            
            mainElement = doc.getDocumentElement();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(FinagoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mainElement.getChildNodes();
    }
    
    private static FinVoice extractInvoice(NodeList nl, FinVoice finVoice){

        Element element;

        for(int i = 0; i < nl.getLength(); i++){
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE){
                element = (Element) nl.item(i);
                finVoice = nodeSwitch(finVoice, element);
            }
        }
        
        return finVoice;
    }
    
    private static FinVoice nodeSwitch(FinVoice finVoice, Element element){
        
        switch(element.getNodeName()){
            case "BuyerPartyDetails":
                BuyerParty bp = extractBuyerParty(element);
                finVoice.setBuyerParty(bp);
                break;
            case "DeliveryPartyDetails":
                Party p = extractDeliveryParty(element);
                finVoice.setDeliveryParty(p);
                break;
            case "InvoiceDetails":
                InvoiceDetails invDetails = extractInvoiceDetails(element);
                finVoice.setInvoiceDetails(invDetails);
                break;
            case "InvoiceRow":
                Article a = extractInvoiceArticle(element);
                finVoice.addArticle(a);
                break;
            default: 
                System.out.println("default");
                break;
        }
        return finVoice;
    }
    
    private static BuyerParty extractBuyerParty( Element element){
        
        BuyerParty bp = new BuyerParty();
       
        String buyerPartyIdentifier = element.getElementsByTagName("BuyerPartyIdentifier").item(0).getTextContent();
        String partyOrganisationName = element.getElementsByTagName("BuyerPartyIdentifier").item(0).getTextContent();
        
        bp.setBuyerPartyIdentifier(buyerPartyIdentifier);
        bp.setPartyOrganisationName(partyOrganisationName);
        
        Address address = new Address();

        String streetName = element.getElementsByTagName("BuyerStreetName").item(0).getTextContent();
        String townName = element.getElementsByTagName("BuyerTownName").item(0).getTextContent();
        String postCodeIdentifier = element.getElementsByTagName("BuyerPostCodeIdentifier").item(0).getTextContent();
        String countryCode = element.getElementsByTagName("CountryCode").item(0).getTextContent();

        address.setStreetName(streetName);
        address.setTownName(townName);
        address.setPostCodeIdentifier(postCodeIdentifier);
        address.setCountryCode(countryCode);
        
        bp.setPartyAddress(address);
        
        return bp;
    }
    
    private static Party extractDeliveryParty(Element element){

        Party p = new Party();
        
        String partyOrganisationName = element.getElementsByTagName("DeliveryOrganisationName").item(0).getTextContent();

        Address address = new Address();

        String streetName = element.getElementsByTagName("DeliveryStreetName").item(0).getTextContent();
        String townName = element.getElementsByTagName("DeliveryTownName").item(0).getTextContent();
        String postCodeIdentifier = element.getElementsByTagName("DeliveryPostCodeIdentifier").item(0).getTextContent();
        String countryCode = element.getElementsByTagName("CountryCode").item(0).getTextContent();

        address.setStreetName(streetName);
        address.setTownName(townName);
        address.setPostCodeIdentifier(postCodeIdentifier);
        address.setCountryCode(countryCode);
        
        p.setPartyOrganisationName(partyOrganisationName);
        p.setPartyAddress(address);
        
        return p;
    }
    
    private static InvoiceDetails extractInvoiceDetails(Element element){

        String invoiceTypeCode = element.getElementsByTagName("InvoiceTypeCode").item(0).getTextContent();
        String invoiceDate = element.getElementsByTagName("InvoiceDate").item(0).getTextContent();
        String currencyIdentifier = element.getElementsByTagName("InvoiceTotalVatExcludedAmount").item(0).getAttributes().getNamedItem("AmountCurrencyIdentifier").getNodeValue();
        String invoiceDueDate = element.getElementsByTagName("InvoiceDueDate").item(0).getTextContent();
        String invoiceFreeText = element.getElementsByTagName("InvoiceFreeText").item(0).getTextContent();

        double invoiceVatExcludedAmount = Double.parseDouble(element.getElementsByTagName("InvoiceTotalVatExcludedAmount").item(0).getTextContent().replace(",", "."));
        double invoiceTotalVatAmount = Double.parseDouble(element.getElementsByTagName("InvoiceTotalVatAmount").item(0).getTextContent().replace(",", "."));
        double invoiceTotalVatIncludedAmount = Double.parseDouble(element.getElementsByTagName("InvoiceTotalVatIncludedAmount").item(0).getTextContent().replace(",", "."));
        double paymentOverDueFinePercent = Double.parseDouble(element.getElementsByTagName("PaymentOverDueFinePercent").item(0).getTextContent().replace(",", "."));

        PaymentTerm ptd = new PaymentTerm();
        
        ptd.setInvoiceDueDate(invoiceDueDate);
        ptd.setPaymentOverDueFinePercent(paymentOverDueFinePercent);
        
        InvoiceDetails invDetails = new InvoiceDetails();
        
        invDetails.setInvoiceTypeCode(invoiceTypeCode);
        invDetails.setInvoiceDate(invoiceDate);
        invDetails.setCurrencyIdentifier(currencyIdentifier);
        invDetails.setInvoiceVatExcludedAmount(invoiceVatExcludedAmount);
        invDetails.setInvoiceTotalVatAmount(invoiceTotalVatAmount);
        invDetails.setInvoiceTotalVatIncludedAmount(invoiceTotalVatIncludedAmount);
        invDetails.setInvoiceFreeText(invoiceFreeText);
        invDetails.setPaymentTermsDetails(ptd);
        
        return invDetails;
    }
    
    
    private static Article extractInvoiceArticle(Element element){
        
        Article a = new Article();

        String articleIdentifier = element.getElementsByTagName("ArticleIdentifier").item(0).getTextContent();
        String articleName = element.getElementsByTagName("ArticleName").item(0).getTextContent();
        String quantityUnitCode = element.getElementsByTagName("OrderedQuantity").item(0).getAttributes().getNamedItem("QuantityUnitCode").getNodeValue();
        String amountCurrencyIdentifier = element.getElementsByTagName("UnitPriceAmount").item(0).getAttributes().getNamedItem("AmountCurrencyIdentifier").getNodeValue();

        int orderedQuantity = Integer.parseInt(element.getElementsByTagName("OrderedQuantity").item(0).getTextContent());
        int vatRatePercent = Integer.parseInt(element.getElementsByTagName("RowVatRatePercent").item(0).getTextContent());

        double unitPriceAmount = Double.parseDouble(element.getElementsByTagName("UnitPriceAmount").item(0).getTextContent().replace(",", "."));
        double vatExcludedAmount = Double.parseDouble(element.getElementsByTagName("RowVatExcludedAmount").item(0).getTextContent().replace(",", "."));
        double amount = Double.parseDouble(element.getElementsByTagName("RowAmount").item(0).getTextContent().replace(",", "."));
        double vatAmount = Double.parseDouble(element.getElementsByTagName("RowVatAmount").item(0).getTextContent().replace(",", ".").replace(",", "."));
       
        a.setArticleIdentifier(articleIdentifier);
        a.setArticleName(articleName);
        a.setQuantityUnitCode(quantityUnitCode);
        a.setOrderedQuantity(orderedQuantity);
        a.setAmountCurrencyIdentifier(amountCurrencyIdentifier);
        a.setUnitPriceAmount(unitPriceAmount);
        a.setVatRatePercent(vatRatePercent);
        a.setVatExcludedAmount(vatExcludedAmount);
        a.setAmount(amount);
        a.setVatAmount(vatAmount);
        
        return a;
    }
}
