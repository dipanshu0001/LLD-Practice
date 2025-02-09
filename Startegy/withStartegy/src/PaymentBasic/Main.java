package PaymentBasic;

import PaymentBasic.IPaymentStartegy;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void updateData(int[] data){
        for(int i=0;i<5;i++){
            data[i]=10;
        }
    }
    public  static void updatValue(int x){
        x=15;
    }
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        IPaymentStartegy paymentStartegy = null;
        System.out.print("Hello and welcome!\n");
        Scanner sc=new Scanner(System.in);

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {
            String payType=sc.nextLine();
            if(payType.equals("1")){
                paymentStartegy=new PaymentBasic.PayPalWay();
            }else if(payType.equals("2")){
                paymentStartegy=new PaymentBasic.DebitCardWay();
            }
            paymentStartegy.pay();
        }
//        assert paymentStartegy != null;
//        int x=0;//primitive data type ->pass by value
//        updatValue(x);
//        System.out.println(x);//0
//
//        int[] data=new int[5];//0,0,0,0,0//array is also object object refernce object
//        for(int i=0;i<5;i++){
//            System.out.print(data[i]);
//        }
//        System.out.println();
//        updateData(data);
//        for(int i:data){
//            System.out.print(i);//10,10,10,10,10
//        }

    }
}