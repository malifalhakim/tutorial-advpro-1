package id.ac.ui.cs.advprog.eshop.model;

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
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","PINJAMAN",paymentData,"SUCCESS");
        });
    }

    @Test
    void testCreatePaymentVoucherMethod(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData,"SUCCESS");
        assertEquals("VOUCHER",payment.getMethod());
        assertEquals("78952556-012a-4c07-b546-54eb1396d79b",payment.getId());
        assertEquals("SUCCESS",payment.getStatus());
        assertEquals(1,payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("ESHOP1234ABC5678",payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData,"MEOW");
        });
    }

    @Test
    void testCreatePaymentDefaultStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData);
        assertEquals("WAITING",payment.getMethod());
    }

    @Test
    void testCreatePaymentEmptyPaymentData(){
        this.paymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData,"SUCCESS");
        });
    }

    @Test
    void testEditPaymentInvalidStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData);
        assertThrows(IllegalArgumentException.class,() -> payment.setStatus("MEOW"));
    }

    @Test
    void testEditPaymentSuccessStatus(){
        Payment payment = new Payment("78952556-012a-4c07-b546-54eb1396d79b","VOUCHER",paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS",payment.getStatus());

    }
}
