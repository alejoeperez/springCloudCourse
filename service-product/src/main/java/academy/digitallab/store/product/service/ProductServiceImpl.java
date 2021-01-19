package academy.digitallab.store.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	
	private final ProductRepository productRepository;
	
	@Override
	public List<Product> listAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		 
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("Created");
		product.setCreateAt(new Date());
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		Product productDB = getProduct(product.getId());
		
		if(null == productDB) {
			
			return null;
		}
		
		productDB.setName(product.getName());
		productDB.setDescription(product.getDescription());
		productDB.setPrice(product.getPrice());
		productDB.setCategory(product.getCategory());
		
		return productRepository.save(productDB);
		
	}

	@Override
	public Product deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(id);
		
		if(null == productDB) {
			
			return null;
		}
		
		productDB.setStatus("Deleted");
		
		return productRepository.save(productDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		
		return productRepository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		Product productDB = getProduct(id);
		
		if(null == productDB) {
			
			return null;
		}
		Double doubleTotal = productDB.getStock() + quantity;
		productDB.setStock(doubleTotal);
		return productRepository.save(productDB);
	}

	
}