package in.nozama.service.shipment.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "warehouses")
public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "warehouseid")
	Long warehouseid;
	
	@NotNull
	String warehouseCity;
	
	@NotNull
	String warehoustPincode;

	@Column(name = "userid", nullable = false)
	Long thirdPartyUser;

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

	public Long getThirdPartyUser() {
		return thirdPartyUser;
	}

	public void setThirdPartyUser(Long thirdPartyUser) {
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
