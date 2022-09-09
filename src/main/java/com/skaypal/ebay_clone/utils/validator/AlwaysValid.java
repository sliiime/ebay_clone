package com.skaypal.ebay_clone.utils.validator;

public class AlwaysValid<T> extends ValidationStep<T>{

    @Override
    public ValidationResult validate(T toValidate) {
        return this.next == null ? ValidationResult.valid() : checkNext(toValidate);
    }
}
