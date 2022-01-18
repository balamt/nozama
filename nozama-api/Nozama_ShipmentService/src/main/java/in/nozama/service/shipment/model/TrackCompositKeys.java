package in.nozama.service.shipment.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import in.nozama.service.entity.Order;
import in.nozama.service.user.model.User;

import java.io.Serializable;
import java.util.Objects;

/**
 * TrackCompositKeys class was created to achive the Composit Key in the DB Tables
 * Reference URL : https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
 */

@Embeddable
public class TrackCompositKeys implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order orderId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userId;

    public TrackCompositKeys(){}

    public TrackCompositKeys(User userId, Order orderId){
        this.userId = userId;
        this.orderId = orderId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackCompositKeys trackCompositKeys = (TrackCompositKeys) o;
        return orderId.equals(trackCompositKeys.orderId) &&
                userId.equals(trackCompositKeys.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId);
    }

    @Override
    public String toString() {
        return "TrackCompositKeys{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                '}';
    }
}
