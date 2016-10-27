package finance;

import finance.enums.CompoundingOption;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * This class provides generalized time-value of money (TVM) functions and can
 * be used as the calculation engine for financial applications. The API for
 * this class uses time periods specified in years, interest rate (or return)
 * specified as an annual percentage rate (APR), and the compounding frequency
 * is specified using the enumerated class finance.enum.CompoundingOption.
 * Calculations performed by the TVMEngine class are based on the values of the
 * following five TVM registers (implemented as instance variables):<ol>
 * <li>Years: the number of years associated with the financial calculation
 * (e.g., the duration of a loan or investment)</li>
 * <li>APR: the annual percentage interest rate (in the case of a loan) or the
 * annual return (in the case of an investment)</li>
 * <li>PV: the present value. This would be the amount borrowed (in the case of
 * a loan) or an initial investment.</li>
 * <li>PMT: the payment. This would be a periodic loan payment or a periodic
 * investment.</li>
 * <li>FV: the future value. This would be a balloon payment on a loan or the
 * future value of an investment.</li>
 * </ol><br>
 * Financial calculations can be performed by following these steps:
 * <ol>
 * <li>Set the compounding frequency with the setCompounding () method.</li>
 * <li> To perform a financial calculation, you must know 4 out of the 5 TVM
 * register values. Set 4 of the 5 TVM registers to the values you know, and set
 * the remaining TVM register to zero.</li>
 * <li>Call the appropriate "calc" method to find the value of the unknown TVM
 * register. For example, call the calcPMT() method to find the periodic loan
 * payment, and call the calcFV() method to find the future value of an
 * investment.</li>
 * </ol><br>
 * The TVMEngine class is abstract and must be inherited by a subclass. If the
 * subclass constructor is passed the compounding interval and 4 out of the 5
 * TVM register values, then the TVMEngine constructor with parameters can be
 * used. Otherwise, use the constructor without parameters. In this case you
 * must set the compounding frequency with the setCompounding () method and set
 * the TVM registers by using the various "set" methods (e.g., setPV(), setPMT,
 * etc.).
 * <p>
 * The calculations performed by this class assume the cash flow sign convention
 * used by standard financial calculators. This convention is that cash flowing
 * away from you is negative, and cash flowing toward you is positive.
 * <p>
 * A convenience toCurrency () method is provided to convert numbers to text
 * formatted as the currency set by the computer's operating system.
 *
 * @author koluongMBP
 * @version 161012
 */
public abstract class TVMEngine {

    private double annualRate;
    private double periodRate;
    private double pv;
    private double fv;
    private double pmt;
    private double df;
    private double periods;
    private double years;
    private double periodsPerYear;
    private CompoundingOption compounding;
    private NumberFormat currency;

    /**
     * Creates a TVM engine object with the TVM registers cleared and
     * compounding set to annual.
     */
    protected TVMEngine() {
        clearTVM();
        currency = NumberFormat.getCurrencyInstance();
    }

    /**
     * Creates a TVM engine object with the TVM function configured per the
     * constructor parameters.
     *
     * @param years the number of years over which the TVM calculation applies
     * @param annualRate the interest rate or return specified as an annual
     * percentage rate (APR)
     * @param compounding the interest compounding interval
     * @param pv present value (e.g., loan amount or initial investment) -
     * standard cash flow sign convention applies. Set this to zero if unknown.
     * @param pmt payment (e.g., loan payment or periodic investment) - standard
     * cash flow sign convention applies. Set this to zero if unknown.
     * @param fv future value (e.g., value of an investment over a period of
     * time) - standard cash flow sign convention applies. Set this to zero if
     * unknown.
     */
    protected TVMEngine(double years, double annualRate,
            CompoundingOption compounding, double pv,
            double pmt, double fv) {
        this();
        this.years = years;
        propogateYearsChange();
        propogateAnnualRateChange(annualRate);
        periodsPerYear = compounding.getPeriodsPerYear();
        this.compounding = compounding;
        changeCompounding();
        this.pv = pv;
        this.pmt = pmt;
        this.fv = fv;
    }

    /**
     * Clears the TVM calculation parameters and sets compounding to annual
     *
     */
    protected final void clearTVM() {

        // Set default compounding
        compounding = CompoundingOption.ANNUAL;
        periodsPerYear = compounding.getPeriodsPerYear();

        // Initialize remaining TVM registers
        pv = 0;
        fv = 0;
        pmt = 0;
        periods = 0;
        years = 0;
        periodRate = 0;
        annualRate = 0;

        // Initialize the discount factor
        df = 1;
    }

    /**
     * Sets the interest rate or rate of return.
     *
     * @param apr the interest rate or return specified as an annual percentage
     * rate (APR)
     */
    public final void setAPR(double apr) {
        propogateAnnualRateChange(apr);
    }

    /**
     * Sets the interest compounding frequency.
     *
     * @param cOption the interest compounding interval
     */
    public final void setCompounding(CompoundingOption cOption) {
        periodsPerYear = cOption.getPeriodsPerYear();
        compounding = cOption;
        changeCompounding();
    }

    /**
     * Sets the present value (e.g., loan amount or initial investment).
     *
     * @param pv present value (standard cash flow sign convention applies)
     */
    protected final void setPV(double pv) {
        this.pv = pv;
    }

    /** 
     * Sets the future value (e.g., value of an investment over a period of
     * time).
     *
     * @param fv future value (standard cash flow sign convention applies)
     */
    protected final void setFV(double fv) {
        this.fv = fv;
    }

    /**
     * Sets the payment (e.g., loan payment or periodic investment).
     *
     * @param pmt payment (standard cash flow sign convention applies)
     */
    protected final void setPMT(double pmt) {
        this.pmt = pmt;
    }

    /**
     * Sets the time period (in years) over which the financial calculations
     * will apply (i.e., loan duration or amount of time money is invested).
     *
     * @param years the number of years over which the TVM calculation applies
     */
    public final void setYears(double years) {
        this.years = years;
        propogateYearsChange();
    }

    protected double getPV() {
        return pv;
    }

    protected double getFV() {
        return fv;
    }

    protected double getPMT() {
        return pmt;
    }

    public double getAPR() {
        return annualRate * 100;
    }

    public double getYears() {
        return years;
    }

    public CompoundingOption getCompounding() {
        return compounding;
    }

    /**
     * Calculate the payment (e.g., loan payment or periodic investment).
     *
     * @return payment (standard cash flow sign convention applies)
     */
    protected double calcPMT() {
        pmt = (-pv - fv / df) * periodRate / (1 - 1 / df);
        return pmt;
    }

    /**
     * Calculate the future value (e.g., value of an investment
     * over a period of time).
     *
     * @return future value (standard cash flow sign convention applies)
     */
    protected double calcFV() {
        fv = (-pv - (pmt / periodRate) * (1 - 1 / df)) * df;
        return fv;
    }

    /**
     * Converts numbers to text formatted as the default currency specified by
     * the operating system.
     *
     * @param number the number to be rounded and formatted as currency
     * @return text representation of the specified number formatted as the
     * currency of default locale (specified by the operating system) and
     * rounded to the decimal places used that currency.
     */
    public String toCurrency(double number) {
        BigDecimal bigNumber = BigDecimal.valueOf(number);
        BigDecimal roundedNumber = bigNumber.setScale(currency.getMaximumFractionDigits(),
                RoundingMode.HALF_UP);
        return currency.format(roundedNumber);
    }

    /**
     * Abstract method to be implemented by subclasses.
     *
     * @return value result depending on subclass operations.
     */
    public abstract String getValue();

    ///////////////////////////////////////////
    //Private methods - NOT PART OF THE API!!!
    private void propogateAnnualRateChange(double annualRate) {
        this.annualRate = annualRate / 100.0;
        periodRate = this.annualRate / periodsPerYear;
        df = calcDF();
    }

    private void changeCompounding() {
        periodRate = annualRate / periodsPerYear;
        periods = years * periodsPerYear;
        df = calcDF();
    }

    private void propogateYearsChange() {
        periods = years * periodsPerYear;
        df = calcDF();
    }

    private double calcDF() {
        return Math.pow(1 + periodRate, periods);
    }

}
