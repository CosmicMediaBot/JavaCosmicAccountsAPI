package media.cosmic.api.accounts.utils;

/**
 * Utility class to store and retrieve user info
 * @author termer
 * @since v2.0.0
 */
public class CosmicUserInfo {
	private String _ID_ = null;
	private String _USERNAME_ = null;
	private String _EMAIL_ = null;
	private String _FULLNAME_ = null;
	private String _GENDER_ = null;
	private String _BIRTHDAY_ = null;
	private String _COUNTRY_ = null;
	private String _PHONE_ = null;
	
	/**
	 * Returns this user's CosmicID
	 * @return this user's CosmicID
	 * @since v2.0.0
	 */
	public String getCosmicID() {
		return _ID_;
	}
	/**
	 * Stores a new CosmicID value for this object
	 * @param id the value
	 * @since v2.0.0
	 */
	public void setCosmicID(String id) {
		_ID_ = id;
	}
	public boolean isCosmicIDPresent() {
		return _ID_!=null;
	}
	/**
	 * Returns this user's username
	 * @return this user's username
	 * @since v2.0.0
	 */
	public String getUsername() {
		return _USERNAME_;
	}
	/**
	 * Stores a new username value for this object
	 * @param username the value
	 * @since v2.0.0
	 */
	public void setUsername(String username) {
		_USERNAME_ = username;
	}
	/**
	 * Returns whether a username value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isUsernamePresent() {
		return _USERNAME_!=null;
	}
	
	/**
	 * Returns this user's email
	 * @return this user's email
	 * @since v2.0.0
	 */
	public String getEmail() {
		return _EMAIL_;
	}
	/**
	 * Stores a new email value for this object
	 * @param email the value
	 * @since v2.0.0
	 */
	public void setEmail(String email) {
		_EMAIL_ = email;
	}
	/**
	 * Returns whether an email value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isEmailPresent() {
		return _EMAIL_!=null;
	}
	
	/**
	 * Returns this user's full name
	 * @return this user's full name
	 * @since v2.0.0
	 */
	public String getFullName() {
		return _FULLNAME_;
	}
	/**
	 * Stores a new full name value for this object
	 * @param fullName the value
	 * @since v2.0.0
	 */
	public void setFullName(String fullName) {
		_FULLNAME_ = fullName;
	}
	/**
	 * Returns whether a full name is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isFullNamePresent() {
		return _FULLNAME_!=null;
	}
	
	/**
	 * Returns this user's gender
	 * @return this user's gender
	 * @since v2.0.0
	 */
	public String getGender() {
		return _GENDER_;
	}
	/**
	 * Stores a new gender value for this object
	 * @param gender the value
	 * @since v2.0.0
	 */
	public void setGender(String gender) {
		_GENDER_ = gender;
	}
	/**
	 * Returns whether a gender value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isGenderPresent() {
		return _GENDER_!=null;
	}
	
	/**
	 * Returns this user's birthday
	 * @return this user's birthday
	 * @since v2.0.0
	 */
	public String getBirthday() {
		return _BIRTHDAY_;
	}
	/**
	 * Stores a new birthday value for this object
	 * @param birthday the value
	 * @since v2.0.0
	 */
	public void setBirthday(String birthday) {
		_BIRTHDAY_ = birthday;
	}
	/**
	 * Returns whether a birthday value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isBirthdayPresent() {
		return _BIRTHDAY_!=null;
	}
	
	/**
	 * Returns this user's country
	 * @return this user's country
	 * @since v2.0.0
	 */
	public String getCountry() {
		return _COUNTRY_;
	}
	/**
	 * Stores a new country value for this object
	 * @param country the value
	 * @since v2.0.0
	 */
	public void setCountry(String country) {
		_COUNTRY_ = country;
	}
	/**
	 * Returns whether a country value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isCountryPresent() {
		return _COUNTRY_!=null;
	}
	
	/**
	 * Returns this user's phone number
	 * @return this user's phone number
	 * @since v2.0.0
	 */
	public String getPhoneNumber() {
		return _PHONE_;
	}
	/**
	 * Stores a new phone number value for this object
	 * @param phone the value
	 * @since v2.0.0
	 */
	public void setPhoneNumber(String phone) {
		_PHONE_ = phone;
	}
	/**
	 * Returns whether a phone number value is present
	 * @return whether the value is present
	 * @since v2.0.0
	 */
	public boolean isPhoneNumberPresent() {
		return _PHONE_!=null;
	}
}
