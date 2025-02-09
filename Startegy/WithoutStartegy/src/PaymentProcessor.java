public class PaymentProcessor {
    public void processor(String paymentType){
        switch (paymentType){
            case "PAYPAL"-> new PaypalWay().Pay();
            case "DEBITCARD"-> new DebitCardWay().Pay();
        }
    }
}
