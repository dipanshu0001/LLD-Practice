public class Stock {
    private final String stockName;
    private int price;

    public Stock(String stockName,int price){
        this.stockName=stockName;
        this.price=price;
    }
    public String getStockName(){
        return this.stockName;
    }
    public int getPrice(){
        return this.price;
    }
    public void updatePrice(int price){
        this.price=price;
    }
}
