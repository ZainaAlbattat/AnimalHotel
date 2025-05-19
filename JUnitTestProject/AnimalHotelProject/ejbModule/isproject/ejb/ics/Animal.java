package isproject.ejb.ics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")
})
@Table(name = "Animal")


public class Animal implements Serializable {

	private int animalID;
	private String animalName;
	private String species;
	private Owner owner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AnimalID")
	public int getAnimalID() {
		return animalID;
	}

	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}


	@Column(name = "AnimalName")
	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	@Column(name = "Species")
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
	 

	@ManyToOne
	@JoinColumn(name = "OwnerID", referencedColumnName = "OwnerID")
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public void setOwnerID(int ownerId) {
	    if (owner == null) {
	        owner = new Owner();
	    }
	    owner.setOwnerID(ownerId);
	}

}