package business.concretes;

import java.util.List;
import java.util.function.Predicate;

import business.abstracts.UserService;
import core.base.concretes.Helper;
import core.results.abstracts.DataResult;
import core.results.abstracts.Result;
import core.results.concretes.ErrorDataResult;
import core.results.concretes.SuccessDataResult;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService {
	UserDao _userDao;

	public UserManager(UserDao userDao) {
		_userDao = userDao;
	}

	private void validateUserInfos(User user) throws Exception {
		if (!Helper.isValidEmail(user.getEmail()))
			throw new Exception("Invalid Email");
		if (user.getPassword().length() < 6)
			throw new Exception("Password length must be at least 6 characters");
		if (user.getName().length() < 2)
			throw new Exception("First Name length must be at least 2 characters");
		if (user.getSurname().length() < 2)
			throw new Exception("Last Name length must be at least 2 characters");
	}

	@Override
	public DataResult<User> add(User entity) {
		try {
			if (_userDao.get(u -> u.getEmail().equalsIgnoreCase(entity.getEmail())).size() > 0)
				throw new Exception("Email already in use");
			this.validateUserInfos(entity);
			_userDao.add(entity);
		} catch (Exception e) {
			return new ErrorDataResult<User>(entity, e.getMessage());
		}
		return new SuccessDataResult<User>(entity);
	}

	@Override
	public Result update(User entity) {
		try {
			this.validateUserInfos(entity);
			_userDao.update(entity);
		} catch (Exception e) {
			return new ErrorDataResult<User>(entity, e.getMessage());
		}
		return new SuccessDataResult<User>(entity);
	}

	@Override
	public Result delete(User entity) {
		try {
			_userDao.delete(entity);
		} catch (Exception e) {
			return new ErrorDataResult<User>(entity, e.getMessage());
		}
		return new SuccessDataResult<User>(entity, "User successfully deleted");
	}

	@Override
	public User get(int id) {
		return _userDao.get(id);
	}

	@Override
	public User get(Predicate<? super User> filter) {
		try {
			return _userDao.get(filter).get(0);
		} catch (Exception ex) {
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return _userDao.getAll();
	}

	@Override
	public List<User> getList(Predicate<? super User> filter) {
		return _userDao.get(filter);
	}
}
