package PaymentBasic;

import PaymentBasic.IPaymentStartegy;

public class PaymentContext {
    private IPaymentStartegy paymentStartegy;

    public void setPaymentStartegy(IPaymentStartegy startegy){
        paymentStartegy=startegy;
    }

    public void paymentRouting(int type){

    }
}
