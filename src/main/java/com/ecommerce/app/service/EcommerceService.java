package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.dto.LoginDTO;
import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.entity.Catelog;
import com.ecommerce.app.entity.CatelogCount;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.ProductCount;

public interface EcommerceService {

	public String registerUser(UserDTO userdto);
	
	public List<Catelog> login(LoginDTO logindto);
	
	public List<Product> showProduct(int id);
	
	public Product showProductDetai(int categoryId,int productId);
	
	public List<CatelogCount> showCatelogAnaLytics();
	
	public List<ProductCount> showProductAnaLytics();
	
	
	
	
}