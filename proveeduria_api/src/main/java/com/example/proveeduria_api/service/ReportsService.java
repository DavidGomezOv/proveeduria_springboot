/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.FinancialReportMonthlyItemReponseModel;
import com.example.proveeduria_api.models.FinancialReportResponseModel;
import com.example.proveeduria_api.repository.OrdersRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    private final OrdersRepository ordersRepository;

    public ReportsService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public FinancialReportResponseModel getMonthlyFinancialReport(Integer months) {

        List<Object[]> rawData = ordersRepository.findMonthlyTotalOrdersByPeriodNative(months);

        List<FinancialReportMonthlyItemReponseModel> monthlyItems = rawData.stream()
                .map(row -> {

                    Integer year = (Integer) row[0];
                    Integer month = (Integer) row[1];
                    BigDecimal totalAmount = (BigDecimal) row[2];

                    LocalDateTime startOfMonth = YearMonth.of(year, month)
                            .atDay(1)
                            .atStartOfDay();

                    DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

                    String monthDateString = startOfMonth.format(customFormatter);

                    FinancialReportMonthlyItemReponseModel item = new FinancialReportMonthlyItemReponseModel();
                    item.setMonthDate(monthDateString);
                    item.setFinancialValue(totalAmount);
                    return item;
                })
                .collect(Collectors.toList());

        BigDecimal periodFinancialValue = monthlyItems.stream()
                .map(FinancialReportMonthlyItemReponseModel::getFinancialValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        FinancialReportResponseModel response = new FinancialReportResponseModel();

        response.setItems(monthlyItems);
        response.setPeriodFinancialValue(periodFinancialValue);

        return response;

    }
}
