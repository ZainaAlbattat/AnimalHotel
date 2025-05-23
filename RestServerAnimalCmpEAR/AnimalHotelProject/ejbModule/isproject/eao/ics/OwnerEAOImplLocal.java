package isproject.eao.ics;

import javax.ejb.Local;

import isproject.ejb.ics.Owner;


@Local
public interface OwnerEAOImplLocal {

	public Owner findByOwnerId(int ownerID);
	public Owner createOwner(Owner owner);
	public Owner updateOwner(Owner owner);
	public void deleteOwner(int ownerID);
	public void deleteOwnerAndAnimals (int ownerId);
}