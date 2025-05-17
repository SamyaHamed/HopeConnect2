package com.example.Software_Advance.externalApi;

import com.example.Software_Advance.models.Tables.Donation;
import com.example.Software_Advance.exceptions.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Setter
@Service
public class PaymentService {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @Value("${stripe.currency}")
    private String currency;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public PaymentIntent createPaymentIntent(Donation donation) {
        try {
            long amountInCents = (long)(donation.getDonationAmount() * 100);

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency(currency)
                    .addPaymentMethodType("card")
                    .build();

            return PaymentIntent.create(params);
        } catch (StripeException e) {
            throw new PaymentException("Failed to create payment intent", e);
        }
    }

    public String getWebhookSecret() {
        return endpointSecret;
    }
}
