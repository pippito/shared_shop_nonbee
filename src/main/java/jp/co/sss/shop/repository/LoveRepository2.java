package jp.co.sss.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.shop.entity.Love2;
import jp.co.sss.shop.entity.LoveKey2;

/**
 * itemsテーブル用リポジトリ
 *
 * @author System Shared
 */
@Repository
public interface LoveRepository2 extends JpaRepository<Love2, LoveKey2> {
}
