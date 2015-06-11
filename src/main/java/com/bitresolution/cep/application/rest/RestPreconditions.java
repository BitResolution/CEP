package com.bitresolution.cep.application.rest;

import com.bitresolution.cep.application.rest.ResourceAlreadyExistsException;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;

import java.util.Collection;

public class RestPreconditions {

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *            an object reference.
     * @param message
     *            the message to be displayed when the exception is thrown.
     * @return the non-null reference that was validated
     * @throws com.bitresolution.cep.application.rest.ResourceNotFoundException
     *             if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, final String message) {
        if (reference == null) {
            throw new ResourceNotFoundException(message);
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is null.
     *
     * @param reference
     *            an object reference.
     * @param message
     *            the message to be displayed when the exception is thrown.
     * @return the null reference that was validated
     * @throws com.bitresolution.cep.application.rest.ResourceAlreadyExistsException
     *             if {@code reference} is null
     */
    public static <T> T checkIsNull(final T reference, final String message) {
        if (reference != null) {
            throw new ResourceAlreadyExistsException(message);
        }
        return reference;
    }

    /**
     * Ensures that an string passed as a parameter to the calling method is not null or empty.
     *
     * @param reference
     *            an object reference.
     * @param message
     *            the message to be displayed when the exception is thrown.
     * @return the string that was validated
     * @throws ResourceNotFoundException
     *             if {@code reference} is null
     */
    public static String checkStringNotNullOrEmpty(final String str, final String message) {

        if (str != null && !str.isEmpty()) {
            return str;
        }
        throw new ResourceNotFoundException(message);
    }

    /**
     * Ensures that a collection passed as a parameter to the calling method is not null or empty.
     *
     * @param collection
     *            an object collection.
     * @param message
     *            the message to be displayed when the exception is thrown.
     * @return the string that was validated
     * @throws ResourceNotFoundException
     *             if {@code collection} is null or empty
     */
    public static <T> Collection<T> checkNotEmpty(final Collection<T> collection, final String message) {
        if (collection != null && !collection.isEmpty()) {
            return collection;
        }
        throw new ResourceNotFoundException(message);
    }

    /**
     * Ensures that a condition passed as a parameter to the calling method is not false.
     *
     * @param boolean an object Boolean.
     * @param message
     *            the message to be displayed when the exception is thrown.
     * @return the condition that was validated
     * @throws ResourceNotFoundException
     *             if {@code boolean} is false
     */
    public static boolean checkNotFalse(final boolean condition, final String message) {
        if (condition)
            return condition;
        throw new ResourceNotFoundException(message);
    }
}
