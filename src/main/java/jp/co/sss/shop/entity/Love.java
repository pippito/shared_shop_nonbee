package jp.co.sss.shop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 会員情報エンティティクラス
 *
 * @author SystemShared
 */
@Entity
@Table(name = "loves")
public class Love  implements Serializable {

	/**
	 * 会員ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer userId;

	/**
	 * 商品ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer itemId;

	/**
	 * 登録日付
	 */
	@Column(insertable = false)
	private Date insertDate;

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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}