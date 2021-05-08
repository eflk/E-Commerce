package business.abstracts;

import core.results.abstracts.DataResult;
import entities.concretes.User;

public interface AuthenticatorAdapterService {
	DataResult<User> validateAuthentication(UserService userService);

}
