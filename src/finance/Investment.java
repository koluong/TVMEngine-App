package finance;

import finance.enums.CompoundingOption;

/**
 * The Investment class calculates the future value of an investment after a specified number of years. It also provides a summary report of the investment parameters. This class inherits the TVMEngine class and implements the Report interface.
 *
 * @author KoluongMBP
 * @version 161012
 */
public class Investment extends TVMEngine implements Report {

    /**
     * This class constructor does not accept parameters. Parameters must be set with the appropriate set() methods. The investment parameters are provided to the object through the constructor.
     * 
     */
    public Investment() {
        super();

    }

    /**
     *The class constructor creates an Investment object configured to calculate future value of an investment. The investment parameters are provided to the object through the constructor.
     *
     * @param initialInvestment the amount invested at the beginning of the investment term.
     * @param periodicPayment the amount invested at regular intervals over the length of the investment.
     * @param interestRate the return on the investment. This parameter is expressed as an annual percentage rate (APR).
     * @param compounding indicates how often interest is added to the value of the investment.
     * @param yearsInvested the length of time the investment is made in years.
     */
    public Investment(double initialInvestment, double periodicPayment, double interestRate, CompoundingOption compounding, double yearsInvested) {
        super();
        setPV(initialInvestment);
        setPMT(periodicPayment);
        setAPR(interestRate);
        setCompounding(compounding);
        setYears(yearsInvested);
    }

    /**
     * Sets the initial investment amount associated with this investment.
     * 
     * @param initialInvestment the amount invested at the beginning of the investment term.
     */
    public void setInitialInvestment(double initialInvestment) {
        setPV(initialInvestment);
    }

    /**
     * Sets the periodic investment amount associated with this investment.
     * 
     * @param periodicInvestment the amount invested at regular intervals over the length of the investment.
     */
    public void setPeriodicInvestment(double periodicInvestment) {
        setPMT(periodicInvestment);
    }

    /**
     * Returns the initial investment amount associated with this investment.
     * 
     * @return the initial investment amount.
     */
    public double getInitialInvestment() {
        return getPV();
    }

    /**
     * Returns the periodic investment associated with this investment.
     * 
     * @return the periodic investment amount.
     */
    public double getPeriodicInvestment() {
        return getPMT();
    }

    /**
     * Provides the future value of an investment as text formatted as US currency.
     * 
     * @return a String object representing the investment future value formatted as US currency rounded to two decimal places.
     */
    @Override
    public String getValue() {
        return toCurrency(-calcFV());
    }

    /**
     * Provides a text summary report of the investment. The report includes the initial investment amount, periodic investment amount, annual return, compounding, length of the investment in years, and the future value of the investment.
     * 
     * @return a String object containing a summary of the investment parameters.
     */
    @Override
    public String print() {
        return "\nInvestment Value Summary"
                + "\nInitial Investment: " + toCurrency(getPV())
                + "\nPeriodic Investment (" + getCompounding().toString().toLowerCase() + "): " + toCurrency(getPMT())
                + "\nAnnual Return: " + getAPR() + "%"
                + "\nInvestment after " + getYears() + " years: " + getValue();
    }
}
