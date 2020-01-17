package com.init.products.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductosDAO;
import com.init.products.entities.Producto;

@RestController
@RequestMapping("api/sps/helloword/v1")
public class ProductsREST {
	
	@Autowired
	private ProductosDAO productoDAO;
	
	@GetMapping("/listado")
	public ResponseEntity<List<Producto>> getProducto(){
		List<Producto> productos = productoDAO.findAll();
		return ResponseEntity.ok(productos);
	}
	
	@GetMapping("/producto/{productoId}")
	//@RequestMapping( value = "{productoId}")
	public ResponseEntity<Producto> getProductoId(@PathVariable("productoId") Long productoId){
		Optional<Producto> optionalProducto = productoDAO.findById(productoId);
		if(optionalProducto.isPresent()) {
			return ResponseEntity.ok(optionalProducto.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Producto> addProducto(@RequestBody Producto producto){
		Producto newProducto = productoDAO.save(producto);
		return ResponseEntity.ok(newProducto);
	}
	
	@DeleteMapping("/eliminar/{productoId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable("productoId") Long productoId){
		productoDAO.deleteById(productoId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/modificar")
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
		Optional<Producto> optionalProducto = productoDAO.findById(producto.getId());
		if(optionalProducto.isPresent()) {
			Producto updateProducto = optionalProducto.get();
			updateProducto.setNombre(producto.getNombre());
			updateProducto.setTipo(producto.getTipo());
			updateProducto.setPrecio(producto.getPrecio());
			productoDAO.save(producto);
			return ResponseEntity.ok(updateProducto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
