package pl.jakub.walczak.driveme.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.payment.PaymentService;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
}
