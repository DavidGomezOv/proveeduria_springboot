/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

import java.math.BigDecimal;

public class FinancialReportMonthlyItemReponseModel {

    private String monthDate;
    private BigDecimal financialValue;

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public BigDecimal getFinancialValue() {
        return financialValue;
    }

    public void setFinancialValue(BigDecimal financialValue) {
        this.financialValue = financialValue;
    }

}
