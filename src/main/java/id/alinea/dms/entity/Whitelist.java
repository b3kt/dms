package id.alinea.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="system_whitelist")
public class Whitelist extends BaseEntity {
    
    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    

    
}
