
package org.solent.com504.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ships")
public class Ship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
        @ApiModelProperty(hidden = true)
	private Long id;
        private UUID uuid = null;

 
        private Double draft;
                //Shipping team using depth AND size
        
        
        //passengers
        //lengthoverall
        private Float length; //prefer draft
        //arrival_date
        //services

        
        // bool to declare whether boat can dock at high/low tide
        private Boolean tide;

	// Empty default constructor 
	public Ship() {

	}

    
           public Long getId() {
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }

        public UUID getUuid() {
        return uuid;
    }

        public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
        
        public double getDraft() {
		return draft;
	}

	public void setDraft(Double draft) {
		this.draft = draft;
        }
        
       public boolean isTide() {
        return tide;
    }

    public void setTide(Boolean tide) {
        this.tide = tide;
    }
 
        public float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

        @Override
	public String toString() {
		return getClass().getSimpleName() + String.format("[id=%d, uuid=%s,draft=%f]", id, uuid, draft);
	}
	
}
