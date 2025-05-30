package recordatorio.recordatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import recordatorio.recordatorio.service.emailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class emailController {
    @Autowired
    private emailService emailService;
    
    // @GetMapping("/basicEmail")
    // public String sendBasicEmail() {
    //     emailService.basicEmail();
    //     return "mail sent successfully";
    // }
    @GetMapping("/advancedEmail/{email}")
    public String sendAdvance(@PathVariable String email) {
        emailService.AdvancedMail(email);
        return "mail sent successfully";
    }
    @GetMapping("/newAccountEmail/{email}")
    public String sendNewAccount(@PathVariable String email) {
        emailService.newAccountEmail(email);
        return "mail sent successfully";
    }
    @GetMapping("/passwordResetEmail/{email}")
    public String sendPasswordReset(@PathVariable String email) {
        emailService.passwordResetEmail(email);
        return "mail sent successfully";
    }
    @GetMapping("/verificationCodeEmail/{email}")
    public String sendVerificationCode(@PathVariable String email) {
        emailService.verificationCodeEmail(email);
        return "mail sent successfully";
    }
    @GetMapping("/lowStockNotification/{email}")
    public String sendLowStockNotification(@PathVariable String email) {
        emailService.lowStockNotification(email);
        return "mail sent successfully";
    }
    @GetMapping("/abandonedCartEmail/{email}")
    public String sendAbandonedCart(@PathVariable String email) {
        emailService.abandonedCartEmail(email);
        return "mail sent successfully";
    }
    @GetMapping("/passwordChangeEmail/{email}")
    public String sendPasswordChange(@PathVariable String email) {
        emailService.passwordChangeEmail(email);
        return "mail sent successfully";
    }
    @GetMapping("/purchaseNotification/{email}")
    public String sendPurchaseNotification(@PathVariable String email) {
        emailService.purchaseNotification(email);
        return "mail sent successfully";
    }
}

