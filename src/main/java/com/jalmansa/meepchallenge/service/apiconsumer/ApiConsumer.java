package com.jalmansa.meepchallenge.service.apiconsumer;

import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.exception.UnexpectedApiResponseException;

public interface ApiConsumer {
    public Vehicles execute() throws UnexpectedApiResponseException;
}
