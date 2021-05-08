import business.abstracts.AuthenticationService;
import business.abstracts.UserService;
import business.concretes.AuthenticationManager;
import business.concretes.GoogleSSOAuthenticatorAdapter;
import business.concretes.SystemAuthenticatorAdapter;
import business.concretes.UserManager;
import core.base.concretes.Helper;
import core.results.abstracts.Result;
import dataAccess.concretes.HibernateUserDao;
import entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		User user = null;
		Result addResult= null;
		
		
		// Create user with HibernateUserDao
		UserService userService = new UserManager(new HibernateUserDao());
		AuthenticationService authenticationService = new AuthenticationManager(userService);
		
		//user 1
		user = new User(0, "Emre", "Felek", "emrefelek@gmail.com", "12345");
		addResult = authenticationService.signUp(user);
		System.out.printf("%s - %s - [Message: \"%s\"]\n", addResult.isSuccess() ? "Successful" : "Unsuccessful.", user.getEmail(), addResult.getMessage());

		//user 2
		user = new User(0, "Ali", "Veli", "aliveli@gmail.com", "49504950");
		addResult = authenticationService.signUp(user);
		System.out.printf("%s - %s - [Message: \"%s\"]\n", addResult.isSuccess() ? "Successful" : "Unsuccessful.", user.getEmail(), addResult.getMessage());
		
		System.out.println("\n\nUSER LIST IN DB:_________________________________________");
		Helper.<User>dumpGetterValuesOfEntities(userService.getAll());
		
		//Lets authenticate for aliveli@gmail.com
		Result signInResult = authenticationService.signIn(new SystemAuthenticatorAdapter("aliveli@gmail.com", "49504950"));
		System.out.printf("\n\n%s - %s - [Message: \"%s\"]\n", signInResult.isSuccess() ? "Successful" : "Unsuccessful.", user.getEmail(), signInResult.getMessage());
		
		//User has click to link
		Result confirmEmailResult = authenticationService.confirmEmail("MV9hbGl2ZWxpQGdtYWlsLmNvbQ==");
		System.out.printf("\n\n%s - %s - [Message: \"%s\"]\n", confirmEmailResult.isSuccess() ? "Successful" : "Unsuccessful.", user.getEmail(), confirmEmailResult.getMessage());

		//Lets authenticate using for demouser@gmail.com with google sso
		Result signInResultWithGoogleSSO = authenticationService.signIn(new GoogleSSOAuthenticatorAdapter("demouser@gmail.com", "Passwd"));
		System.out.printf("\n\n%s - %s - [Message: \"%s\"]\n", signInResultWithGoogleSSO.isSuccess() ? "Successful" : "Unsuccessful.", "demouser@gmail.com", signInResultWithGoogleSSO.getMessage());
		
		System.out.println("\n\nUSER LIST IN DB:_________________________________________");
		Helper.<User>dumpGetterValuesOfEntities(userService.getAll());
		
	}
}
