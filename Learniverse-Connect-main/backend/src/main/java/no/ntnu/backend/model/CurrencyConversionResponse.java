package no.ntnu.backend.model;

import java.util.Map;

/**
 * Represents a response for currency conversion containing a map of currency
 * rates.
 * The keys are currency codes, and the values are the conversion rates.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
public class CurrencyConversionResponse {
  private Map<String, Double> data;

  /**
   * Gets the map of currency rates.
   * 
   * @return a map where the keys are currency codes and the values are conversion
   *         rates
   */
  public Map<String, Double> getData() {
    return data;
  }

  /**
   * Sets the map of currency rates.
   * 
   * @param rates a map where the keys are currency codes and the values are
   *              conversion rates
   */
  public void setData(Map<String, Double> rates) {
    this.data = rates;
  }
}
