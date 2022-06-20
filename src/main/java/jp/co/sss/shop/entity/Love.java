package jp.co.sss.shop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 会員情報エンティティクラス
 *
 * @author SystemShared
 */
@Entity
@Table(name = "loves")
<<<<<<< HEAD
@IdClass(value=LoveKey.class)
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
public class Love  implements Serializable {

	/**
	 * 会員ID
	 */
	@Id
<<<<<<< HEAD
	@Column(name="userId")
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer userId;

	/**
	 * 商品ID
	 */
	@Id
<<<<<<< HEAD
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
=======
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer itemId;

	/**
	 * 登録日付
	 */
	@Column(insertable = false)
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
	private Date insertDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
<<<<<<< HEAD

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
=======
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}