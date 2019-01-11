package pl.jakub.walczak.driveme.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Generator {

    private static final Random random = new Random();

    @Value("${password.length}")
    private int passwordLength;

    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private char[] licensePlateAppendix = {'K', 'G', 'O', 'B', 'Y', 'C', 'L', 'T', 'Z', 'R'};
    private char[] licensePlateSecondsPart = {
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '0'};


    public String generateLicensePlate() {
        StringBuilder licensePlate = new StringBuilder("S");
        licensePlate.append(licensePlateAppendix[random.nextInt(licensePlateAppendix.length)]);
        licensePlate.append(' ');
        int length = random.nextInt(2) + 4;
        for (int i = 0; i < length; i++) {
            licensePlate.append(licensePlateSecondsPart[random.nextInt(licensePlateSecondsPart.length)]);
        }
        return licensePlate.toString();
    }

    public String generatePhoneNumber() {
        int initial = random.nextInt(9) + 1;
        StringBuilder phoneNumber = new StringBuilder(initial);
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(numbers[random.nextInt(numbers.length)]);
        }
        return phoneNumber.toString();
    }

    public String generatePesel() {
        int initial = random.nextInt(9) + 1;
        StringBuilder pesel = new StringBuilder(initial);
        for (int i = 0; i < 10; i++) {
            pesel.append(numbers[random.nextInt(numbers.length)]);
        }
        return pesel.toString();
    }


    public String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(passwordLength);
    }

}
