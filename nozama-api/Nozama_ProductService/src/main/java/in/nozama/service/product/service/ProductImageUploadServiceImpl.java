package in.nozama.service.product.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.nozama.service.product.config.ProductImageUploadProperties;
import in.nozama.service.product.exception.ProductImageUploadException;

@Service
public class ProductImageUploadServiceImpl implements ProductImageUploadService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductImageUploadServiceImpl.class);

	private final Path dirLocation;

	@Autowired
	public ProductImageUploadServiceImpl(ProductImageUploadProperties productImageUploadProperties) {
		LOG.error(productImageUploadProperties.getLocation());
		if (productImageUploadProperties.getLocation() != null
				&& productImageUploadProperties.getLocation().trim().length() <= 0) {
			this.dirLocation = Paths.get("E:\\temp\\nozama\\product").toAbsolutePath().normalize();
		} else {
			this.dirLocation = Paths.get(productImageUploadProperties.getLocation()).toAbsolutePath().normalize();
		}
	}

	@Override
	@PostConstruct
	public void init() throws ProductImageUploadException {
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			throw new ProductImageUploadException("Could not create Upload directory!");
		}
	}

	@Override
	public String saveProductImage(MultipartFile file, Long productId) throws ProductImageUploadException {
		try {
			StringBuilder fileName = new StringBuilder(file.getOriginalFilename());
			if (productId >= 1L) {
				fileName = fileName.append("__").append(productId);
			}
			final String file_name = fileName.toString();
			Path dirPath = this.dirLocation.resolve(file_name);
			Files.copy(file.getInputStream(), dirPath, StandardCopyOption.REPLACE_EXISTING);
			return file_name;
		} catch (Exception e) {
			throw new ProductImageUploadException("Could not save product image!");
		}
	}

	@Override
	public Resource loadProductImage(String filename) throws ProductImageUploadException {
		try {
			Path file = this.dirLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ProductImageUploadException("Cannot find or open the product image!");
			}
		} catch (MalformedURLException mue) {
			throw new ProductImageUploadException("Cannot find or open the product image!");
		}
	}

	@Override
	public void deleteProductImage(String filename) throws ProductImageUploadException {
		try {
			Path file = this.dirLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				Files.delete(file);
			} 
		} catch (Exception mue) {
			if(!(mue instanceof NoSuchFileException)) {
				throw new ProductImageUploadException("Cannot find or open the product image!");
			}			
		}
		
	}

}
