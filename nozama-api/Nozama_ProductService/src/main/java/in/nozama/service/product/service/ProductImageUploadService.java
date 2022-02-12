package in.nozama.service.product.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.nozama.service.product.exception.ProductImageUploadException;

@Service
public interface ProductImageUploadService {
	void init() throws ProductImageUploadException;

	String saveProductImage(MultipartFile file) throws ProductImageUploadException;

	Resource loadProductImage(String filename) throws ProductImageUploadException;

}
