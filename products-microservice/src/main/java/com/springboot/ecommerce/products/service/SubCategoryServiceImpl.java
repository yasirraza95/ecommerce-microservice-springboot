package com.springboot.ecommerce.products.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.ecommerce.products.model.Subcategory;
import com.springboot.ecommerce.products.repository.SubCategoryRepository;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepo;

	@Override
	public List<Subcategory> getSubCategories() {
		List<Subcategory> result =  (List<Subcategory>) subCategoryRepo.findAll();
		if(!result.isEmpty()) return result;
		
		throw new EntityNotFoundException("No Sub Category found");
	}

	@Override
	public Subcategory addSubCategory(Subcategory subCategory) {
		return subCategoryRepo.save(subCategory); 
	}

	@Override
	public Subcategory updateSubCategory(String id, Subcategory subCategory) {
		Subcategory updateSubCategory = new Subcategory();
		updateSubCategory.setId(Long.parseLong(id));
		updateSubCategory.setName(subCategory.getName());
		
		Subcategory dbCategory = subCategoryRepo.findOneById(Long.parseLong(id)); 
		 if(dbCategory != null) {
			 subCategoryRepo.saveAndFlush(updateSubCategory);
			 
			 Subcategory newSubCategory = subCategoryRepo.findOneById(Long.parseLong(id));
			 return newSubCategory; 
		 } else {
			throw new EntityNotFoundException("Sub Category with id '"+ id +"' not found.");
		 }
	}

	@Override
	public void deleteById(String id) {
		subCategoryRepo.deleteById(Long.parseLong(id));
	}
}