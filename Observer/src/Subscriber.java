public class Subscriber implements Observer<Stock>{
    String name;
    public Subscriber(String name){
        this.name=name;
    }
    @Override
    public void update(Stock data) {
        System.out.println(name+"stock price of stock :"+data.getStockName()+"got updated with price: "+data.getPrice());
    }
}
