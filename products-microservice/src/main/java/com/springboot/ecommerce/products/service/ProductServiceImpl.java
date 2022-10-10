package com.springboot.ecommerce.products.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.ecommerce.products.model.Product;
import com.springboot.ecommerce.products.model.ProductEntity;
import com.springboot.ecommerce.products.model.UploadProduct;
import com.springboot.ecommerce.products.repository.ProductRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static String UPLOADED_FOLDER = "F://product_image//";

	@Autowired
	private ProductRepository prodRepo;

	Pageable pageable;

	@Override
	public Map<String, Object> getAllProducts(int page, int size, String subCatName, String name, String price, String sort) {
		try {
			//TODO multiple cols sorting
			if(sort.split("\\.")[1] == "desc") {
				pageable = PageRequest.of(page, size, Sort.by(sort.split("\\.")[0]).descending());
			} else {
				pageable = PageRequest.of(page, size, Sort.by(sort.split("\\.")[0]));
			}
			
//			Page<ProductEntity> result;
			Page<Product> result;
			
			//FIXME multi col search
			if(name != null) {
				result =  prodRepo.findByNameContaining(name, pageable);
			} else if(price != null) {
				result =  prodRepo.findByPriceContaining(price, pageable);
			} else if(subCatName != null) {
				result =  prodRepo.findBySubCatNameContaining(subCatName, pageable);
			} else {
				result =  prodRepo.findAllProductBy(pageable);
			}
						
//			result =  prodRepo.findAllProductBy(pageable);
			
			Map<String, Object> response = new LinkedHashMap<>();
			response.put("records", result.getContent());
			response.put("currentPage", result.getNumber());
			response.put("totalRecords", result.getTotalElements());
			response.put("totalPages", result.getTotalPages());

			if(!result.isEmpty()) return response;

			throw new EntityNotFoundException("No Product found");
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Product getProductById(String id) {
		try {
			Product response = prodRepo.findOneById(Long.parseLong(id));
			if(response != null )  return response;
			
			throw new EntityNotFoundException("No Product found");
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public Product save(UploadProduct product, MultipartFile file) throws Exception {
		try {
			
            String imageName = UPLOADED_FOLDER + file.getOriginalFilename();            
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imageName);
            Files.write(path, bytes);

            ProductEntity productEntity = new ProductEntity();
            productEntity.setImage(imageName);
            productEntity.setName(product.getName());
            productEntity.setPrice(product.getPrice());
            productEntity.setSubCatId(product.getSubCatId());
            
			ProductEntity result = prodRepo.save(productEntity);
			if(result.getId() > 0) {
				Product response = prodRepo.findOneById(result.getId());
				if(response != null )  return response;
			} 
			
			throw new EntityNotFoundException("Error inserting product information");
		} catch(Exception e) {
			throw e;
		}

	}
	

	@Override
	public Product update(String id, ProductEntity product) {
		ProductEntity updateProduct = new ProductEntity();
		updateProduct.setId(Long.parseLong(id));
		updateProduct.setSubCatId(product.getSubCatId());
		updateProduct.setName(product.getName());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setImage(product.getImage());

		Product dbProduct = prodRepo.findOneById(Long.parseLong(id)); 
		if(dbProduct != null) {
			prodRepo.saveAndFlush(updateProduct);

			Product newProduct = prodRepo.findOneById(Long.parseLong(id));
			return newProduct; 
		} else {
			throw new EntityNotFoundException("Product with id '"+ id +"' not found.");
		}

	}


	@Override
	public void deleteById(String id) {
		prodRepo.deleteById(Long.parseLong(id));
	}
		
}
