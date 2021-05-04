package id.alinea.dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.alinea.dms.entity.Whitelist;

@Repository
public interface WhitelistRepository extends CrudRepository<Whitelist,Long> {

    @Query(" SELECT w FROM Whitelist w WHERE w.type = :type ")
	List<Whitelist> findByType(@Param("type") String string);
    
}
