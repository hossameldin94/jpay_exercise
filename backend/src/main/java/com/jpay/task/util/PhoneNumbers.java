package com.jpay.task.util;

public enum PhoneNumbers {
    CAMERON("+237","^\\(237\\) .*","\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("+251","^\\(251\\) .*","\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOROCCO("+212","^\\(212\\) .*","\\(212\\)\\ ?[5-9]\\d{8}$"),
    MOZAMBIQUE("+258","^\\(258\\) .*","\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("+256","^\\(256\\) .*","\\(256\\)\\ ?\\d{9}$");

    String countryCode;
    String countryRegex;
    String validRegex;
    PhoneNumbers(String countryCode,String countryRegex , String validRegex)
            {
                this.countryCode = countryCode;
                this.countryRegex = countryRegex;
                this.validRegex = validRegex;
            }

    public String getCountryRegex() {
        return countryRegex;
    }

    public void setCountryRegex(String countryRegex) {
        this.countryRegex = countryRegex;
    }

    public String getValidRegex() {
        return validRegex;
    }

    public void setValidRegex(String validRegex) {
        this.validRegex = validRegex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
