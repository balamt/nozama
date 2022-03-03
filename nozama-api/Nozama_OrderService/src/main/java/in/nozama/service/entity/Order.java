package in.nozama.service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderid;

	@Column(name = "userid")
	private long user;

	@Column(name = "item_id")
	//https://stackoverflow.com/questions/6164123/org-hibernate-mappingexception-could-not-determine-type-for-java-util-set
	@ElementCollection(targetClass = Long.class)
	private Set<Long> items = new HashSet<>();

	//Default value set to NEW
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "order_status")
	private Status status = Status.NEW;

	/**
	 * If After Discount is Applied, what is the price
	 * If NO Discount is applied, set the total price to finalPrice
	 */
	@NotNull
	@Column(name = "final_price")
	private Double finalprice;

	@NotNull
	@Column(name = "total_price")
	private Double totalPrice;

	//Default value set to False
	@Column(name = "discount_applied")
	private Boolean discountApplied = Boolean.FALSE;

	@Column(name = "discount_percent")
	private Double discountPercentage;

	/**
	 * https://stackoverflow.com/questions/44220795/spring-boot-kafka-localdatetime
	 * LocalDateTime serialize and deserialize issue when using Kafka
	 */
	@CreationTimestamp
    @Column(name = "created_on")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "modified_on")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime updatedDate;

/*	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getFinalprice() {
		return finalprice;
	}

	public void setFinalprice(Double finalprice) {
		this.finalprice = finalprice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(Boolean discountApplied) {
		this.discountApplied = discountApplied;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}*/

/*	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return orderid.equals(order.orderid) &&
				Objects.equals(user, order.user) &&
				Objects.equals(items, order.items) &&
				status == order.status &&
				Objects.equals(finalprice, order.finalprice) &&
				Objects.equals(totalPrice, order.totalPrice) &&
				Objects.equals(discountApplied, order.discountApplied) &&
				Objects.equals(discountPercentage, order.discountPercentage) &&
				Objects.equals(createdDate, order.createdDate) &&
				Objects.equals(updatedDate, order.updatedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderid, user, items, status, finalprice, totalPrice, discountApplied, discountPercentage, createdDate, updatedDate);
	}*/

	@Override
	public String toString() {
		return "Order{" +
				"orderid=" + orderid +
				", user=" + user +
				", items=" + items +
				", status=" + status +
				", finalprice=" + finalprice +
				", totalPrice=" + totalPrice +
				", discountApplied=" + discountApplied +
				", discountPercentage=" + discountPercentage +
				", createdDate=" + createdDate +
				", updatedDate=" + updatedDate +
				'}';
	}
}

