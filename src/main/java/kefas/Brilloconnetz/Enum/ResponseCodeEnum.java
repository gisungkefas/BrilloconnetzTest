package kefas.Brilloconnetz.Enum;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {

    SUCCESS(0, "Success"),
    ERROR(-1, "An error occurred. Error message : ${errorMessage}"),
    ERROR_EMAIL_INVALID(-3, "Invalid email address."),
    ERROR_PASSWORD_MISMATCH(-4,"Password does not match"),
    ERROR_DUPLICATE_USER(-5,"User already exist."),
    UNAUTHORISED_ACCESS(-7,"You are not authorised to perform this operation"),
    USER_NOT_FOUND(-8, "Email does not exist"),
    ;

    ResponseCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;
    private final String description;
}
