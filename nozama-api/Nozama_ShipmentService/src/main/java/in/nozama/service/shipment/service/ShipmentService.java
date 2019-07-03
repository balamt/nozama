package in.nozama.service.shipment.service;

import in.nozama.service.exception.ShipmentAlreadyCreatedException;
import in.nozama.service.exception.ShipmentNotFoundException;
import in.nozama.service.model.Order;
import in.nozama.service.model.Track;
import in.nozama.service.model.TrackCompositKeys;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipmentService {
    Track getShipmentDetails(TrackCompositKeys trackid) throws ShipmentNotFoundException;

	List<Track> getAll();

	Track createShipment(Order order) throws ShipmentAlreadyCreatedException;
}
