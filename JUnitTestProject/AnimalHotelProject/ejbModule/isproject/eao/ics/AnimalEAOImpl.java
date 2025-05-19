package isproject.eao.ics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import isproject.ejb.ics.Animal;

@Stateless
public class AnimalEAOImpl implements AnimalEAOImplLocal {

	@PersistenceContext(unitName = "AnimalHotelPU")
	private EntityManager em;

	public AnimalEAOImpl() {
	}

	public Animal findByAnimalID(int animalID) {
		return em.find(Animal.class, animalID);
	}

	public Animal createAnimal(Animal animal) {
		em.persist(animal);
		return animal;
	}

	public Animal updateAnimal(Animal animal) {
		em.merge(animal);
		return animal;
	}

	public void deleteAnimal(int animalID) {
		Animal a = this.findByAnimalID(animalID);
		if (a != null) {
		em.remove(a);
		}
	}
	
	 
	 public List<Animal> findAllAnimals() {
		 TypedQuery<Animal> query =
		 em.createNamedQuery("Animal.findAll",Animal.class);
		 List<Animal> results = query.getResultList();
		 return results;
		 }
}