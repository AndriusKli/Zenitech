package com.example.andriuskli.enums;

public enum PropertyType {
    APARTMENT(0.013),
    HOUSE(0.015),
    INDUSTRIAL(0.02);

    private final double taxRate;

    PropertyType(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
