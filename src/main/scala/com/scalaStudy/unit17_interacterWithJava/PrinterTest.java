package com.scalaStudy.unit17_interacterWithJava;

public class PrinterTest {

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.printAllArgs("haha","sweet");

        Stock stock = new Stock(11.2d,"000285");
        stock.setPrice(15.2d);
        stock.setCode("000275");
        System.out.println(stock);
    }
}
