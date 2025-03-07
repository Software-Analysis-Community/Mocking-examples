package com.test.mocking;

public interface ApiService {
    String getProductName(Long id);
    Double getProductPrice(Long id);

    default String getConnectionLink(){
        return "Connection not established";
    }
}
