package com.springboot.ecommerce.products.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.products.model.Category;
import com.springboot.ecommerce.products.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public List<Category> getCategories() {
		List<Category> result =  (List<Category>) categoryRepo.findAll();
		if(!result.isEmpty()) return result;
		
		throw new EntityNotFoundException("No Category found");
	}

	@Override
	public Category addCategory(Category category) {
		return categoryRepo.save(category); 
	}

	@Override
	public Category updateCategory(String id, Category category) {
		Category updateCategory = new Category();
		updateCategory.setId(Long.parseLong(id));
		updateCategory.setName(category.getName());
		
		Category dbCategory = categoryRepo.findOneById(Long.parseLong(id)); 
		 if(dbCategory != null) {
			 categoryRepo.saveAndFlush(updateCategory);
			 
			 Category newCategory = categoryRepo.findOneById(Long.parseLong(id));
			 return newCategory; 
		 } else {
			throw new EntityNotFoundException("Category with id '"+ id +"' not found.");
		 }
		
	}

	@Override
	public void deleteById(String id) {
		categoryRepo.deleteById(Long.parseLong(id));	
	}
}
