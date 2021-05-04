package id.alinea.dms.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.alinea.dms.entity.SystemFile;

@Repository
public interface SystemFileRepository extends CrudRepository<SystemFile, Long> {
    
    @Query("SELECT sf FROM SystemFile sf WHERE sf.fileUuid = :uuid")
    SystemFile findByFileUuid(@Param("uuid") String uuid);
}
