package ru.urfu.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    @Test
    void constructorAndGetters_ShouldWorkCorrectly() {
        Country country = new Country("Testland", "Test Subregion", "Test Region", 100L, 200L);

        assertEquals("Testland", country.getName());
        assertEquals("Test Subregion", country.getSubregion());
        assertEquals(100L, country.getInternetUsers());
        assertEquals(200L, country.getPopulation());
    }
}