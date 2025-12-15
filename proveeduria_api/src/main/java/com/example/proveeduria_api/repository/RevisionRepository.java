/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.RevisionModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<RevisionModel, Long> {

    RevisionModel findByOrder_Id(Integer idOrder);
    
    List<RevisionModel> findByUser_Id(Integer idUser);
}
