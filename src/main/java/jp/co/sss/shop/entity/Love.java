package jp.co.sss.shop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 会員情報エンティティクラス
 *
 * @author SystemShared
 */
@Entity
@Table(name = "loves")
@IdClass(value=LoveKey.class)
public class Love  implements Serializable {

	/**
	 * 会員ID
	 */
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer userId;

	/**
	 * 商品ID
	 */
	@Id
//	@Column(name="itemId")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
//	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	/**
	 * 登録日付
	 */
	@Id
	@Column(insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Date insertDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}