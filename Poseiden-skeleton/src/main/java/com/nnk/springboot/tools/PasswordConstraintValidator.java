package com.nnk.springboot.tools;
import org.passay.CharacterRule;

import org.passay.*;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Component
public class PasswordConstraintValidator implements ConstraintValidator <ValidPassword,String> {
    @Override
    public void initialize(final ValidPassword arg0){
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

        // length between 8 and 60 characters, 60 because the length of the hashed password
        new LengthRule(8, 60),

        // at least one upper-case character
        new CharacterRule(EnglishCharacterData.UpperCase, 1),

        // at least one digit character
        new CharacterRule(EnglishCharacterData.Digit, 1),

        // at least one symbol (special character)
        new CharacterRule(EnglishCharacterData.Special, 1)
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }

        context.buildConstraintViolationWithTemplate("Le mot de passe devrait contenir au moins 8 caractères, 1 majuscule, 1 chiffre et 1 caractère spécial")
                .addConstraintViolation().disableDefaultConstraintViolation();


        return false;
    }
}
