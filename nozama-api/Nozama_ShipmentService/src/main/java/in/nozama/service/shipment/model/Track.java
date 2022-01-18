package in.nozama.service.shipment.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table( name = "tracking" )
public class Track implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TrackCompositKeys class is used as Composite Key constrain, by restricting Order_Id and User_Id collectively
	 * treated as Unique.
	 *
	 * Reference: https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
	 */
	@EmbeddedId
	@NonNull
	private TrackCompositKeys trackCompositKeys;

	@OneToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse = null;

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


	public TrackCompositKeys getTrackCompositKeys() {
		return trackCompositKeys;
	}

	public void setTrackCompositKeys(TrackCompositKeys trackCompositKeys) {
		this.trackCompositKeys = trackCompositKeys;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Track track = (Track) o;
		return trackCompositKeys.equals(track.trackCompositKeys) &&
				Objects.equals(warehouse, track.warehouse) &&
				Objects.equals(createdDate, track.createdDate) &&
				Objects.equals(updatedDate, track.updatedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trackCompositKeys, warehouse, createdDate, updatedDate);
	}

	@Override
	public String toString() {
		return "Track{" +
				", trackCompositKeys=" + trackCompositKeys +
				", warehouse=" + warehouse +
				", createdDate=" + createdDate +
				", updatedDate=" + updatedDate +
				'}';
	}
}
