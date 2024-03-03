package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp(){
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode","ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        assertThrows(IllegalArgumentException.class,() -> {
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","PINJAMAN",paymentData, PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testCreatePaymentVoucherMethod(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), paymentData,PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentMethod.VOUCHER.getValue(),payment.getMethod());
        assertEquals("78952556-012a-4c07-b546-54eb1396d79b",payment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment.getStatus());
        assertEquals(1,payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("ESHOP1234ABC5678",payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b",PaymentMethod.VOUCHER.getValue(), paymentData,"MEOW");
        });
    }

    @Test
    void testCreatePaymentDefaultStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b",PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.WAITING.getValue(),payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyPaymentData(){
        this.paymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b",PaymentMethod.VOUCHER.getValue(), paymentData,PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testEditPaymentInvalidStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b",PaymentMethod.VOUCHER.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class,() -> payment.setStatus("MEOW"));
    }

    @Test
    void testEditPaymentSuccessStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b",PaymentMethod.VOUCHER.getValue(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment.getStatus());
    }
}
