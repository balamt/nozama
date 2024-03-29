package in.nozama.service.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "product.image.location")
public class ProductImageUploadProperties {
	
	@Value("${product.image.location}")
	private String location;

}
