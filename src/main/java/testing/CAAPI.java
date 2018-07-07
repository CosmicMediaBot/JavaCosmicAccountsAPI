package testing;

import media.cosmic.api.accounts.CosmicAccounts;

public class CAAPI {
	public static void main(String[] args) {
		try {
			// Use dev server
			CosmicAccounts.setBaseURL("https://accounts-dev.cosmic.media/api/");
			
			
			System.out.println(CosmicAccounts.getAPIVersionString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}