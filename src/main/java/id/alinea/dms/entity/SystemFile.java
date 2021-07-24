package id.alinea.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="system_files")
public class SystemFile extends BaseEntity{
    
    @Setter @Getter
    @Column(name="file_name")
    private String fileName;
    
    @Setter @Getter
    @Column(name="file_type")
    private String fileType;
    
    @Setter @Getter
    @Column(name="entity_id")
    private String entityId;
    
    @Setter @Getter
    @Column(name="entity_type")
    private String entityType;

    @Setter @Getter
    @Column(name="entity_field")
    private String entityField;

    @Setter @Getter
    @Column(name="file_uuid")
    private String fileUuid;

    @Setter @Getter
    @Column(name="url")
    private String url;
    
    @Setter @Getter
    @Column(name="thumbnail_url")
    private String thumbnailUrl;
    
    @Setter @Getter
    @Column(name="file_path")
    private String filePath;
    
    @Setter @Getter
    @Column(name="tags")
    private String tags;
    
    @Setter @Getter
    @Column(name="imagekit_raw_response")
    private String imagekitRawResponse; 

}
