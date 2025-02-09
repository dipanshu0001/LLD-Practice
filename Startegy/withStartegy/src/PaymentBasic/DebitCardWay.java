package PaymentBasic;

import PaymentBasic.IPaymentStartegy;

public class DebitCardWay implements IPaymentStartegy {
    @Override
    public void pay() {
        System.out.print("Paying Through PaymentBasic.DebitCardWay");
    }
}
