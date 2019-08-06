package com.ecommerce.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.LoginDTO;
import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.entity.Catelog;
import com.ecommerce.app.entity.CatelogCount;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.ProductCount;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.repository.CategoryCountRepository;
import com.ecommerce.app.repository.CatelogRepository;
import com.ecommerce.app.repository.ProductCountRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.UserRepository;

@Service
public class EcommerceServiceImpl implements EcommerceService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CatelogRepository catelogRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryCountRepository catelogCountRepository;
	
	@Autowired
	private ProductCountRepository productCountRepository;
	
	@Override
	public String registerUser(UserDTO userdto) {
       User user = new User();
		if(userdto.getPassword().equals(userdto.getConfirmPassword()))
		{
			BeanUtils.copyProperties(userdto, user);
			userRepository.save(user);
		}
		else
		{
			//throw new PasswordNotMatch();
			return "registration failed";
		}
		return "sucess";
	}

	@Override
	public List<Catelog> login(LoginDTO logindto) {

		List<Catelog> listCateLog=null;
		User user=userRepository.findByName(logindto.getName());
		
		if(user!=null)
		{
			if(logindto.getPassword().equals(user.getPassword()))
			{
				listCateLog=catelogRepository.findAll();
			}
		}
		
		
		
		
	
		return listCateLog;
	}

	@Override
	public List<Product> showProduct(int id) {
		//category id as id
		Catelog store=new Catelog();
		Optional<Catelog> category=catelogRepository.findById(id);
		CatelogCount catelogcountStore=new CatelogCount();
		System.out.println(category.isPresent());
		if(category.isPresent())
		{
			System.out.println(category.get().getCategoryName());
			store=category.get();
			System.out.println(store);
			System.out.println(store.getProduct());
			
			Optional<CatelogCount> catelogCount=catelogCountRepository.findByCatelog(store);
			if(catelogCount.isPresent())
			{

				catelogcountStore=catelogCount.get();
				catelogcountStore.setCatelog(store);
				catelogcountStore.setCount(catelogcountStore.getCount()+1);
				catelogcountStore.setId(catelogcountStore.getId());
				catelogCountRepository.save(catelogcountStore);
				
			}
			else
			{
				catelogcountStore.setCatelog(store);
				catelogcountStore.setCount(1);
				catelogCountRepository.save(catelogcountStore);
			}
			
		}
		else
		{
			
		}
		System.out.println(store.getProduct());
		return store.getProduct();
	}

	@Override
	public Product showProductDetai(int categoryId, int productId) {

		ProductCount productCount=new ProductCount();
		Product product=new Product();
		
		
		Catelog Catelog=new Catelog();
		
		Optional<Catelog> catelogRepo=catelogRepository.findById(categoryId);
		if(catelogRepo.isPresent())
		{
			Catelog=catelogRepo.get();
			Optional<Product> productById=productRepository.findById(productId);
		     if(productById.isPresent())
		       {
					product=productById.get();
						
							if(Catelog.getProduct().contains(product))
							{
								Optional<ProductCount> proCount=productCountRepository.findByProduct(product);
								if(proCount.isPresent())
								{
									System.out.println("present---update");
									productCount=proCount.get();
									productCount.setCount(productCount.getCount()+1);
									productCount.setProduct(product);
									productCount.setId(productCount.getId());
									productCountRepository.save(productCount);
									
								}
								else
								{
									System.out.println("not present ---insert");
									productCount.setCount(1);
									productCount.setProduct(product);
									productCountRepository.save(productCount);
									
								}
							
					
					
				}
				
			
		}
		}
		
		return product;
	}

	@Override
	public List<CatelogCount> showCatelogAnaLytics() {
		List<CatelogCount> catelogCount=catelogCountRepository.findAll();
		
		return catelogCount;
	}
	@Override
	public List<ProductCount> showProductAnaLytics() {
		 List<ProductCount> listProduct=productCountRepository.findAll();
		return listProduct;
	}




}
