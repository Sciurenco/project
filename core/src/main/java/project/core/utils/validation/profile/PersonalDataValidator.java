package project.core.utils.validation.profile;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.core.dto.profile.PersonalDataDTO;
import project.core.dto.profile.UserDTO;

import java.util.Optional;

@Component
public class PersonalDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        if (clazz.getName().contains("UserDTO")) return UserDTO.class.equals(clazz);
        return PersonalDataDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonalDataDTO personalData;

        if (target.getClass().equals(UserDTO.class)) {
            UserDTO user = (UserDTO) target;
            personalData = user.getPersonalData();
        } else {
            personalData = (PersonalDataDTO) target;
        }

        if (!checkPhoneNumber(personalData)) {
            errors.rejectValue("personalData.phoneNumber", "phoneNumber.invalid", "Phone number is invalid," +
                    "please enter international format + phone number, for example  << +373-68-***-*** >>");
        }

    }

    private static boolean checkPhoneNumber(PersonalDataDTO personalData) {
        String phoneNumber = Optional.ofNullable(personalData)
                .map(PersonalDataDTO::getPhoneNumber)
                .orElse(null);

        if (phoneNumber == null) return true;
        return isPhoneNumberValid(phoneNumber);

    }

    public static boolean isPhoneNumberValid(String phone) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;

        try {
            phoneNumber = phoneNumberUtil.parse(phone, "IN");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }

        return phoneNumberUtil.isValidNumber(phoneNumber);
    }
}
