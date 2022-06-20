package jp.co.sss.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class LoveKey2 implements Serializable{
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}
