package finance;

import finance.enums.CompoundingOption;

/**
 * The LoanPayment class calculates the periodic payment needed to pay off a loan. It also provides a summary report of the loan parameters. The class interface is based on a purchase amount and a down payment. Internally, the class calculates the amount to be financed by the loan as the purchase amount less the down payment. This class inherits the TVMEngine class and implements the Report interface.
 *
 * @author KoluongMBP
 * @version 161012
 */
public class LoanPayment extends TVMEngine implements Report {

    private double purchaseAmount;
    private double downPayment;

    /**
     * This class constructor does not accept parameters. Parameters must be set through the appropriate set() methods. The loan parameters are provided to the object through the constructor.
     */
    public LoanPayment() {
        super();
        purchaseAmount = 0;
        downPayment = 0;
    }

    /**
     * The class constructor creates a LoanPayment object configured to calculate a loan payment. Parameters are used. The loan parameters are provided to the object through the constructor.
     *
     * @param purchaseAmount the amount of the purchase to be made by the borrower.
     * @param downPayment the down payment to be made by the borrower against the purchase amount.
     * @param interestRate the annual percentage rate (APR) to be applied to the loan.
     * @param compounding indicates how often interest is added to the loan principal.
     * @param loanDuration the duration of the loan in years.
     */
    public LoanPayment(double purchaseAmount, double downPayment, double interestRate, CompoundingOption compounding, double loanDuration) {
        super();
        this.purchaseAmount = purchaseAmount;
        this.downPayment = downPayment;
        setCompounding(compounding);
        updateLoan();
        setAPR(interestRate);
        setYears(loanDuration);
    }

    /**
     * Sets the purchase amount associated with this loan.
     *
     * @param purchaseAmount the amount of the purchase to be made by the borrower.
     */
    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        updateLoan();
    }

    /**
     * Sets the down payment amount associated with this loan.
     *
     * @param downPayment the down payment to be made by the borrower against the purchase amount.
     */
    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
        updateLoan();
    }

    /**
     * Returns the purchase amount associated with this loan.
     *
     * @return the purchase amount.
     */
    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    /**
     * Returns the down payment amount associated with this loan.
     *
     * @return the down payment amount.
     */
    public double getDownPayment() {
        return downPayment;
    }

    /**
     * Provides a text summary report of the loan. The report includes the amount purchased, down payment, interest rate (APR), compounding, loan duration, and periodic payment.
     *
     * @return a String object containing a summary of the loan parameters.
     */
    @Override
    public String print() {

        return "\nLoan Payment Summary"
                + "\nPurchase Amount: " + toCurrency(purchaseAmount)
                + "\nDown Payment: " + toCurrency(downPayment)
                + "\nAmount Financed: " + toCurrency(getPV())
                + "\nAPR: " + getAPR() + "%"
                + "\nCompounding: " + getCompounding().toString().toLowerCase()
                + "\nLoan Duration (years): " + getYears()
                + "\nPayment " + "(" + getCompounding().toString().toLowerCase() + "): " + getValue();

    }

    /**
     * Provides the periodic payment required to pay off the amount financed. The payment is provided as text formatted as US currency.
     *
     * @return a String object representing the loan payment formatted as US currency rounded to two decimal places.
     */
    @Override
    public String getValue() {
        return toCurrency(-calcPMT());
    }

    private void updateLoan() {
        setPV(this.purchaseAmount - this.downPayment);
    }

}
