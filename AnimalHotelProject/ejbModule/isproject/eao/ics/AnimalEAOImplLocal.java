package isproject.eao.ics;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import isproject.ejb.ics.Animal;

@Local
public interface AnimalEAOImplLocal {
	public Animal findByAnimalID(int animalID);
	public Animal createAnimal(Animal animal);
	public Animal updateAnimal(Animal animal);
	public void deleteAnimal(int animalID);
	public List<Animal> findAllAnimals();
  //  public Set<Animal> findAnimalsByOwnerId(int ownerId);
}