// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        StockMarket stockMarket=new StockMarket();
        Observer<Stock> sub1=new Subscriber("sub1");
        Observer<Stock> sub2=new Subscriber("sub2");
        stockMarket.AddObserver(sub1);
        stockMarket.AddObserver(sub2);

        stockMarket.addStock("xyz",500);
        stockMarket.addStock("af",1000);
        stockMarket.removeObserver(sub2);
        stockMarket.updatePriceOfStock("xyz",1000);

    }
}