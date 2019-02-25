package group.bridge.web.dao;

import group.bridge.web.entity.WarnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WarnRecordRepository extends BaseRepository<WarnRecord, Integer> {
    List<WarnRecord> findAllByStatus(String status);

    Page<WarnRecord> findAllByOrderByDateDesc(Pageable pageable);
}
