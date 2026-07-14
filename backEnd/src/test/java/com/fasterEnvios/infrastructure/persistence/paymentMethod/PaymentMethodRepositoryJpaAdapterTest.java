package com.fasterEnvios.infrastructure.persistence.paymentMethod;

import com.fasterEnvios.domain.model.PaymentMethod;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({PaymentMethodRepositoryJpaAdapter.class})
@ActiveProfiles("test")
class PaymentMethodRepositoryJpaAdapterTest {
    @Autowired
    private PaymentMethodRepositoryJpaAdapter adapter;
    @Autowired
    private TestEntityManager testEntityManager;
    @Nested
    class FindPaymentMethodByNameTest {
        @Test
        void findPaymentMethodByName() {
            PaymentMethod paymentMethod = buildPaymentMethodForTest();
            PaymentMethod paymentMethodInDb = adapter.save(paymentMethod);

            Optional<PaymentMethod> result = adapter.findPaymentMethodByName(paymentMethodInDb.getMethodName());

            assertTrue(result.isPresent());
            assertEquals(paymentMethodInDb.getMethodName(), result.get().getMethodName());
        }
    }

    private PaymentMethod buildPaymentMethodForTest(){
        return PaymentMethod.builder()
                .withMethodName("EFECTIVO")
                .withStatus(true)
                .build();
    }
}