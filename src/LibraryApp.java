import java.util.ArrayList;
import java.util.Scanner;

import com.library.userroles.UserRole;
import com.library.backlog.*;


/**
 * This will host as the runtime for the new app.
 * @author muhammadtayyabsaeed
 *
 */
public class LibraryApp {	
	
	public static  void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter your user name: ");
		String adminUsername = sc.nextLine();
		System.out.println("Please enter your password: ");
		String adminPassword = sc.nextLine(); 
		sc.close();
		
		User adminstrativeUser = new User(UserRole.ADMIN, "Tayyab", "adminpass");
		adminstrativeUser.login();
		
		
		}
	}
