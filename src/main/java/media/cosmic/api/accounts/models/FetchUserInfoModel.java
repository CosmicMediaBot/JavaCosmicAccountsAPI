package media.cosmic.api.accounts.models;

/**
 * Response model for fetch_user_info API call
 * @author termer
 * @since v2.0.0
 */
public class FetchUserInfoModel extends CosmicAccountModel {
	public String cosmic_id = null;
	public String username = null;
	public String email = null;
	public String fullname = null;
	public String gender = null;
	public String birthdate = null;
	public String country = null;
	public String phone = null;
}