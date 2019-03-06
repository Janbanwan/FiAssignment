package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentTerm {
    
    private Date invoiceDueDate;
    private double paymentOverDueFinePercent;

    public PaymentTerm() {
    }

    public PaymentTerm(String invoiceDueDate, double paymentOverDueFinePercent) throws ParseException {
        this.invoiceDueDate = formatDateTimeFromString(invoiceDueDate);
        this.paymentOverDueFinePercent = paymentOverDueFinePercent;
    }

    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(String invoiceDueDate) {
        try {
            this.invoiceDueDate = formatDateTimeFromString(invoiceDueDate);
        } catch (ParseException ex) {
            Logger.getLogger(PaymentTerm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getPaymentOverDueFinePercent() {
        return paymentOverDueFinePercent;
    }

    public void setPaymentOverDueFinePercent(double paymentOverDueFinePercent) {
        this.paymentOverDueFinePercent = paymentOverDueFinePercent;
    }

    private static Date formatDateTimeFromString(String input) throws ParseException{
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(input);
        return date;
    }

    private static String formatDateTimeToString(Date input){
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
        return s.format(input);
    }
    
    @Override
    public String toString() {
        return "PaymentTerm{" + "invoiceDueDate=" + formatDateTimeToString(invoiceDueDate) + ", paymentOverDueFinePercent=" + paymentOverDueFinePercent + '}';
    }

    public String toStringCsv(){
        return formatDateTimeToString(invoiceDueDate);
    }
}
