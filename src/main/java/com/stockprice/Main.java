package com.stockprice;
import java.io.IOException;


public class Main  {
   
    public static void main(String[] args) throws IOException {
        CalculateMaxPrice cmp = new CalculateMaxPrice();
        ResourceObject ro = cmp.maxProfit("AAPL", "2014");
        System.out.println("Buy date: " + ro.getBuyDate());
        System.out.println("Buy price: " + ro.getBuyPrice());
        System.out.println("Sell date: " + ro.getSellDate());
        System.out.println("Sell price: " + ro.getSellPrice());
        System.out.println("Profit: " + ro.getProfit());
    }
}