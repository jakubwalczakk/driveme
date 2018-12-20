package pl.jakub.walczak.driveme.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

    @Value("${password.length}")
    private int passwordLength;

    public String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(passwordLength);
    }
}
