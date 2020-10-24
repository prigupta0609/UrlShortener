package com.padlet.validator;

import com.padlet.common.Error;
import org.springframework.stereotype.Component;

@Component("UrlValidator")
public class UrlValidator implements IValidator {

    @Override
    public void validate(Object object) throws ValidationException {
        if (object instanceof String) {
            String url = (String) object;
            org.apache.commons.validator.routines.UrlValidator defaultValidator =
                    new org.apache.commons.validator.routines.UrlValidator();
            if (!defaultValidator.isValid(url)) {
                throw new ValidationException(Error.INVALID_URL_FORMAT.getErrorCode(),
                        Error.INVALID_URL_FORMAT.getMessage());
            }
        } else {
            throw new ValidationException(Error.INVALID_URL_FORMAT.getErrorCode(),
                    Error.INVALID_URL_FORMAT.getMessage());
        }
    }
}
