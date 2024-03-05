package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

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
    void testAddPaymentValidVoucherPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment result = paymentRepository.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(),paymentData);

        Payment findResult = paymentRepository.getPayment(orders.getFirst().getId());
        assertEquals(orders.getFirst().getId(),result.getId());
        assertEquals(orders.getFirst().getId(),findResult.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(),findResult.getMethod());
        assertEquals(paymentData.size(),findResult.getPaymentData().size());
        assertTrue(findResult.getPaymentData().containsKey("voucherCode"));
        assertEquals(paymentData.get("voucherCode"),findResult.getPaymentData().get("voucherCode"));
        assertEquals(PaymentStatus.SUCCESS.getValue(),findResult.getStatus());
    }

    @Test
    void testAddPaymentInvalidVoucherPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","SHOP1234ABC5678");
        Payment result = paymentRepository.addPayment(orders.getFirst(),PaymentMethod.VOUCHER.getValue(),paymentData);
        Payment findResult = paymentRepository.getPayment(orders.getFirst().getId());

        assertEquals(orders.getFirst().getId(),result.getId());
        assertEquals(orders.getFirst().getId(),findResult.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(),findResult.getStatus());
    }

    @Test
    void testAddPaymentValidBankPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("bankName","Bank Indonesia");
        paymentData.put("referenceCode","INV-1");
        Payment result = paymentRepository.addPayment(orders.getFirst(), PaymentMethod.BANK.getValue(),paymentData);

        Payment findResult = paymentRepository.getPayment(orders.getFirst().getId());
        assertEquals(orders.getFirst().getId(),result.getId());
        assertEquals(orders.getFirst().getId(),findResult.getId());
        assertEquals(PaymentMethod.BANK.getValue(),findResult.getMethod());
        assertEquals(paymentData.size(),findResult.getPaymentData().size());
        assertTrue(findResult.getPaymentData().containsKey("bankName"));
        assertEquals(paymentData.get("bankName"),findResult.getPaymentData().get("bankName"));
        assertTrue(findResult.getPaymentData().containsKey("referenceCode"));
        assertEquals(paymentData.get("referenceCode"),findResult.getPaymentData().get("referenceCode"));
        assertEquals(PaymentStatus.SUCCESS.getValue(),findResult.getStatus());
    }

    @Test
    void testAddPaymentInvalidBankPayment(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("bankName","Bank Indonesia");
        paymentData.put("referenceCode",null);
        Payment result = paymentRepository.addPayment(orders.getFirst(), PaymentMethod.BANK.getValue(),paymentData);
        Payment findResult = paymentRepository.getPayment(orders.getFirst().getId());

        assertEquals(orders.getFirst().getId(),result.getId());
        assertEquals(orders.getFirst().getId(),findResult.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(),findResult.getStatus());
    }

    @Test
    void testSetStatusSuccess(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment payment = paymentRepository.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(),paymentData);

        Payment result = paymentRepository.setStatus(payment,PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getId(),result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(),result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(),orders.getFirst().getStatus());
    }

    @Test
    void testSetStatusReject(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        Payment payment = paymentRepository.addPayment(orders.getFirst(),PaymentMethod.VOUCHER.getValue(), paymentData);

        Payment result = paymentRepository.setStatus(payment,PaymentStatus.REJECTED.getValue());
        assertEquals(payment.getId(),result.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(),result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(),orders.getFirst().getStatus());
    }

    @Test
    void testGetPaymentInvalidId(){
        assertThrows(NoSuchElementException.class,()->{
            Payment findResult = paymentRepository.getPayment("ZCZC");
        });
    }

    @Test
    void testGetAllPayments(){
        Map<String,String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode","ESHOP1234ABC5678");
        Payment result1 = paymentRepository.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(),paymentData1);

        Map<String,String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode","ESHOP1234ABD5678");
        Payment result2 = paymentRepository.addPayment(orders.get(1),PaymentMethod.VOUCHER.getValue(),paymentData2);

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(2,allPayments.size());
        assertEquals(orders.getFirst().getId(),allPayments.getFirst().getId());
        assertEquals(orders.get(1).getId(),allPayments.get(1).getId());
    }
}
