package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceDetails {
    
    private String invoiceTypeCode;
    private Date invoiceDate;
    private String currencyIdentifier;
    private double invoiceVatExcludedAmount;
    private double invoiceTotalVatAmount;
    private double invoiceTotalVatIncludedAmount;
    private String invoiceFreeText;    
    private PaymentTerm paymentTermsDetails;

    public InvoiceDetails() {
    }

    public InvoiceDetails(String invoiceTypeCode, String invoiceDate, String currencyIdentifier, double invoiceVatExcludedAmount, double invoiceTotalVatAmount, double invoiceTotlVatIncludedAmount, String invoiceFreeText, PaymentTerm paymentTermsDetails){
        this.invoiceTypeCode = invoiceTypeCode;
        this.invoiceDate = formatDateTimeFromString(invoiceDate);
        this.currencyIdentifier = currencyIdentifier;
        this.invoiceVatExcludedAmount = invoiceVatExcludedAmount;
        this.invoiceTotalVatAmount = invoiceTotalVatAmount;
        this.invoiceTotalVatIncludedAmount = invoiceTotlVatIncludedAmount;
        this.invoiceFreeText = invoiceFreeText;
        this.paymentTermsDetails = paymentTermsDetails;
    }

    public String getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    public void setInvoiceTypeCode(String invoiceTypeCode) {
        this.invoiceTypeCode = invoiceTypeCode;
    }

    public String getInvoiceDate() {
        return formatDateTimeToString(invoiceDate);
    }

    public void setInvoiceDate(String invoiceDate){
        this.invoiceDate = formatDateTimeFromString(invoiceDate);
    }
   
    public String getCurrencyIdentifier() {
        return currencyIdentifier;
    }

    public void setCurrencyIdentifier(String currencyIdentifier) {
        this.currencyIdentifier = currencyIdentifier;
    }

    public double getInvoiceVatExcludedAmount() {
        return invoiceVatExcludedAmount;
    }

    public void setInvoiceVatExcludedAmount(double invoiceVatExcludedAmount) {
        this.invoiceVatExcludedAmount = invoiceVatExcludedAmount;
    }

    public double getInvoiceTotalVatAmount() {
        return invoiceTotalVatAmount;
    }

    public void setInvoiceTotalVatAmount(double invoiceTotalVatAmount) {
        this.invoiceTotalVatAmount = invoiceTotalVatAmount;
    }

    public double getInvoiceTotalVatIncludedAmount() {
        return invoiceTotalVatIncludedAmount;
    }

    public void setInvoiceTotalVatIncludedAmount(double invoiceTotalVatIncludedAmount) {
        this.invoiceTotalVatIncludedAmount = invoiceTotalVatIncludedAmount;
    }

    public String getInvoiceFreeText() {
        return invoiceFreeText;
    }

    public void setInvoiceFreeText(String invoiceFreeText) {
        this.invoiceFreeText = invoiceFreeText;
    }

    public PaymentTerm getPaymentTermsDetails() {
        return paymentTermsDetails;
    }

    public void setPaymentTermsDetails(PaymentTerm paymentTermsDetails) {
        this.paymentTermsDetails = paymentTermsDetails;
    }

    private static Date formatDateTimeFromString(String input){
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(input);
        } catch (ParseException ex) {
            Logger.getLogger(InvoiceDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    private static String formatDateTimeToString(Date input){
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
        return s.format(input);
    }
    
    @Override
    public String toString() {
        return "InvoiceDetails{" + "invoiceTypeCode=" + invoiceTypeCode + ", currencyIdentifier=" + currencyIdentifier + ", invoiceVatExcludedAmount=" + invoiceVatExcludedAmount + ", invoiceTotalVatAmount=" + invoiceTotalVatAmount + ", invoiceTotlVatIncludedAmount=" + invoiceTotalVatIncludedAmount + ", invoiceFreeText=" + invoiceFreeText + ", paymentTermsDetails=" + paymentTermsDetails + '}';
    }
    
}
