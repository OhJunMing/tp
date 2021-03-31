package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a car in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCar(String, String)}
 */
public class Car {

    public static final String MESSAGE_CONSTRAINTS = "Car Brand + Car Type can take any values, it should not be blank";

    /*
     * The first character of the preferred car must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String carBrand;
    public final String carType;

    /**
     * Constructs a {@code Car}.
     *
     * @param carBrand A valid carBrand.
     * @param carType A valid carType.
     */
    public Car(String carBrand, String carType) {
        requireNonNull(carBrand);
        requireNonNull(carType);
        checkArgument(isValidCar(carBrand, carType), MESSAGE_CONSTRAINTS);

        this.carBrand = carBrand;
        this.carType = carType;
    }

    /**
     * Returns true if a given string is a valid car.
     */
    public static boolean isValidCar(String carBrand, String carType) {
        return isValidCarBrand(carBrand) && isValidCarType(carType);
    }

    /**
     * Returns true if a given string is a valid carBrand.
     */
    public static boolean isValidCarBrand(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid carType.
     */
    public static boolean isValidCarType(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Compares carBrand and carType strings.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarBrandAndCarType(Car otherCar) {
        return isSameCarBrand(otherCar) && isSameCarType(otherCar);
    }
    /**
     * Compares carBrand string.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarBrand(Car otherCar) {
        return this.carBrand.equals(otherCar.carBrand);
    }

    /**
     * Compares carType string.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarType(Car otherCar) {
        return this.carType.equals(otherCar.carType);
    }
    @Override
    public String toString() {
        return carBrand + "," + carType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Car // instanceof handles nulls
                && carBrand.equals(((Car) other).carBrand)
                && carType.equals(((Car) other).carType)); // state check
    }

    /**
     * Helps with comparing car Objects.
     * carBrand and carType attributes are essential to determine same attributes.
     */
    @Override
    public int hashCode() {
        return Objects.hash(carBrand, carType);
    }

}
