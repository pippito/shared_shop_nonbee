package jp.co.sss.shop.bean;

import java.time.LocalDateTime;

/**
 * 商品情報クラス
 *
 * @author SystemShared
 */
/**
 * 商品情報クラス
 *
 * @author SystemShared
 */
public class LoveBean {

	/**
	 * 商品ID
	 */
	private Integer loveId;

	/**
	 * 商品名
	 */
	private Integer itemId;

	/**
	 * 価格
	 */
	private Integer userId;

	/**
	 * 商品説明
	 */
	private LocalDateTime createdAt;

	public Integer getLoveId() {
		return loveId;
	}

	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
	}

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

	public LocalDateTime getInsertDate() {
		return createdAt;
	}

	public void setInsertDate(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
