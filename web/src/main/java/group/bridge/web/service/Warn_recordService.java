package group.bridge.web.service;

import group.bridge.web.entity.Warn_record;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//BaseService类是业务类Service的父类，用于提供通用的方法，也就是说它是个通用类，
// 包括对数据库的增删查改，还有存储过程，还有各种调用SQL语句的查询方式

public interface Warn_recordService  extends BaseService<Warn_record,Integer>{
    Warn_record addWarn_record(Warn_record wr);
//    //本地sql语句查询
//    @Query(value = "select * from warn_record  where status = 未解除",nativeQuery=true)
    List<Warn_record> getWarn_record();
}
