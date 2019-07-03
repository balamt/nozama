package in.nozama.service.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "warehouses")
public class Warehouse extends ResourceSupport implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "warehouseid")
	Long warehouseid;
	
	@NotNull
	String warehouseCity;
	
	@NotNull
	String warehoustPincode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	User thirdPartyUser;

	public Long getWarehouseId() {
		return warehouseid;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseid = warehouseId;
	}

	public String getWarehouseCity() {
		return warehouseCity;
	}

	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	public String getWarehoustPincode() {
		return warehoustPincode;
	}

	public void setWarehoustPincode(String warehoustPincode) {
		this.warehoustPincode = warehoustPincode;
	}

	public User getThirdPartyUser() {
		return thirdPartyUser;
	}

	public void setThirdPartyUser(User thirdPartyUser) {
		this.thirdPartyUser = thirdPartyUser;
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseid + ", warehouseCity=" + warehouseCity + ", warehoustPincode="
				+ warehoustPincode + ", thirdPartyUser=" + thirdPartyUser + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Warehouse warehouse = (Warehouse) o;
		return warehouseid.equals(warehouse.warehouseid) &&
				Objects.equals(warehouseCity, warehouse.warehouseCity) &&
				Objects.equals(warehoustPincode, warehouse.warehoustPincode) &&
				Objects.equals(thirdPartyUser, warehouse.thirdPartyUser);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), warehouseid, warehouseCity, warehoustPincode, thirdPartyUser);
	}
}
