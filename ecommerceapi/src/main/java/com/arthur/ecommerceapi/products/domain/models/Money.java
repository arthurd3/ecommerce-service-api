package com.arthur.ecommerceapi.products.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Money {

    private BigDecimal value;
    private static final String MONETARY_UNIT = "R$";
    private static final DecimalFormat FORMAT = new DecimalFormat(MONETARY_UNIT + " #,###,##0.00");

    public Money(final String value){
        this.value = new BigDecimal(value);
    }

    @JsonCreator
    public Money(@JsonProperty("value") BigDecimal value){
        this.value = value;
    }


    public BigDecimal getValue() {
        return this.value;
    }

    public String getFormatedValue() {
        return FORMAT.format(this.value);
    }

    public void sumWith(String valueSum){
        this.value = this.value.add(new BigDecimal(valueSum));
    }

    public void subtract(String valueSub){
        this.value = this.value.subtract(new BigDecimal(valueSub));
    }
}
