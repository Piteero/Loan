package com.Loan.Calculation;

import java.util.Scanner;

public class Calculation
{
	private double amount;
	private int period;
	private String assurance;
	private double[] lendingRateTab = { 0.10, 0.09, 0.08, 0.07, 0.12, 0.11, 0.10, 0.09 };
	private double[] commissionTab = { 0.05, 0.04, 0.03, 0.02, 0.07, 0.06, 0.05, 0.04 };
	private double lendingRate;
	private double commission;
	private double assuranceAmount;
	private double leanCost;
	private double instalment;

	public static void runProgram()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Wprowadź kwotę: ");
		double amount = scanner.nextDouble();
		System.out.println("Wprowadź okres: ");
		int period = scanner.nextInt();
		System.out.println("Wprowadź \"T\" jeśli z ubezpieczniem, bądź \"N\" jeśli bez ");
		String assurance = scanner.next();

		Calculation calculation = new Calculation(amount, period, assurance); // nowy
																				// obiekt
																				// klasy
																				// wyliczeniowej
																				// Calculation.

		calculation.setLendingRateAndCommission(); // ustawia oprocentowanie
													// oraz prowizję
		calculation.setAssurance(); // ustawia wartośc ubezpieczenia.
		calculation.setInstalment(); // ustawia ratę.
		calculation.setLoanCost(); // ustawia koszty pożyczki.
		calculation.printOffer(); // drukuje ofertę.

		scanner.close();
	}

	public Calculation(double amount, int period, String assurance)
	{
		this.amount = amount;
		this.period = period;
		this.assurance = assurance;
	}

	public void setLendingRateAndCommission()
	{
		lendingRateAndCommissionVariants(amount, assurance);
	}

	public void setAssurance()
	{
		assuranceCalculation(amount, period);
	}

	public void setInstalment()
	{
		lendingRateCalculation(amount, lendingRate, assuranceAmount, period);
	}

	public void setLoanCost()
	{
		loanCostCalculation();
	}

	public void lendingRateAndCommissionVariants(double amount, String assurance)
	{
		if (assurance.equalsIgnoreCase("T") && amount >= 1000.0 && amount < 10000.0)
		{
			lendingRate = lendingRateTab[0];
			commission = commissionTab[0];
		} else if (assurance.equalsIgnoreCase("T") && amount >= 10000.0 && amount < 50000.0)
		{
			lendingRate = lendingRateTab[1];
			commission = commissionTab[1];
		} else if (assurance.equalsIgnoreCase("T") && amount >= 50000.0 && amount < 100000.0)
		{
			lendingRate = lendingRateTab[2];
			commission = commissionTab[2];
		} else if (assurance.equalsIgnoreCase("T") && amount >= 100000.0 && amount <= 120000.0)
		{
			lendingRate = lendingRateTab[3];
			commission = commissionTab[3];
		} else if (assurance.equalsIgnoreCase("N") && amount >= 1000.0 && amount < 10000.0)
		{
			lendingRate = lendingRateTab[4];
			commission = commissionTab[4];
		} else if (assurance.equalsIgnoreCase("N") && amount >= 10000.0 && amount < 50000.0)
		{
			lendingRate = lendingRateTab[5];
			commission = commissionTab[5];
		} else if (assurance.equalsIgnoreCase("N") && amount >= 50000.0 && amount < 100000.0)
		{
			lendingRate = lendingRateTab[6];
			commission = commissionTab[6];
		} else if (assurance.equalsIgnoreCase("N") && amount >= 100000.0 && amount <= 120000.0)
		{
			lendingRate = lendingRateTab[7];
			commission = commissionTab[7];
		} else
		{
			System.out.println("Niepoprawne dane, wprowadź dane ponownie");
		}
	}

	public void assuranceCalculation(double amount, int period)
	{
		assuranceAmount = (amount * 0.0025) * period;
	}

	public void lendingRateCalculation(double amount, double lendingRate, double assuranceAmount, int period)
	{
		instalment = ((amount * (lendingRate + commission)) + amount + assuranceAmount) / (period);
	}

	public void loanCostCalculation()
	{
		leanCost = (instalment * period) - amount;
	}

	public void printOffer()
	{
		if (amount >= 1000 && amount <= 120000 && period > 0 && period < 121 && assurance.equalsIgnoreCase("T")
				|| assurance.equalsIgnoreCase("N"))
		{
			System.out.print("                                                -- Oferta pożyczki: --\n Rata:");
			System.out.printf("% .2f", instalment);
			System.out.print("zł  Oprocentowanie: ");
			System.out.printf("% .2f", lendingRate * 100);
			System.out.print("%" + "  Prowizja: ");
			System.out.printf("% .2f", commission * 100);
			System.out.print("%" + "  Ubezpieczenie: ");
			System.out.printf("% .2f", assuranceAmount);
			System.out.print("zł  Koszt pożyczki: ");
			System.out.printf("% .2f", leanCost);
			System.out.print("zł");
		}
	}

}
