package PaymentBasic;

import PaymentBasic.IPaymentStartegy;

public class PayPalWay implements IPaymentStartegy {

    @Override
    public void pay() {
        System.out.print("Paying Through PayPal");
    }
}
