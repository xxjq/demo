package com.example.demo.anno;

import com.example.demo.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//元注解，这个注解类会生成在帮助文档中
@Constraint(validatedBy = {StateValidation.class})
@Target({ElementType.FIELD})//元注解
@Retention(RetentionPolicy.RUNTIME)//元注解，这里是注解保留到运行时阶段
public @interface State {

    /**
     * message when check fail
     *
     * @return message
     */
    String message() default "state value is only 已发布 or 草稿";

    /**
     * group
     *
     * @return check groups
     */
    Class<?>[] groups() default {};

    /**
     * payload
     *
     * @return ?
     */
    Class<? extends Payload>[] payload() default {};

}
