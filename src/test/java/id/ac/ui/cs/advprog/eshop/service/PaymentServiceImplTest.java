package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L,"Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("13652556-012a-4c07-b546-54eb1396d79a",
                products, 1708570000L,"Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testAddPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment payment = new Payment(orders.getFirst().getId(),PaymentMethod.VOUCHER.getValue(),paymentData);
        doReturn(payment).when(paymentRepository).addPayment(orders.getFirst(),PaymentMethod.VOUCHER.getValue(),paymentData);


        Payment result = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(),paymentData);

        assertEquals(orders.getFirst().getId(),result.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(),result.getMethod());
        assertEquals(paymentData.size(),result.getPaymentData().size());
        assertTrue(result.getPaymentData().containsKey("voucherCode"));
        assertEquals(paymentData.get("voucherCode"),result.getPaymentData().get("voucherCode"));
        verify(paymentRepository,times(1)).addPayment(orders.getFirst(),PaymentMethod.VOUCHER.getValue(),paymentData);
    }

    @Test
    void testAddPaymentNullArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment result = paymentService.addPayment(orders.getFirst(),PaymentMethod.VOUCHER.getValue(),null);
        });
    }

    @Test
    void testSetStatusPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment payment = new Payment(orders.getFirst().getId(),PaymentMethod.VOUCHER.getValue(),paymentData);
        Payment newPayment = new Payment(orders.getFirst().getId(),PaymentMethod.VOUCHER.getValue(),paymentData,PaymentStatus.SUCCESS.getValue());
        doReturn(newPayment).when(paymentRepository).setStatus(payment,PaymentStatus.SUCCESS.getValue());

        Payment result = paymentService.setStatus(payment,PaymentStatus.SUCCESS.getValue());
        assertEquals(newPayment.getId(),result.getId());
        verify(paymentRepository,times(1)).setStatus(payment,PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testSetStatusNullArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment result = paymentService.setStatus(null,PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testGetPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment payment = new Payment(orders.getFirst().getId(),PaymentMethod.VOUCHER.getValue(),paymentData);
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());


        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(),result.getId());
        verify(paymentRepository,times(1)).getPayment(payment.getId());
    }

    @Test
    void testGetPaymentNullId(){
        assertThrows(IllegalArgumentException.class,()->{
            Payment result = paymentService.getPayment(null);
        });
    }

    @Test
    void testGetAllPayments(){
        Map<String,String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode","ESHOP1234ABC5678");
        Payment result1 = new Payment(orders.getFirst().getId(),PaymentMethod.VOUCHER.getValue(),paymentData1);

        Map<String,String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode","ESHOP1234ABD5678");
        Payment resullt2 = new Payment(orders.get(1).getId(),PaymentMethod.VOUCHER.getValue(),paymentData2);

        List<Payment> payments = new ArrayList<>();
        payments.add(result1);
        payments.add(resullt2);

        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> allPayments = paymentService.getAllPayments();
        assertEquals(2,allPayments.size());
        assertEquals(orders.getFirst().getId(),allPayments.getFirst().getId());
        assertEquals(orders.get(1).getId(),allPayments.get(1).getId());
        verify(paymentRepository,times(1)).getAllPayments();
    }
}
