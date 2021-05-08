package id.alinea.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="system_files")
public class SystemFile extends BaseEntity{
    
    @Column(name="file_name")
    private String fileName;
    
    @Column(name="file_type")
    private String fileType;
    
    @Column(name="entity_id")
    private String entityId;
    
    @Column(name="entity_type")
    private String entityType;

    @Column(name="entity_field")
    private String entityField;

    @Column(name="file_uuid")
    private String fileUuid;


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return this.fileType;
    }
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return this.entityId;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return this.entityType;
    }
    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getFileUuid() {
        return this.fileUuid;
    }

    
    public void setEntityField(String entityField) {
        this.entityField = entityField;
    }

    public String getEntityField() {
        return this.entityField;
    }
}
