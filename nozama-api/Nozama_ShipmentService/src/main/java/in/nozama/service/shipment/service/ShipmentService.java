package in.nozama.service.shipment.service;

import in.nozama.service.entity.Order;
import in.nozama.service.model.Track;
import in.nozama.service.model.TrackCompositKeys;
import in.nozama.service.shipment.exception.ShipmentAlreadyCreatedException;
import in.nozama.service.shipment.exception.ShipmentNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipmentService {
    Track getShipmentDetails(TrackCompositKeys trackid) throws ShipmentNotFoundException;

	List<Track> getAll();

	Track createShipment(Order order) throws ShipmentAlreadyCreatedException;
}
