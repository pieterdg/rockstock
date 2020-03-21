package org.piedere.rockstock.service.home;

/**
 * Service Interface used on the home page.
 */
public interface HomeService {

    /**
     * Get the total number of countries in the collection.
     * 
     * @return the total number of countries.
     */
    long countCountries();

    long countMinerals();
    
    long countSpecimen();
}