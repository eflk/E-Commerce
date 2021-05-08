package business.concretes;

import business.abstracts.AuthenticatorAdapterService;
import business.abstracts.UserService;
import core.base.concretes.Helper;
import core.results.abstracts.DataResult;
import core.results.abstracts.Result;
import core.results.concretes.ErrorDataResult;
import core.results.concretes.SuccessDataResult;
import entities.concretes.User;

public class GoogleSSOAuthenticatorAdapter implements AuthenticatorAdapterService {
	private String email;
	private String password;

	public GoogleSSOAuthenticatorAdapter(String email, String password) {
		this.email = email;
		this.password = password;

	}

	@Override
	public DataResult<User> validateAuthentication(UserService userService) {
		if (!Helper.isValidEmail(email))
			return new ErrorDataResult<User>(null, "Incorrect mail address");

		// We assume that the user is verified directly by google sso.
		boolean isUserVerifiedByGoogleSSO = true;
		if (!isUserVerifiedByGoogleSSO)
			return new ErrorDataResult<User>(null, "Incorrect user");

		User user = userService.get(p -> p.getEmail().equalsIgnoreCase(email));
		boolean newUser = user == null;
		if (newUser) {
			user = new User(0, "google.name" + Helper.getZDateTimeSystemDateUTC().getNano(), "google.surname", email,
					"<ManagedByGoogle>");
			Result addResult = userService.add(user);
			if (!addResult.isSuccess())
				return new ErrorDataResult<User>(null, addResult.getMessage());
		}
		user.setActive(true);
		user.setMailConfirmed(true);
		user.setLastLoginTime(Helper.getDateSystemDateUTC());
		return new SuccessDataResult<User>(user);

	}
}
