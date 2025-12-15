package ru.urfu.data.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiServiceTest {

    @Test
    void getCapital_WithValidCountry_ShouldReturnCapital() {
        String capital = ApiService.getCapital("Germany");
        assertEquals("Berlin", capital);
    }

    @Test
    void getCapital_WithInvalidCountry_ShouldReturnErrorString() {
        String response = ApiService.getCapital("NonExistentCountry123");
        assertTrue(response.contains("Не найдено"));
    }
}