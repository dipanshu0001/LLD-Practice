import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StockMarket implements Subject<Stock>{
    private Set<Observer<Stock>> observersSet;
    private Map<String,Integer> stockList;
    public StockMarket(){
        observersSet=new HashSet<>();
        stockList=new HashMap<>();
    }
    public void addStock(String stockName,int price){
        stockList.putIfAbsent(stockName,price);
        notifyObserver(new Stock(stockName, price));
    }
    @Override
    public void AddObserver(Observer<Stock> observer) {
        observersSet.add(observer);
    }

    @Override
    public void removeObserver(Observer<Stock> observer) {
        observersSet.remove(observer);
    }

    public void updatePriceOfStock(String stock,int price){
        stockList.putIfAbsent(stock,price);
        notifyObserver(new Stock(stock,price));
    }
    @Override
    public void notifyObserver(Stock stock) {
        for(Observer<Stock> observer:observersSet){
            System.out.print("Calling update for All the Subscriber");
            observer.update(stock);

        }
    }
}
