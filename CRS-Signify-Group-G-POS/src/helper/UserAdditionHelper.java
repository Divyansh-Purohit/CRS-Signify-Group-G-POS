/**
 * 
 */
package helper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.signify.bean.Student;
import com.signify.collection.UserData;

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
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		System.out.print("Enter Address: ");
		String address = sc.nextLine();
		System.out.print("Enter Date of Joining (YYYY-MM-DD) / PRESS 1 FOR TODAY: ");
		String doj = sc.nextLine();
		if(doj.equals("1"))
		{
			doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		String[] userDetails = {name, address, password, doj};
		return userDetails;
	}
}
