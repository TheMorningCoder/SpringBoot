package com.example.weather_forecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
        @JsonProperty("Metric")
        private Metric metric;

        @JsonProperty("Imperial")
        private Imperial imperial;

        // Getters and Setters
        public Metric getMetric() {
            return metric;
        }

        public void setMetric(Metric metric) {
            this.metric = metric;
        }

        public Imperial getImperial() {
            return imperial;
        }

        public void setImperial(Imperial imperial) {
            this.imperial = imperial;
        }

        public static class Metric {
            @JsonProperty("Value")
            private double value;

            @JsonProperty("Unit")
            private String unit;

            @JsonProperty("UnitType")
            private int unitType;

            // Getters and Setters
            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getUnitType() {
                return unitType;
            }

            public void setUnitType(int unitType) {
                this.unitType = unitType;
            }
        }

        public static class Imperial {
            @JsonProperty("Value")
            private double value;

            @JsonProperty("Unit")
            private String unit;

            @JsonProperty("UnitType")
            private int unitType;

            // Getters and Setters
            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getUnitType() {
                return unitType;
            }

            public void setUnitType(int unitType) {
                this.unitType = unitType;
            }
        }
    }//