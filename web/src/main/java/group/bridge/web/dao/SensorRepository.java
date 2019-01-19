package group.bridge.web.dao;

import group.bridge.web.entity.Sensor;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorRepository extends BaseRepository<Sensor, Integer> {
}
