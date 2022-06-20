package jp.co.sss.shop.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.shop.entity.Love;
import jp.co.sss.shop.entity.LoveKey;
/**
 * lovesテーブル用リポジトリ
 *
 * @author System Shared
 */
@Repository
public interface LoveRepository extends JpaRepository<Love, LoveKey> {
	
	List<Love> findByUserIdOrderByInsertDateDesc(int userId);
	
	LoveRepository deleteByUserIdAndItemId(Integer userId, Integer itemId);
}