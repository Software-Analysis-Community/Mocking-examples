package com.test.mocking;

public class ApiServiceDummyImpl implements ApiService{
    @Override
    public String getProductName(Long id) {
        return "Dummy";
    }

    @Override
    public Double getProductPrice(Long id) {
        return 99.9;
    }
}
