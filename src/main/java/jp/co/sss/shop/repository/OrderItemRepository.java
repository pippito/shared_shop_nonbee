package jp.co.sss.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.shop.entity.OrderItem;

/**
 * order_itemsテーブル用リポジトリ
 *
 * @author Seiyou Yume
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	
	//売れ筋順並び替え
		@Query("SELECT new OrderItem (o.item) FROM OrderItem o GROUP BY o.item ORDER BY sum(o.quantity) DESC, o.item.id ASC")
		public List<OrderItem> findByItemOrderByQuantityDescIdAsc();
}