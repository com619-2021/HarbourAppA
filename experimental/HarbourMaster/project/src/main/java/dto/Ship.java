
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
	
}
