package com.i000.stock.user.core.util;


import com.i000.stock.user.core.constant.enums.ApplicationErrorMessage;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public final class ValidationUtils {

    private static final String REG_MOBILE = "^1[0-9]{10}$";
    private static final String REG_EMAIL = "^\\w+((-\\w+)|(\\.\\w+)|(:\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    private ValidationUtils() {
    }

    /**
     * id验证
     *
     * @param id      id
     * @param message 错误信息
     */
    public static void validateId(Number id, String message) {
        if (Objects.isNull(id) || id.longValue() <= 0) {
            throw new ValidateException(message);
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches("^1[0-9]{10}$", mobile);
    }

    /**
     * 验证邮箱格式
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(
                "^\\w+((-\\w+)|(\\.\\w+)|(:\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
                email);
    }

    /**
     * 请求参数验证
     *
     * @param parameter 请求参数
     * @param message   错误信息
     */
    public static void validateParameter(Object parameter, String message) {
        if (Objects.isNull(parameter)) {
            throw new ValidateException(message);
        }
    }

    /**
     * 数字类型参数验证,大于0
     *
     * @param value   值
     * @param message 错误信息
     */
    public static void validateGreaterThanZero(Long value, String message) {
        if (Objects.isNull(value) || value <= 0) {
            throw new ValidateException(message);
        }
    }

    /**
     * 持续时间验证
     *
     * @param time    日期时间戳
     * @param message 错误信息
     */
    public static void validateUntilTime(Long time, String message) {
        if (Objects.isNull(time) || time <= 0 || time < System.currentTimeMillis()) {
            throw new ValidateException(message);
        }
    }

    /**
     * 请求参数验证
     *
     * @param parameter 请求参数
     * @param message   错误信息
     */
    public static void validateStringParameter(String parameter, String message) {
        if (StringUtils.isBlank(parameter)) {
            throw new ValidateException(message);
        }
    }

    /**
     * list验证
     *
     * @param list    集合源
     * @param message 错误信息
     */
    public static void validateList(List list, String message) {
        if (CollectionUtils.isEmpty(list)) {
            throw new ValidateException(message);
        }
    }

    /**
     * 实体验证
     *
     * @param entity 实体
     * @param groups groups
     * @param <T>    泛型
     */
    public static <T> void validate(T entity, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(entity, groups);
        if (constraintViolations.isEmpty()) {
            return;
        }
        ConstraintViolation<T> first = constraintViolations.iterator().next();
        throw new ValidateException(first.getMessage());
    }

    /**
     * 验证范围参数
     *
     * @param value   待验证的值
     * @param min     最小值
     * @param max     最大值
     * @param message 错误信息
     */
    public static void validateRange(long value, long min, long max, String message) {
        if (value < min || value > max) {
            throw new ValidateException(message);
        }
    }

    /**
     * 验证手机格式
     *
     * @param mobile 手机号
     */
    public static void validateMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || !ValidationUtils.isMobile(mobile)) {
            throw new ValidateException("手机号格式不正确");
        }
    }

    /**
     * 验证手机格式
     *
     * @param mobile 手机号
     */
    public static void validateMobileWithAreaCode(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() != 15 || !ValidationUtils.isMobile(mobile)) {
            throw new ValidateException("手机号格式不正确");
        }
    }

    /**
     * 验证密码长度
     *
     * @param password 密码
     */
    public static void validatePassword(String password, String message) {
        if (StringUtils.isBlank(password) || password.trim().length() <= 8) {
            throw new ValidateException(message);
        }
    }

    public static final class ValidateException extends IllegalArgumentException {

        private static final long serialVersionUID = -8195207007269567980L;

        @Getter
        private Long code = ApplicationErrorMessage.INVALID_PARAMETER.getCode();

        ValidateException(String message) {
            super(message);
        }

        ValidateException(Throwable cause) {
            super(cause);
        }

        ValidateException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}