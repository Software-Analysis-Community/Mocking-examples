package com.test.mocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class ApiTest {

    @Test
    void mocking(){
        // Создание мока ApiService
        ApiService apiService = Mockito.mock(ApiService.class);
        // Создание объекта apiManipulator, в конструктор передается мок
        ApiManipulator apiManipulator = new ApiManipulator(apiService);

        // Вызов метода ApiManipulator с внедренным моком
        String productString = apiManipulator.getProductString(1L);

        // Проверка, что методы мока с id=1 были вызваны
        Mockito.verify(apiService).getProductName(1L);
        Mockito.verify(apiService).getProductPrice(1L);

        // Проверка, что методы мока с id=2 не были вызваны
        Mockito.verify(apiService, Mockito.never()).getProductName(2L);
        Mockito.verify(apiService, Mockito.never()).getProductPrice(2L);

        // Вывод в консоль строки, полученной с использованием мока
        System.out.println(productString);
    }

    @Test
    void stubbing(){
        // Создание мока ApiService
        ApiService apiService = Mockito.mock(ApiService.class);
        // Создание объекта apiManipulator, в конструктор передается мок
        ApiManipulator apiManipulator = new ApiManipulator(apiService);

        // Установка возвращаемых значений для продукта с id=1
        Mockito.when(apiService.getProductName(1L)).thenReturn("Apple");
        Mockito.when(apiService.getProductPrice(1L)).thenReturn(9.99);

        // Вызов метода ApiManipulator с внедренным моком
        String productString = apiManipulator.getProductString(1L);

        // Проверка, что внедренный мок вернул верное значение
        Assertions.assertEquals("Apple: 9.99₽", apiManipulator.getProductString(1L));

        // Вызов методов все еще можно верифицировать
        Mockito.verify(apiService, Mockito.atLeastOnce()).getProductName(1L);
        Mockito.verify(apiService, Mockito.atLeastOnce()).getProductPrice(1L);

        // Вывод в консоль строки, полученной с использованием мока
        System.out.println(productString);
    }

    @Test
    void spying(){
        // Создание мока и шпиона
        ApiService apiServiceMock = Mockito.mock(ApiServiceDummyImpl.class);
        ApiService apiServiceSpy = Mockito.spy(ApiServiceDummyImpl.class);

        // Вывод метода getConnectionLink мока и шпиона
        System.out.println("getConnectionLink:");
        System.out.println(apiServiceSpy.getConnectionLink());
        System.out.println(apiServiceMock.getConnectionLink());

        // Создание apiManipulator с моком и шпионом
        ApiManipulator apiManipulatorSpy = new ApiManipulator(apiServiceSpy);
        ApiManipulator apiManipulatorMock = new ApiManipulator(apiServiceMock);

        // Получение строки для разных apiManipulator
        String resultSpy = apiManipulatorSpy.getProductString(1L);
        String resultMock = apiManipulatorMock.getProductString(1L);

        // Проверка вызова метода и результата для шпиона
        Mockito.verify(apiServiceSpy).getProductPrice(1L);
        Mockito.verify(apiServiceSpy).getProductName(1L);
        Mockito.verify(apiServiceSpy).getConnectionLink();
        Assertions.assertEquals("Dummy: 99.9₽", resultSpy);

        // Вывод результата
        System.out.println("result:");
        System.out.println(resultSpy);
        System.out.println(resultMock);
    }
}
