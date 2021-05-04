package id.alinea.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="system_parameters")
public class SystemParameter extends BaseEntity{
    
    @Column
    private String code;

    @Column
    private String value;

    @Column
    private String type;


    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

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
