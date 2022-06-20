package jp.co.sss.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.shop.entity.Category;
import jp.co.sss.shop.entity.Item;

/**
 * itemsテーブル用リポジトリ
 *
 * @author System Shared
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {


	/**  商品情報を新着順で検索*/
	public List<Item> findByDeleteFlagOrderByInsertDateDescIdAsc(int deleteFlag);
	public List<Item> findByCategory(Category category);
	public List<Item>findAllByOrderByInsertDateAsc();
<<<<<<< HEAD
	
	// 新着順並び替え
	public List<Item> findAllByOrderByInsertDateDescIdDesc();

	//新着一覧
	public List<Item> findByOrderByInsertDateDescIdDesc();

=======
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
}
