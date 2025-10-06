package uku.java.SpringCore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {

    @Value("${account.default-amount}")
    private String defaultAmount;

    @Value("${account.transfer-commission}")
    private String transferCommission;


    public String getDefaultAmount() {
        return defaultAmount;
    }

    public String getTransferCommission() {
        return transferCommission;
    }
}
