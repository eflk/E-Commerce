package business.abstracts;

import core.results.abstracts.DataResult;
import core.results.abstracts.Result;
import entities.concretes.User;

public interface AuthenticationService {
	Result signUp(User user);

	DataResult<User> signIn(AuthenticatorAdapterService authAdapter);

	public Result confirmEmail(String confirmationCode);

}
