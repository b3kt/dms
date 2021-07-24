package id.alinea.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="system_parameters")
public class SystemParameter extends BaseEntity{
    
    @Setter @Getter
    @Column
    private String code;

    @Setter @Getter
    @Column
    private String value;

    @Setter @Getter
    @Column
    private String type;

}
