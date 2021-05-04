package id.alinea.dms.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.alinea.dms.entity.SystemParameter;

@Repository
public interface SystemParameterRepository extends CrudRepository<SystemParameter,Long> {
    
    @Cacheable
    @Query("SELECT sp.value FROM SystemParameter sp WHERE sp.code = :code ")
    String getValueByCode(@Param("code") String code);
}
