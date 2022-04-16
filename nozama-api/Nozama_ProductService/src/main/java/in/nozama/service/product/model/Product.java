package in.nozama.service.product.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUCTSEQ", sequenceName = "PRODUCTSEQ", initialValue = 10000, allocationSize = 2)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_code")
	@NotNull
	private String productCode;

	@Column(name = "product_name")
	@NotNull
	private String productName;

	@Column(name = "product_description")
	@NotNull
	private String productDescription;

	@Column(name = "rating")
	@Min(value = 0)
	@Max(value = 5)
	private Double rating;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "category")
	private Category category;

	@Column(name = "stock_quantity")
	private Integer stockQuantity;

	@Column(name = "price_per_item")
	private Double pricePerItem;

	@Column(name = "warehouse_id")
	private Long warehouse;

	private String productImg;

	// https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa
	@ElementCollection 
	@CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "tags")
	private List<String> tags;

	// https://stackoverflow.com/questions/811845/setting-a-jpa-timestamp-column-to-be-generated-by-the-database
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(updatable = true, nullable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	@NotNull
	private Long seller;

}
