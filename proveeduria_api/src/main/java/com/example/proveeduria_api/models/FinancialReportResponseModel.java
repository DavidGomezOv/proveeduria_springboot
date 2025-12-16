/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

import java.math.BigDecimal;
import java.util.List;

public class FinancialReportResponseModel {

    private List<FinancialReportMonthlyItemReponseModel> months;
    private BigDecimal periodFinancialValue;

    public List<FinancialReportMonthlyItemReponseModel> getItems() {
        return months;
    }

    public void setItems(List<FinancialReportMonthlyItemReponseModel> items) {
        this.months = items;
    }

    public BigDecimal getPeriodFinancialValue() {
        return periodFinancialValue;
    }

    public void setPeriodFinancialValue(BigDecimal periodFinancialValue) {
        this.periodFinancialValue = periodFinancialValue;
    }

}
