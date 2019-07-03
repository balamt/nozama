package in.nozama.service.shipment.service;

import in.nozama.service.exception.ShipmentAlreadyCreatedException;
import in.nozama.service.exception.ShipmentNotFoundException;
import in.nozama.service.model.Order;
import in.nozama.service.model.Track;
import in.nozama.service.model.TrackCompositKeys;
import in.nozama.service.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ShipmentServiceImpl implements ShipmentService {

	@Autowired
	ShipmentRepository shipmentRepository;

	@Override
	public Track getShipmentDetails(TrackCompositKeys trackid) throws ShipmentNotFoundException {
		Optional<Track> optTrack =  shipmentRepository.findById(trackid);

		if(!optTrack.isPresent()){
			throw new ShipmentNotFoundException("No Such Shipment or Track Id Found");
		}
		return optTrack.get();
	}

	@Override
	public List<Track> getAll() {
		return shipmentRepository.findAll();
	}

	@Override
	public Track createShipment(Order order) throws ShipmentAlreadyCreatedException {

		TrackCompositKeys trackCompositKeys = new TrackCompositKeys(order.getUser(),order);
		if(shipmentRepository.findById(trackCompositKeys).isPresent()){
			throw new ShipmentAlreadyCreatedException("Shipment is already created");
		}

		Track track = new Track();
		track.setWarehouse(order
		.getItems().iterator().next().getProduct().getWarehouse());
		track.setTrackCompositKeys(trackCompositKeys);
		track.setCreatedDate(LocalDateTime.now());

		Track nTrack = shipmentRepository.saveAndFlush(track);
		nTrack.setTrackCompositKeys(nTrack.getTrackCompositKeys());
		return nTrack;
	}
}
