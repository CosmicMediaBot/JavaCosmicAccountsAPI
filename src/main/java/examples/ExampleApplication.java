package examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import media.cosmic.api.accounts.CosmicAccountSession;
import media.cosmic.api.accounts.CosmicAccounts;
import media.cosmic.api.accounts.utils.CosmicUserInfo;

public class ExampleApplication {
	public static void main(String[] args) {
		try {
			// Get private key and callback URL from the command line
			BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Please enter your private key\n> ");
			
			// Private key
			String privKey = terminal.readLine();
			
			System.out.print("\nPlease enter a callback URL\n> ");
			
			// Callback URL
			String callback = terminal.readLine();
			
			CosmicAccountSession sess = CosmicAccounts.createSession(privKey, callback);
			
			System.out.println("Please visit the following URL to authorize this session:");
			System.out.println(sess.getAuthorizationURL()+"\n");
			
			// Refresh every 2 seconds
			while(!sess.isAuthorized()) {
				Thread.sleep(2000);
			}
			
			System.out.println("\nAuthorized!");
			
			CosmicUserInfo info = sess.fetchUserInfo();
			
			// Print all account information available
			if(info.isCosmicIDPresent()) {
				System.out.println("Cosmic ID: "+info.getCosmicID());
			}
			if(info.isUsernamePresent()) {
				System.out.println("Username: "+info.getUsername());
			}
			if(info.isEmailPresent()) {
				System.out.println("Email: "+info.getEmail());
			}
			if(info.isFullNamePresent()) {
				System.out.println("Full name: "+info.getFullName());
			}
			if(info.isGenderPresent()) {
				System.out.println("Gender: "+info.getGender());
			}
			if(info.isBirthdayPresent()) {
				System.out.println("Birthday: "+info.getBirthday());
			}
			if(info.isCountryPresent()) {
				System.out.println("Country: "+info.getCountry());
			}
			if(info.isPhoneNumberPresent()) {
				System.out.println("Phone number: "+info.getPhoneNumber());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}