package dataAccess.concretes;

import core.dbContexts.concretes.memoryDbContext;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class HibernateUserDao extends EntityRepositoryBase<User> implements  UserDao {

	public HibernateUserDao() {
		super(memoryDbContext.users);
	}

}
