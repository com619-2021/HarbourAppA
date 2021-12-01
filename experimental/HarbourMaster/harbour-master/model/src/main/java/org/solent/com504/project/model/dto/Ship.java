
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
	private int pk;
        
        
	private UUID uuid = null;
        private double draft;
        private float length; //prefer draft

        
        // bool to declare whether boat can dock at high/low tide
        private boolean tide;

	// Empty default constructor 
	public Ship() {

	}

public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

//more ship-defining 
        

public double getDraft() {
		return draft;
	}

	public void setDraft(double draft) {
		this.draft = draft;
        }
        
       public boolean isTide() {
        return tide;
    }

    public void setTide(boolean tide) {
        this.tide = tide;
    }
 
        public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

        @Override
	public String toString() {
		return getClass().getSimpleName() + String.format("[pk=%d, uuid=%s, type=%s, draft=%f]", pk, uuid, draft);
	}
	
}
