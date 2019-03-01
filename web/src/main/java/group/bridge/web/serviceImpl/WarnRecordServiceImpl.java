package group.bridge.web.serviceImpl;

import group.bridge.web.dao.WarnRecordRepository;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service
public class WarnRecordServiceImpl extends BaseServiceImpl<WarnRecord, Integer> implements WarnRecordService {
    @Autowired
    WarnRecordRepository warnRecordRepository;

    public Page<WarnRecord> getPage(Pageable pageable){
        return warnRecordRepository.findAllByOrderByDateDesc(pageable);
    }
    @Override
    protected void setRepository() {
        this.repository = warnRecordRepository;
    }

    public List<WarnRecord> getWarn_record(){
        return warnRecordRepository.findAllByStatus("未解除");
    }

    public List<WarnRecord> getRelieveWarn_record(){

        return warnRecordRepository.findAllByStatus("已解除");
    }

    @Override
    public void insertWarn_record(Date warn_date, String status, String warn_para, Integer sensor_id){
        warnRecordRepository.InsertWarn_records(warn_date, status, warn_para, sensor_id);
    }
    public Long getNotRelieveCount(){
       return warnRecordRepository.count((recordRoot,criteriaQuery,Builder) -> {
            Predicate predicate;
            //root接口代表查询的根对象，通过root来获取需要的查询条件
            //如where id=1 其中的id通过root.get("id")获取
            //一定要使用Path<T>,保证类型安全
            Path<String> id=recordRoot.get("status");
            //然后通过CriteriaBuilder来构造条件，= <> < >等
            //Expression在java中是一个接口，Predicate实现了这个接口
            //普通and or > < <>
            // equal =
            predicate=Builder.equal(id,"未解除");
            //and
            //Expression

            return predicate;
        });
    }

    public List<WarnRecord> getWarnRecord(){
       return warnRecordRepository.getWarnRecord();
    }
}

