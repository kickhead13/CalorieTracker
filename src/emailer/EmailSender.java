package emailer;
import java.util.Random;
import java.util.random.*;

public class EmailSender {
	public static void main(String arg[]) {
		Random rand = new Random();
		Integer code = rand.nextInt();
		send("nfel bmac mobj ujxi", "idkwhatthismeans12@gmail.com", code.toString());
	}
	
	public static void send(String emailPassword, String receiver, String code) {
		try {
			ProcessBuilder processBuilder =
					new ProcessBuilder(
							"python", 
							"C:\\Users\\Alex\\eclipse-workspace\\calorieTracker\\src\\emailsender.py",
							emailPassword,
							receiver,
							code
							);
			processBuilder.redirectErrorStream(true);

			Process process = processBuilder.start();
		} catch(Exception e) {
			
		}
	}
}
