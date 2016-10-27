package finance.enums;

/**
 * Provides a set of interest rate compounding intervals.
 * @author JAM
 * @version 150108
 */
public enum CompoundingOption {

    /**
     * Indicates that the compounding option being used is not part of this
     * enumeration.
     */
    CUSTOM (),

    /**
     * Compounding occurs once per year.
     */
    ANNUAL (1),

    /**
     * Compounding occurs twice a year.
     */
    SEMIANNUAL (2),

    /**
     * Compounding occurs four times a year.
     */
    QUARTERLY (4),

    /**
     * Compounding occurs 12 times a year.
     */
    MONTHLY (12),

    /**
     * Compounding occurs 52 times a year.
     */
    WEEKLY (52);
    private double periodsPerYear;
    private String optionText;

    private CompoundingOption () {
        periodsPerYear = 0;
        optionText = "Custom";
    }

    private CompoundingOption (double periodsPerYear) {
        this.periodsPerYear = periodsPerYear;
        if (periodsPerYear == 1) {
            optionText = "Annual";
        } else if (periodsPerYear == 2) {
            optionText = "Semiannual";
        } else if (periodsPerYear == 4) {
            optionText = "Quarterly";
        } else if (periodsPerYear == 12) {
            optionText = "Monthly";
        } else if (periodsPerYear == 52) {
            optionText = "Weekly";
        }        
    }

    /**
     * Provides the compounding periods per year represented by the selected
     * compounding option.
     * @return a number representing the compounding periods per year
     */
    public double getPeriodsPerYear () {
        return periodsPerYear;
    }

    /**
     * Provides a title case representation of the selected compounding option.
     * @return A title case text representation of the compounding option.
     */
    public String getTitleCaseText () {
        return optionText;
    }
}
