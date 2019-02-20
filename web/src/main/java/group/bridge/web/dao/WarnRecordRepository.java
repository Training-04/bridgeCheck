package group.bridge.web.dao;


import group.bridge.web.entity.WarnRecord;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WarnRecordRepository extends BaseRepository<WarnRecord, Integer> {
    List<WarnRecord> findAllByStatus(String status);
}