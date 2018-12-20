package pl.jakub.walczak.driveme.utils;

import lombok.extern.slf4j.Slf4j;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.user.RegistrationDTO;
import pl.jakub.walczak.driveme.enums.UserRole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Validator {

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^\\p{L}+$", Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    private static final Pattern VALID_TOWN_REGEX =
            Pattern.compile("^\\p{L}+(?:[\\s-`',]\\p{L}*)*$", Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern VALID_POST_CODE_REGEX =
            Pattern.compile("^\\w+([\\s-]?\\w+)*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_STREET_REGEX =
            Pattern.compile("^\\w+(?:[\\s-`',&.]\\w*)*$", Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern VALID_HOUSE_NUMBER_REGEX =
            Pattern.compile("^\\w+$");
    private static final Pattern PHONE_NUMBER_REGEX =
            Pattern.compile("^\\d{3}-\\d{3}-\\d{3}$||^\\d{3} \\d{3} \\d{3}$||^\\d{9}$");

    public static boolean userRegistrationValidation(RegistrationDTO registrationDTO) {
        if (nameValidation(registrationDTO.getName()) &&
                nameValidation(registrationDTO.getSurname()) &&
                emailValidation(registrationDTO.getEmail()) &&
                phoneNumberValidation(registrationDTO.getPhoneNumber())) {
            try {
                UserRole userRole = UserRole.of(registrationDTO.getUserRole());
                if(userRole.getValue().equals(UserRole.STUDENT.getValue())){
                    log.info("TUTAJ STUDENT, TRZEBA DODAĆ SPRAWDZENIE ADRESU...");
                }
                return true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean addressValidation(AddressDTO addressDTO) {
        if (townValidation(addressDTO.getCity()) &&
                postCodeValidation(addressDTO.getZipCode()) &&
                streetValidation(addressDTO.getStreet()) &&
                houseNumberValidation(addressDTO.getHouseNo())) {
            return true;
        }
        return false;
    }

    public static boolean nameValidation(String name) {
        Matcher matcher = VALID_NAME_REGEX.matcher(name);
        return matcher.find();
    }

    public static boolean emailValidation(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean passwordValidation(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    private static boolean phoneNumberValidation(String phoneNumber) {
        Matcher matcher = PHONE_NUMBER_REGEX.matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean townValidation(String town) {
        Matcher matcher = VALID_TOWN_REGEX.matcher(town);
        return matcher.find();
    }

    public static boolean postCodeValidation(String postCode) {
        Matcher matcher = VALID_POST_CODE_REGEX.matcher(postCode);
        return matcher.find();
    }

    public static boolean streetValidation(String street) {
        Matcher matcher = VALID_STREET_REGEX.matcher(street);
        return matcher.find();
    }

    public static boolean houseNumberValidation(String houseNumber) {
        Matcher matcher = VALID_HOUSE_NUMBER_REGEX.matcher(houseNumber);
        return matcher.find();
    }
}
