package isproject.facade.ics;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import isproject.ejb.ics.Animal;
import isproject.ejb.ics.Owner;

@Local
public interface FacadeLocal {
	public Owner findByOwnerId(int ownerID);

	public Owner createOwner(Owner owner);

	public Owner updateOwner(Owner owner);

	public void deleteOwner(int ownerID);

	public void deleteOwnerAndAnimals(int ownerID);

	public Animal findByAnimalId(int animalID);

	public Animal createAnimal(Animal animal);

	public Animal updateAnimal(Animal animal);

	public void deleteAnimal(int animalID);

	public List<Animal> findAllAnimals();


}