package finance.test;

import finance.Investment;
import finance.LoanPayment;
import finance.Report;
import finance.TVMEngine;
import finance.enums.CompoundingOption;

/**
 * Test software for CIS 304 OOP Review Application Challenge.
 * @author JAM
 * @version 160406
 */
public class FinancialCalculationsTest {

    /**
     * Test software for CIS 304 OOP Review Application Challenge.
     * @param args no command line arguments are used.
     */
    public static void main(String[] args) {
        System.out.println("Testing LoanPayment Class");
        System.out.println("\ngetValue () Test Case 1");
        LoanPayment loanPayment = new LoanPayment (350000, 0, 10,
                                               CompoundingOption.ANNUAL, 15);
        TVMEngine calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 2");
        loanPayment = new LoanPayment (350000, 50000, 10,
                                       CompoundingOption.ANNUAL, 15);
        calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\netValue () Test Case 3");
        loanPayment = new LoanPayment (350000, 0, 10,
                                       CompoundingOption.SEMIANNUAL, 15);
        calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 4");
        loanPayment = new LoanPayment (350000, 0, 10,
                                       CompoundingOption.QUARTERLY, 15);
        calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 5");
        loanPayment = new LoanPayment (350000, 0, 10,
                                       CompoundingOption.MONTHLY, 15);
        calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 6");
        loanPayment = new LoanPayment (350000, 0, 10,
                                       CompoundingOption.WEEKLY, 15);
        calculator = loanPayment;
        System.out.println("Payment getValue () result: " + calculator.getValue());

        System.out.println("\ngenerateReport () Test Case 1");
        loanPayment = new LoanPayment (350000, 50000, 10,
                                       CompoundingOption.ANNUAL, 15);
        Report report = loanPayment;
        System.out.println(report.print());

        System.out.println("\ngenerateReport () Test Case 2");
        loanPayment = new LoanPayment (350000, 50000, 10,
                                       CompoundingOption.MONTHLY, 15);
        report = loanPayment;
        System.out.println(report.print());

        System.out.println("\n************************");
        System.out.println("Testing Investment Class");
        System.out.println("\ngetValue () Test Case 1");
        Investment investment = new Investment (0, 100, 10,
                                                CompoundingOption.ANNUAL, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 2");
        investment = new Investment (100, 100, 10,
                                     CompoundingOption.ANNUAL, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 3");
        investment = new Investment (0, 100, 10,
                                     CompoundingOption.SEMIANNUAL, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 4");
        investment = new Investment (0, 100, 10,
                                     CompoundingOption.QUARTERLY, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 5");
        investment = new Investment (0, 100, 10,
                                     CompoundingOption.MONTHLY, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngetValue () Test Case 6");
        investment = new Investment (0, 100, 10,
                                     CompoundingOption.WEEKLY, 20);
        calculator = investment;
        System.out.println("Future getValue () result: " + calculator.getValue());

        System.out.println("\ngenerateReport () Test Case 1");
        investment = new Investment (100, 100, 10,
                                     CompoundingOption.ANNUAL, 20);
        report = investment;
        System.out.println(report.print());

        System.out.println("\ngenerateReport () Test Case 2");
        investment = new Investment (100, 100, 10,
                                     CompoundingOption.MONTHLY, 20);
        report = investment;
        System.out.println(report.print());

    }
    
}