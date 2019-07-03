package in.nozama.service.shipment.repository;

import in.nozama.service.model.Track;
import in.nozama.service.model.TrackCompositKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ShipmentRepository extends JpaRepository<Track, TrackCompositKeys> {

}
