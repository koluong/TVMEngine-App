package finance;

/**
 * Interface for generating summary reports for financial calculations.
 * @author koluongMBP
 * @version 161012
 */
public interface Report {
    
    /**
     * Generates a summary report for a selected financial calculation.
     * @return text summary report of the financial calculation
     */
    public String print ();
 
}
