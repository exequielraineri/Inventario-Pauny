/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.repository;

import com.app.inventario.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface int_Usuario_repo extends JpaRepository<Usuario, Integer>{
    
    @Transactional(readOnly = true)
    Optional<Usuario> findByUser(String user);
}
