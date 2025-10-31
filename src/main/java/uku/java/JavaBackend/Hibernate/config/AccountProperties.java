package uku.java.JavaBackend.Hibernate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {

    @Value("${default-amount}")
    private String defaultAmount;

    @Value("${transfer-commission}")
    private String transferCommission;


    public String getDefaultAmount() {
        return defaultAmount;
    }

    public String getTransferCommission() {
        return transferCommission;
    }
}
