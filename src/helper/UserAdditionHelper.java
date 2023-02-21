/**
 * 
 */
package helper;
import java.util.Scanner;
import com.signify.bean.Student;
import com.signify.jdbc.UserData;

/**
 * @author dp201
 *
 */
public class UserAdditionHelper {

	public static String[] detailsHelper()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Address: ");
		String address = sc.nextLine();
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		String[] userDetails = {name, address, password};
		return userDetails;
	}
}
