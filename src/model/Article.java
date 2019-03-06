package model;

public class Article {
    private String articleIdentifier;
    private String articleName;
    private String quantityUnitCode;
    private int orderedQuantity;
    private String amountCurrencyIdentifier;
    private int vatRatePercent;
    private double unitPriceAmount;
    private double vatAmount;
    private double vatExcludedAmount;
    private double amount;

    public Article() {
    }

    public Article(String articleIdentifier, String articleName, String quantityUnitCode, int orderedQuantity, String amountCurrencyIdentifier, int vatRatePercent, double unitPriceAmount, double vatAmount, double vatExcludedAmount, double amount) {
        this.articleIdentifier = articleIdentifier;
        this.articleName = articleName;
        this.quantityUnitCode = quantityUnitCode;
        this.orderedQuantity = orderedQuantity;
        this.amountCurrencyIdentifier = amountCurrencyIdentifier;
        this.vatRatePercent = vatRatePercent;
        this.unitPriceAmount = unitPriceAmount;
        this.vatAmount = vatAmount;
        this.vatExcludedAmount = vatExcludedAmount;
        this.amount = amount;
    }

    public String getArticleIdentifier() {
        return articleIdentifier;
    }

    public double getVatExcludedAmount() {
        return vatExcludedAmount;
    }

    public void setVatExcludedAmount(double vatExcludedAmount) {
        this.vatExcludedAmount = vatExcludedAmount;
    }

    public void setArticleIdentifier(String articleIdentifier) {
        this.articleIdentifier = articleIdentifier;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getQuantityUnitCode() {
        return quantityUnitCode;
    }

    public void setQuantityUnitCode(String quantityUnitCode) {
        this.quantityUnitCode = quantityUnitCode;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getAmountCurrencyIdentifier() {
        return amountCurrencyIdentifier;
    }

    public void setAmountCurrencyIdentifier(String amountCurrencyIdentifier) {
        this.amountCurrencyIdentifier = amountCurrencyIdentifier;
    }

    public int getVatRatePercent() {
        return vatRatePercent;
    }

    public void setVatRatePercent(int vatRatePercent) {
        this.vatRatePercent = vatRatePercent;
    }

    public double getUnitPriceAmount() {
        return unitPriceAmount;
    }

    public void setUnitPriceAmount(double unitPriceAmount) {
        this.unitPriceAmount = unitPriceAmount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Article{" + "articleIdentifier=" + articleIdentifier + ", articleName=" + articleName + ", quantityUnitCode=" + quantityUnitCode + ", orderedQuantity=" + orderedQuantity + ", amountCurrencyIdentifier=" + amountCurrencyIdentifier + ", vatRatePercent=" + vatRatePercent + ", unitPriceAmount=" + unitPriceAmount + ", vatAmount=" + vatAmount + ", vatExcludedAmount=" + vatExcludedAmount + ", amount=" + amount + '}';
    }

    public String toStringCsv(){
        return "\n;" + articleName + ";" + articleIdentifier + ";" + orderedQuantity + ";"
                + quantityUnitCode + ";" + unitPriceAmount + ";" + ";" + vatRatePercent + ";";
    }
   
}
