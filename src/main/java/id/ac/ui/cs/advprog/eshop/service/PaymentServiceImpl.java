package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String,String> paymentData){
        if (order == null | method == null | paymentData == null){
            throw new IllegalArgumentException();
        } else{
            Payment result = paymentRepository.addPayment(order,method,paymentData);
            return result;
        }
    }

    @Override
    public Payment setStatus(Payment payment,String status){
        if (payment == null | status == null){
            throw new IllegalArgumentException();
        } else {
            Payment result = paymentRepository.setStatus(payment,status);
            return result;
        }
    }

    @Override
    public Payment getPayment(String paymentId){
        if (paymentId == null){
            throw new IllegalArgumentException();
        } else {
            Payment result = paymentRepository.getPayment(paymentId);
            return result;
        }
    }

    @Override
    public List<Payment> getAllPayments(){
        List<Payment> payments = paymentRepository.getAllPayments();
        return payments;
    }
}
