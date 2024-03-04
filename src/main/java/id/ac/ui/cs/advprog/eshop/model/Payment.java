package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String,String> paymentData;

    public Payment(String id,String method,Map<String,String> paymentData){
        this.id = id;
        this.status = PaymentStatus.WAITING.getValue();

        if(PaymentMethod.contains(method)){
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }

        boolean isVoucherMethodBankPayment = (method.equals(PaymentMethod.VOUCHER.getValue()) & (paymentData.containsKey("bankName") | paymentData.containsKey("referenceCode")));
        boolean isBankMethodVoucherPayment = (method.equals(PaymentMethod.BANK.getValue()) & paymentData.containsKey("voucherCode"));
        boolean isInvalidMethodPayment = isBankMethodVoucherPayment | isVoucherMethodBankPayment;

        if (paymentData.isEmpty() | isInvalidMethodPayment){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }

    public Payment(String id, String method, Map<String,String> paymentData,String status){
        this(id,method,paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status){
        if (PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
