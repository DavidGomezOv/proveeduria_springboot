/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.OrderModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByUser_Id(Integer idUsuario);

    @Query(value = """
        SELECT 
            YEAR(fecha_creacion) AS year, 
            MONTH(fecha_creacion) AS month, 
            SUM(monto_total) AS total
        FROM dbo.OrdenCompra
        WHERE fecha_creacion >= DATEADD(month, -:months, GETDATE()) -- Uso de :months como parámetro
        GROUP BY 
            YEAR(fecha_creacion), 
            MONTH(fecha_creacion)
        ORDER BY 
            year DESC, 
            month DESC
    """, nativeQuery = true)
    List<Object[]> findMonthlyTotalOrdersByPeriodNative(@Param("months") Integer months);
}
