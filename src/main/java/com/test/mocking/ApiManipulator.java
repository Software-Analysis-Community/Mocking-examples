package com.test.mocking;

public class ApiManipulator {
    private final ApiService apiService;

    public ApiManipulator(ApiService apiService){
        this.apiService = apiService;
    }

    public String getProductString(Long id){
        return apiService.getProductName(id) + ": "
                + apiService.getProductPrice(id) + "₽";
    }
}

