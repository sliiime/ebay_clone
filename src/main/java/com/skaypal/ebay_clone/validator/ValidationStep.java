package com.skaypal.ebay_clone.validator;

public abstract class ValidationStep<T> {

    ValidationStep<T> next;

    public ValidationStep(ValidationStep<T> next) {
        this.next = next;
    }

    public ValidationStep(){}

    public abstract ValidationResult validate(T toValidate);

    public ValidationStep<T> linkWith(ValidationStep<T> next) {

        if (this.next == null) {
            this.next = next;
        } else {
            ValidationStep<T> lastStep = this.next;
            while (lastStep.next != null) lastStep = lastStep.next;
            lastStep.next = next;
        }

        return this;
    }

    protected ValidationResult checkNext(T toValidate) {
        return this.next == null ? ValidationResult.valid() : next.validate(toValidate);
    }


}
