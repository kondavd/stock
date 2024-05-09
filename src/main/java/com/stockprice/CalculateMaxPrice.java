package com.stockprice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * The CalculateMaxPrice class is used to calculate the maximum profit that can be made from buying and selling stocks within a given year.
 *
 * The class has a single public method, maxProfit, which takes a stock name and a year as parameters.
 *
 * The maxProfit method works as follows:
 * - It first initializes a BufferedReader to read from a CSV file located at a specified path, which contains the stock data.
 * - It then creates a new ResourceObject instance, which will hold the result.
 * - It reads the first line of the file to skip the header.
 * - It initializes variables to keep track of the buy price and date, sell price and date, and the maximum profit.
 * - It then enters a while loop, which continues until there are no more lines in the file.
 *   - Inside the loop, it splits the current line into an array of strings.
 *   - If the date on the current line contains the specified year, it checks if the low price on that day is less than the current buy price. If so, it updates the buy price and date.
 *   - It also checks if the profit that can be made by buying at the buy price and selling at the high price on that day is greater than the current maximum profit. If so, it updates the maximum profit, and the sell price and date.
 * - After the loop, it sets the buy and sell dates, prices, and the maximum profit on the ResourceObject instance.
 * - It then closes the BufferedReader and returns the ResourceObject instance.
 *
 * The method also includes catch blocks to handle possible IOExceptions and NumberFormatExceptions, and a finally block to ensure the BufferedReader is closed.
 */
public class CalculateMaxPrice {
    public ResourceObject maxProfit(String stockName, String year) {
        String stocksFolderLocation = "C:/Java - StockPrice/";
        BufferedReader reader = null;
        ResourceObject ro = new ResourceObject();
        try {
            FileReader file = new FileReader(stocksFolderLocation + stockName + ".csv");
            reader = new BufferedReader(file);
            String line = reader.readLine(); // skip header
            line = reader.readLine();
            double buyPrice = Double.MAX_VALUE;
            String buyDate = null;
            double sellPrice = 0;
            String sellDate = null;
            double profit = 0;
            while (line != null) {
                String[] data = line.split(",");
                if (data[0].contains(year)) {
                    double low = Double.parseDouble(data[3]);
                    double high = Double.parseDouble(data[2]);

                    if (low < buyPrice) {
                        buyPrice = low;
                        buyDate = data[0];
                    }

                    if (high - buyPrice > profit) {
                        profit = high - buyPrice;
                        sellPrice = high;
                        sellDate = data[0];
                    }
                }
                line = reader.readLine();
            }
            ro.setBuyDate(buyDate);
            ro.setBuyPrice(buyPrice);
            ro.setSellDate(sellDate);
            ro.setSellPrice(sellPrice);
            ro.setProfit(profit);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing a number: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the reader: " + e.getMessage());
                }
            }
        }
        return ro;
    }
}