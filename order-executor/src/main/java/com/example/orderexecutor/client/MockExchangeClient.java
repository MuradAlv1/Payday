package com.example.orderexecutor.client;

import org.springframework.stereotype.Component;

@Component
public class MockExchangeClient implements ExchangeClient {

    @Override
    public void buy() {}
}
