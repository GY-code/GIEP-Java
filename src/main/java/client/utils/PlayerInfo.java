/**
 * @title PlayerInfo.java
 *
 * @version 1.0
 *
 * @date 2020-11-26 10:13:49 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package client.utils;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title PlayerInfo
 *
 * @description store the personal information in local memory
 *
 */
public class PlayerInfo {
	private String id;
	private int gender;
	private String motto;
	private String friends_name;
	private String friends_request;
	private int pic;
	// singleton design pattern
	private static PlayerInfo instance = new PlayerInfo();

	private PlayerInfo() {
	}

	public static PlayerInfo getPlayerInfo() {
		return instance;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the motto
	 */
	public String getMotto() {
		return motto;
	}

	/**
	 * @param motto the motto to set
	 */
	public void setMotto(String motto) {
		this.motto = motto;
	}

	/**
	 * @return the friends_name
	 */
	public String getFriends_name() {
		return friends_name;
	}

	/**
	 * @param friends_name the friends_name to set
	 */
	public void setFriends_name(String friends_name) {
		this.friends_name = friends_name;
	}

	/**
	 * @return the friends_request
	 */
	public String getFriends_request() {
		return friends_request;
	}

	/**
	 * @param friends_request the friends_request to set
	 */
	public void setFriends_request(String friends_request) {
		this.friends_request = friends_request;
	}

	/**
	 * @return the pic
	 */
	public int getPic() {
		return pic;
	}

	/**
	 * @param pic the pic to set
	 */
	public void setPic(int pic) {
		this.pic = pic;
	}

	public String toString() {
		return "id:" + id + "\ngender:" + gender + "\nmotto:" + motto + "\nfriends_name:" + friends_name + "\npic:"
				+ pic;
	}

}
