package testing;

import media.cosmic.api.accounts.CosmicAccounts;
import media.cosmic.api.accounts.calls.CosmicAPICall;
import media.cosmic.api.accounts.models.FetchUserInfoModel;
import media.cosmic.api.accounts.models.StartAuthSessionModel;

public class CAAPI {
	public static void main(String[] args) {
		try {
			// Use dev server
			CosmicAccounts.setBaseURL("https://accounts-dev.cosmic.media/api/");
			
			
			System.out.println(((StartAuthSessionModel)CosmicAPICall.START_AUTH_SESSION.execute(new String[]{"EO6wO0c7L5ty","https://termer.net/"})).key);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}