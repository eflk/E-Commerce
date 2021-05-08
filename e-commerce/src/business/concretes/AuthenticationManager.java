package business.concretes;

import java.util.Arrays;
import java.util.Base64;
import business.abstracts.AuthenticationService;
import business.abstracts.AuthenticatorAdapterService;
import business.abstracts.UserService;
import core.results.abstracts.DataResult;
import core.results.abstracts.Result;
import core.results.concretes.ErrorDataResult;
import core.results.concretes.ErrorResult;
import core.results.concretes.SuccessResult;
import entities.concretes.User;

public class AuthenticationManager implements AuthenticationService {
	UserService _userService;

	public AuthenticationManager(UserService userService) {
		_userService = userService;

	}

	@Override
	public Result signUp(User user) {
		Result addResult = _userService.add(user);
		return addResult.isSuccess() ? new SuccessResult(this.generateAndSendEmailConfirmation(user)) : addResult;
	}

	private String generateAndSendEmailConfirmation(User user) {
		return String.format("Please click the link to confirm your email. http://www.test123.com/confirmMail/%s to comfirmation",
				Base64.getEncoder().encodeToString(String.valueOf(user.getId() + "_" + user.getEmail()).getBytes()));

	}

	public Result confirmEmail(String confirmationCode) {
		try {
			int a = 1;
			String[] info = new String(Base64.getDecoder().decode(confirmationCode.getBytes())).split("_");
			User user = _userService.get(u -> u.getId() == Integer.parseInt(info[0]) && u.getEmail().equalsIgnoreCase(info[1]) );
			if (user != null) {
				user.setMailConfirmed(true);
				Result updateResult = _userService.update(user);
				return updateResult.isSuccess() ? new SuccessResult("The email has been confirmed.") : updateResult;
			}
		} catch (Exception e) {

		}
		return new ErrorResult("Incorect confirmation code");

	}

	@Override
	public DataResult<User> signIn(AuthenticatorAdapterService authAdapter) {

		DataResult<User> result = authAdapter.validateAuthentication(_userService);
		if (result.isSuccess()) {
			Result updateResult = _userService.update(result.getData());
			if(!updateResult.isSuccess()) return new ErrorDataResult<User>(result.getData(), updateResult.getCode(), updateResult.getMessage());
		}

		return result;
	}

}
