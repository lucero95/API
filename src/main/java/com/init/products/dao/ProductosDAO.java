package com.init.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.products.entities.Producto;

public interface  ProductosDAO extends JpaRepository<Producto, Long>{

}
