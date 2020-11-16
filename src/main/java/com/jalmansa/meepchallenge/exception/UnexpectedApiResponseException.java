package com.jalmansa.meepchallenge.exception;

@SuppressWarnings("serial")
public class UnexpectedApiResponseException extends Exception {

    public UnexpectedApiResponseException(String msg) {
        super(msg);
    }
}
