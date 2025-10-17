package M7P1;
//20251015 SDEV200 Java - Module 7, Programming Assignment 2, Chapter 37 - exercise 37.5

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String sAmount = req.getParameter("amount");
        String sInterest = req.getParameter("interest");
        String sYears = req.getParameter("years");

        PrintWriter out = resp.getWriter();

        out.println("<!doctype html><html><head><meta charset='utf-8'><title>Loan Result</title>");
        out.println("<style>body{font-family:Arial;padding:20px;max-width:700px;margin:auto;} .label{font-weight:bold;}</style>");
        out.println("</head><body>");
        out.println("<h1>Loan Payment Result</h1>");

        // Basic validation
        double amount;
        double interest;
        int years;
        try {
            amount = Double.parseDouble(sAmount);
            interest = Double.parseDouble(sInterest);
            years = Integer.parseInt(sYears);
            if (amount <= 0 || years <= 0 || interest < 0) {
                throw new NumberFormatException("Non-positive values not allowed");
            }
        } catch (Exception ex) {
            out.println("<p style='color:red;'>Invalid input: please enter numeric values (amount > 0, years > 0, interest >= 0).</p>");
            out.println("<p><a href=\"loan.html\">Back to form</a></p>");
            out.println("</body></html>");
            return;
        }

        // Compute
        Loan loan = new Loan(interest, years, amount);
        double monthly = loan.getMonthlyPayment();
        double total = loan.getTotalPayment();

        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMinimumFractionDigits(2);
        percent.setMaximumFractionDigits(2);

        out.println("<table>");
        out.printf("<tr><td class='label'>Loan Amount:</td><td>%s</td></tr>%n", currency.format(amount));
        out.printf("<tr><td class='label'>Annual Interest Rate:</td><td>%.3f%%</td></tr>%n", interest);
        out.printf("<tr><td class='label'>Number of Years:</td><td>%d</td></tr>%n", years);
        out.printf("<tr><td class='label'>Monthly Payment:</td><td>%s</td></tr>%n", currency.format(monthly));
        out.printf("<tr><td class='label'>Total Payment:</td><td>%s</td></tr>%n", currency.format(total));
        out.println("</table>");

        out.println("<p><a href=\"loan.html\">Compute another loan</a></p>");
        out.println("</body></html>");
    }

    // Allow GET to forward to form quickly
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("loan.html");
    }
}
