package in.nozama.service.product.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
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
		this.dirLocation = Paths.get(productImageUploadProperties.getLocation()).toAbsolutePath().normalize();
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
	public String saveProductImage(MultipartFile file) throws ProductImageUploadException {
		try {
			String fileName = file.getOriginalFilename();
			Path dirPath = this.dirLocation.resolve(fileName);

			Files.copy(file.getInputStream(), dirPath, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
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

}
