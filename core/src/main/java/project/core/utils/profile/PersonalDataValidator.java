package project.core.utils.profile;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.core.dto.identification.UserDTO;

@Component
public class PersonalDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        if (!isPhoneNumberValid(user.getPersonalData().getPhoneNumber())) {
            errors.rejectValue("personalData.phoneNumber", "phoneNumber.invalid", "Phone number is invalid," +
                    "please enter international format + phone number, for example  << +373-68-***-*** >>");
        }
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
