package isproject.facade.ics;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import isproject.eao.ics.AnimalEAOImplLocal;
import isproject.eao.ics.OwnerEAOImpl;
import isproject.eao.ics.OwnerEAOImplLocal;
import isproject.ejb.ics.Animal;
import isproject.ejb.ics.Owner;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {

	@EJB
	OwnerEAOImplLocal ownerEAO;
	@EJB
	AnimalEAOImplLocal animalEAO;

	public Facade() {
		// TODO Auto-generated constructor stub
	}

	//Owner metoder
	public Owner findByOwnerId(int ownerID) {
		return ownerEAO.findByOwnerId(ownerID);
	}

	public Owner createOwner(Owner owner) {
		return ownerEAO.createOwner(owner);
	}

	public Owner updateOwner(Owner owner) {
		return ownerEAO.updateOwner(owner);
	}

	public void deleteOwner(int ownerID) {
		ownerEAO.deleteOwner(ownerID);
	}
	public void deleteOwnerAndAnimals(int ownerID) {
	    ownerEAO.deleteOwnerAndAnimals(ownerID);
	}

	//Animal metoder
	public Animal findByAnimalId(int animalID) {
		return animalEAO.findByAnimalID(animalID);
	}

	public Animal createAnimal(Animal animal) {
		return animalEAO.createAnimal(animal);
	}

	public Animal updateAnimal(Animal animal) {
		return animalEAO.updateAnimal(animal);
	}

	public void deleteAnimal(int animalID) {
		animalEAO.deleteAnimal(animalID);
	}
	
	
	public List<Animal> findAllAnimals() {
		return animalEAO.findAllAnimals();
		}
    }