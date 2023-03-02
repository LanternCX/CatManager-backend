package xyz.cdbxinhe.cat.backend.dao;

import org.springframework.data.repository.CrudRepository;
import xyz.cdbxinhe.cat.backend.entity.Cat;


/**
 * package xyz.cdbxinhe.cat.backend.dao
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
public interface CatRepository  extends CrudRepository<Cat,Long> {
}
