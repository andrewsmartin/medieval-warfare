package newworldorder.common.persistence;

import newworldorder.common.model.User;

public interface IUserTransaction {

	public boolean createUser(User user) throws PersistenceException;
	
	public boolean loginUser(User user) throws PersistenceException;
	
	public void logoutUser(String username);
}
