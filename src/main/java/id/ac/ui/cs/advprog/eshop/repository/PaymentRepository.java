package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<List<Object>> paymentsData = new ArrayList<>();

    public Payment addPayment(Order order,String method,Map<String,String> paymentData){
        try{
            Payment payment = new Payment(order.getId(),method,paymentData);
            List<Object> curPaymentData = new ArrayList<>();
            curPaymentData.add(payment);
            curPaymentData.add(order);
            paymentsData.add(curPaymentData);

            if(payment.getMethod().equals(PaymentMethod.VOUCHER.getValue())){
                String voucherCode = payment.getPaymentData().get("voucherCode");
                if (voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && voucherCode.replaceAll("[^0-9]", "").length() == 8){
                    payment.setStatus(PaymentStatus.SUCCESS.getValue());
                    order.setStatus(OrderStatus.SUCCESS.getValue());
                } else {
                    payment.setStatus(PaymentStatus.REJECTED.getValue());
                    order.setStatus(OrderStatus.FAILED.getValue());
                }
            } else if (payment.getMethod().equals(PaymentMethod.BANK.getValue())){
                String bankName = payment.getPaymentData().get("bankName");
                String referenceCode = payment.getPaymentData().get("referenceCode");
                if (bankName == null | referenceCode == null){
                    payment.setStatus(PaymentStatus.REJECTED.getValue());
                    order.setStatus(OrderStatus.FAILED.getValue());
                } else {
                    payment.setStatus(PaymentStatus.SUCCESS.getValue());
                    order.setStatus(OrderStatus.SUCCESS.getValue());
                }
            }
            return payment;
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public Payment setStatus(Payment payment,String status){
        for (List<Object> curPaymentData:paymentsData){
            Payment curPayment = (Payment)(curPaymentData.get(0));
            Order curOrder = (Order)(curPaymentData.get(1));
            if(payment.getId().equals(curPayment.getId())){
                curPayment.setStatus(status);
                if (status.equals(PaymentStatus.SUCCESS.getValue())){
                    curOrder.setStatus(OrderStatus.SUCCESS.getValue());
                } else if (status.equals(PaymentStatus.REJECTED.getValue())){
                    curOrder.setStatus(OrderStatus.FAILED.getValue());
                }
                return curPayment;
            }
        }
        return null;
    }

    public Payment getPayment(String paymentId){
        for (List<Object> curPaymentData:paymentsData){
            Payment curPayment = (Payment) (curPaymentData.getFirst());
            if(paymentId.equals(curPayment.getId())){
                return curPayment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments(){
        List<Payment> payments = new ArrayList<>();
        for (List<Object> curPaymentData:paymentsData){
            Payment curPayment = (Payment) (curPaymentData.getFirst());
            payments.add(curPayment);
        }
        return payments;
    }
}
