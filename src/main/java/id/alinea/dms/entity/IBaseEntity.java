package id.alinea.dms.entity;

import java.util.Date;

public interface IBaseEntity {
    
    Long getId();
	void setId(Long id);

	String getCreatedBy();
	void setCreatedBy(final String createdBy);

	Date getCreatedDate();
	void setCreatedDate(final Date creationDate);

	String getLastModifiedBy();
	void setLastModifiedBy(final String lastModifiedBy);

	Date getLastModifiedDate();
	void setLastModifiedDate(final Date lastModifiedDate);
}
