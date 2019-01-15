package group.bridge.web.service;

import group.bridge.web.entity.Warn_record;
import org.springframework.stereotype.Service;

//BaseService类是业务类Service的父类，用于提供通用的方法，也就是说它是个通用类，
// 包括对数据库的增删查改，还有存储过程，还有各种调用SQL语句的查询方式

public interface Warn_recordService  extends BaseService<Warn_record,Integer>{

}
