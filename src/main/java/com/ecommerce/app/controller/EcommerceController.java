package com.ecommerce.app.controller;

import java.util.List;
import java.util.logging.LogManager;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.dto.LoginDTO;
import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.entity.Catelog;
import com.ecommerce.app.entity.CatelogCount;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.ProductCount;
import com.ecommerce.app.service.EcommerceService;

@RestController
@RequestMapping("/ecommerceApp")
public class EcommerceController {
	{
		
	}
	
	@Autowired
	private EcommerceService ecommerceService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> register(@RequestBody UserDTO userdto)
	{
		String status=ecommerceService.registerUser(userdto);
		
		return new ResponseEntity<>(status,HttpStatus.OK);
		
	}
	
	@PutMapping("/loginUser")
	public ResponseEntity<List<Catelog>> login(@RequestBody LoginDTO logindto)
	{
		List<Catelog> listCAtelog=ecommerceService.login(logindto);
		
		return new ResponseEntity<>(listCAtelog,HttpStatus.OK);
	}
	
	@PostMapping("/catelogAvailable/{id}")
	public  ResponseEntity<List<Product>> showProduct(@PathVariable int id)
	{
		List<Product> listProduct=ecommerceService.showProduct(id);
		return new ResponseEntity<>(listProduct,HttpStatus.OK);
	}
	
	@PostMapping("/catelogAvailable/{categoryId}/productAvailable/{productId}")
	public ResponseEntity<Product> showProductDetails(@PathVariable int categoryId,@PathVariable int productId)
	{
		return	new ResponseEntity<>(ecommerceService.showProductDetai(categoryId, productId),HttpStatus.OK);
		
		
	}
	@GetMapping("/catelogAnalytics")
	public ResponseEntity<List<CatelogCount>> getCatalogAnalytics()
	{
		List<CatelogCount> catelogCount=ecommerceService.showCatelogAnaLytics();
		return new ResponseEntity<>(catelogCount,HttpStatus.OK);
	}
	
	@GetMapping("/productAnalytics")
	public ResponseEntity<List<ProductCount>> getProductAnalytics()
	{
		List<ProductCount> listProduct=ecommerceService.showProductAnaLytics();
		return new ResponseEntity<>(listProduct,HttpStatus.OK);
	}
	

} 
