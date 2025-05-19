package isproject.eao.ics;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import isproject.ejb.ics.Animal;
import isproject.ejb.ics.Owner;

/**
 * Session Bean implementation class OwnerEAOImpl
 */
@Stateless
public class OwnerEAOImpl implements OwnerEAOImplLocal {

	@PersistenceContext(unitName = "AnimalHotelPU")
	private EntityManager em;

	public OwnerEAOImpl() {
		// TODO Auto-generated constructor stub

	}

	public Owner findByOwnerId(int ownerID) {
		return em.find(Owner.class, ownerID);
	}

	public Owner createOwner(Owner owner) {
		em.persist(owner);
		return owner;
	}

	public Owner updateOwner(Owner owner) {
		em.merge(owner);
		return owner;
	}

	public void deleteOwner(int ownerID) {
		Owner o = this.findByOwnerId(ownerID);
		if (o != null) {
			em.remove(o);
		}
	}
		 public void deleteOwnerAndAnimals(int ownerId) {
		        Owner owner = em.find(Owner.class, ownerId);
		        if (owner != null) {
		            Set<Animal> animals = owner.getAnimals();
		            if (animals != null && !animals.isEmpty()) {
		                for (Animal animal : animals) {
		                    em.remove(animal);
		                }
		            }
		            em.remove(owner);
		        }
	}

}