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
<<<<<<< HEAD
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
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
<<<<<<< HEAD
@IdClass(value=LoveKey.class)
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
public class Love  implements Serializable {

	/**
	 * 会員ID
	 */
	@Id
<<<<<<< HEAD
	@Column(name="userId")
=======
<<<<<<< HEAD
	@Column(name="userId")
=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer userId;

	/**
	 * 商品ID
	 */
	@Id
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
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
<<<<<<< HEAD
=======
=======
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_loves_gen")
	@SequenceGenerator(name = "seq_loves_gen", sequenceName = "seq_loves", allocationSize = 1)
	private Integer itemId;

	/**
	 * 登録日付
	 */
	@Column(insertable = false)
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	private Date insertDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
<<<<<<< HEAD
=======
=======
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}