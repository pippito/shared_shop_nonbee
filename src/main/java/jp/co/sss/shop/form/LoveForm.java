package jp.co.sss.shop.form;

import java.sql.Date;

import javax.validation.constraints.Pattern;

/**
 * お気に入り情報のフォーム
 *
 *　0=チェックボックスOFF、1＝チェックボックスON
 * @author SystemShared
 */
public class LoveForm {
	
	@Pattern(regexp="checked")
	private String inputSingleCheck;

	public String getInputSingleCheck() {
	     return inputSingleCheck;
	}
	public void setInputSingleCheck(String inputSingleCheck) {
	     this.inputSingleCheck = inputSingleCheck;
	}

	/**
	 * ユーザーID
	 */
	private int userId;
	
	/**
	 * 商品ID
	 */
	private int itemId;
	
	/**
	 * 登録日付
	 */
	private Date insertDate;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
