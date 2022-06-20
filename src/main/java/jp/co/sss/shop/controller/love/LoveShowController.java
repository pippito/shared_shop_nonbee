package jp.co.sss.shop.controller.love;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.entity.Love;
import jp.co.sss.shop.entity.Love2;
import jp.co.sss.shop.entity.LoveKey2;
import jp.co.sss.shop.form.LoveForm;
import jp.co.sss.shop.repository.ItemRepository;
import jp.co.sss.shop.repository.LoveRepository;
import jp.co.sss.shop.repository.LoveRepository2;

@Controller
public class LoveShowController {

	/**
	 * お気に入り情報
	 */
	@Autowired
	LoveRepository loveRepository;

	/**
	 * 商品情報
	 */
	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * セッション
	 */
	@Autowired
	HttpSession session;
	

	@Autowired
	LoveRepository2 loveRepository2;
	
	@Autowired
	EntityManager entityManager;

	/**
	 * お気に入り情報一覧表示処理
	 * @param model Viewとの値受け渡し
	 * @return "love/list/love_list" お気に入り情報
	 * @author 大手瑞稀,NaotoShibata
	 */
	@GetMapping("/love/list")
	public String showLoveList(Model model, @ModelAttribute LoveForm form, HttpSession session) {

		//		会員IDに該当するお気に入り情報を登録日付順で検索
		UserBean userBean = (UserBean) session.getAttribute("user");
		List<Love> loveList = loveRepository.findByUserIdOrderByInsertDateDesc
				((Integer)userBean.getId());
		//商品情報
		Item item = new Item();
		//お気に入り商品情報リスト
		List<Item> loveItemList = new ArrayList<>();
		
		//お気に入りリストの要素数分の繰り返し
		for (int i = 0; i < loveList.size(); i++) {
			//お気に入り商品の情報取得
			Love love = loveList.get(i);
			//一致する商品の情報取得
			item = itemRepository.getById(love.getItem().getId());
			//お気に入り商品の情報をリストに追加
			loveItemList.add(item);
			
		}
		//お気に入り商品情報リストをViewへ渡す
		model.addAttribute("Loves", loveItemList);
		model.addAttribute("url", "/love/list");
		
			return "love/list/love_list";
	}
	
	/**
	 * お気に入り解除処理（お気に入り情報一覧画面）
	 *
	 * @param itemid 商品ID
	 * @param userid 会員ID
	 * @param model Viewとの値受渡し
	 * @return "redirect:/love/list" お気に入り情報一覧表示処理へ
	 */
	@RequestMapping(path = "/love/list/delete", method = {RequestMethod.GET})
	public String deleteLovelist(@RequestParam("itemId") int itemId,@RequestParam("userId") int userId, Model model) {
		
		// エンティティ生成
		Love2 love2 = new Love2();
		LoveKey2 key2 = new LoveKey2();
		
		// フォームの情報をコピー
		love2.setItemId(itemId);
		love2.setUserId(userId);
		key2.setItemId(itemId);
		key2.setUserId(userId);
		
		// 商品がお気に入り登録されているか判定
		if(loveRepository2.existsById(key2))  {
			
			// データベースから削除
			loveRepository2.delete(love2);
		}
		
		
		return "redirect:/love/list";
	}
}

