package group.bridge.web.dao;

import group.bridge.web.entity.WarnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WarnRecordRepository extends BaseRepository<WarnRecord, Integer> {
    List<WarnRecord> findAllByStatus(String status);

    Page<WarnRecord> findAllByOrderByDateDesc(Pageable pageable);

    @Query(nativeQuery = true,
            value="INSERT INTO warn_records (warn_date, status, warn_para, sensor_id)" +
                    "values (warn_date = :warn_date, status = :status, warn_para = :warn_para, sennsor_id = :sensor_id)")
    void InsertWarn_records(@Param("warn_date")Date warn_date, @Param("status")String status, @Param("warn_para")String warn_para, @Param("sensor_id")Integer sensor_id);
}
