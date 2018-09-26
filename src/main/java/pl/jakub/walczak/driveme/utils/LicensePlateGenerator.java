package pl.jakub.walczak.driveme.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LicensePlateGenerator {

    private char[] appendix = {'K', 'G', 'O', 'B', 'Y', 'C', 'L', 'T', 'Z', 'R'};
    private char[] secondPart = {
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '0'};

    private static final Random random = new Random();

    public String generateLicensePlate() {
        StringBuilder licensePlate = new StringBuilder("S");
        licensePlate.append(appendix[random.nextInt(appendix.length)]);
        licensePlate.append(' ');
        for (int i = 0; i < 5; i++) {
            licensePlate.append(secondPart[random.nextInt(secondPart.length)]);
        }
        return licensePlate.toString();
    }
}
