package group.bridge.web.dao;

import group.bridge.web.entity.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface SensorRepository extends BaseRepository<Sensor,Integer> {
    @Query(nativeQuery = true,
            value = "Select COUNT(*) FROM sensors;")
    Integer getSensorCount();

}
