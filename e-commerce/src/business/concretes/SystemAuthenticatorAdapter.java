package business.concretes;

import business.abstracts.AuthenticatorAdapterService;
import business.abstracts.UserService;
import core.base.concretes.Helper;
import core.results.abstracts.DataResult;
import core.results.concretes.ErrorDataResult;
import core.results.concretes.SuccessDataResult;
import entities.concretes.User;

public class SystemAuthenticatorAdapter implements AuthenticatorAdapterService {
	private String email;
	private String password;

	public SystemAuthenticatorAdapter(String email, String password) {
		this.email = email;
		this.password = password;

	}

	@Override
	public DataResult<User> validateAuthentication(UserService userService) {
		if (!Helper.isValidEmail(email) || password.length() < 6)
			return new ErrorDataResult<User>(null, "Incorrect user");

		User user = userService.get(p -> p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password) && p.getActive());

		if (user != null) {
			if (user.getMailConfirmed())
				return new ErrorDataResult<User>(null, "The email address has not been verified yet.");
			user.setLastLoginTime(Helper.getDateSystemDateUTC());
			return new SuccessDataResult<User>(user);
		}
		return new ErrorDataResult<User>(null, "Incorrect user");
	}

}
