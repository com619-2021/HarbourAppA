
package dto;

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
	private int id;
	private double draft;
        private float length;

        
        // bool to declare whether boat can dock at high/low tide
        private boolean tide;

	// Empty default constructor 
	public Ship() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//more ship-defining 
        

//	public double getDraft() {
//		return draft;
//	}
//
//	public void setDraft(double draft) {
//		this.draft = draft;
//	}
        
        
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

        
	
}
