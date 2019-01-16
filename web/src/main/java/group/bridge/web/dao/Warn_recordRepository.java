package group.bridge.web.dao;

import group.bridge.web.entity.Warn_record;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Warn_recordRepository extends BaseRepository<Warn_record,Integer>{
    List<Warn_record> findAllByStatus(String status);
}
