package jp.co.sss.shop.controller.item;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

<<<<<<< HEAD
=======
=======
import java.util.List;

>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.entity.Category;
import jp.co.sss.shop.entity.Item;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
import jp.co.sss.shop.entity.Love2;
import jp.co.sss.shop.entity.LoveKey2;
import jp.co.sss.shop.entity.OrderItem;
import jp.co.sss.shop.repository.ItemRepository;
import jp.co.sss.shop.repository.LoveRepository;
import jp.co.sss.shop.repository.LoveRepository2;
import jp.co.sss.shop.repository.OrderItemRepository;
<<<<<<< HEAD
=======
=======
import jp.co.sss.shop.form.LoveForm;
import jp.co.sss.shop.repository.ItemRepository;
import jp.co.sss.shop.util.BeanCopy;
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af

/**
 * 商品管理 一覧表示機能(一般会員用)のコントローラクラス
 *
 * @author SystemShared
 */
@Controller
public class ItemShowCustomerController {
	/**
	 * 商品情報
	 */
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	LoveRepository loveRepository;
	
	@Autowired
	LoveRepository2 loveRepository2;

	
	@Autowired
	EntityManager entityManager;
	

	// 商品情報を全件表示
	
	@GetMapping("/item/list/item_list")
	public String showItemCustomer(Model model) {
		
		model.addAttribute("items", itemRepository.findByOrderByInsertDateDescIdDesc());
		
		return "item/list/item_list";
	}
	
	/**
	 * 商品一覧画面 新着順並び替え
	 *
	 * @param model    Viewとの値受渡し
	 * @return "item/list/item_list" 商品一覧画面へ
	 */
	@RequestMapping("/item/list/insertDate")
	public String newArrivalOrder(Model model) {
	
	model.addAttribute("items", itemRepository.findAllByOrderByInsertDateDescIdDesc());
	
	model.addAttribute("sortNum",0);
	
	return "item/list/item_list";
	 
	}
	
	/**
<<<<<<< HEAD
	 * 商品一覧画面  ナビゲーションバー 新着一覧
=======
<<<<<<< HEAD
	 * 商品一覧画面  ナビゲーションバー 新着一覧
=======
	 * 商品一覧画面  ナビゲーションバー 新着順
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	 *
	 * @param model    Viewとの値受渡し
	 * @return "item/list/item_list" 商品一覧画面へ
	 */
	@RequestMapping("/item/list/1")
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	public String  newArrivalList(Model model) {
	
	model.addAttribute("items", itemRepository.findByOrderByInsertDateDescIdDesc());
	
	return "item/list/item_list";

	}
	
	/**
	 * 商品一覧画面  売れ筋順並び替え
	 *
	 * @param model    Viewとの値受渡し
	 * @return "item/list/item_list" 商品一覧画面へ
	 */
	@RequestMapping("/item/list/hotSellers")
	public String hotSellers(Model model) {
	
		List<OrderItem> orderItemList = orderItemRepository.findByItemOrderByQuantityDescIdAsc();
		List<Item> itemList = new ArrayList<>();
		for (int i = 0; i < orderItemList.size(); i++) {
			itemList.add(orderItemList.get(i).getItem());
		}
		model.addAttribute("items",itemList );

		model.addAttribute("sortNum",1);
		
		return "item/list/item_list";

<<<<<<< HEAD
=======
=======
	public String sintyaku(Model model) {
	
	model.addAttribute("items", itemRepository.findAllByOrderByInsertDateAsc());
	
	return "item/list/item_list";
	
	}
	
//	++++++++++++++++++++以下改修++++++++++++++++++++	確認後に削除してください。
	
	/**
	 * 商品一覧画面
	 * テンプレートパス（/item/list）
	 * ファイル名（item_list.html）
	 * @author Naoto Shibata
	 */
	
	@GetMapping("item/list/item_list")
	public String showItemCustomer(Model model) {
		// 商品情報を全件検索(新着順)
		List<Item> itemList = itemRepository.findAll();

		// エンティティ内の検索結果をJavaBeansにコピー
		List<ItemBean> itemBeanList = BeanCopy.copyEntityToItemBean(itemList);

		// 商品情報をViewへ渡す
		model.addAttribute("items", itemBeanList);
		model.addAttribute("url", "/item/item_list/");
		return "item/list/item_list";
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	}
	
	/**
	 * 商品情報詳細表示処理
	 *
	 * @param id 商品ID
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "/item/detail/item_detail_user" 商品情報 詳細画面へ
	 */
	@RequestMapping(path = "/item/detail/{id}")
	public String showItem(@PathVariable int id, Model model) {

		// 商品IDに該当する商品情報を取得
		Item item = itemRepository.getById(id);

		ItemBean itemBean = new ItemBean();

		// Itemエンティティの各フィールドの値をItemBeanにコピー
		BeanUtils.copyProperties(item, itemBean);

		// 商品情報にカテゴリ名を設定
		itemBean.setCategoryName(item.getCategory().getName());

		// 商品情報をViewへ渡す
		model.addAttribute("item", itemBean);

		return "item/detail/item_detail_user";
	}
	
	/**
	 *ジャンル検索
	 *
	 * @param id 商品ID
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "item/list/item_list" 商品情報 一覧画面へ
	 */
	@RequestMapping(path = "item/list/category/1",method = {RequestMethod.GET})
	public String showCategoryItemList(@RequestParam("categoryId") int id, Model model) {
		
		Category category = new Category();
		
		category.setId(id);
		
		// 商品情報を全件検索(新着順)
		List<Item> itemList = itemRepository.findByCategory(category);

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
		// 商品情報をViewへ渡す
		model.addAttribute("items", itemList);
		model.addAttribute("url", "/item/list/item_list/");
		return "item/list/item_list";	
	}
	
	/**
	 * お気に入り登録処理-detail
	 *
	 * @param id 商品ID
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "/item/love/regist" 商品情報 詳細画面へ
	 */
	@RequestMapping(path = "/item/detail/regist", method = {RequestMethod.GET})
	public String registLove(@RequestParam("itemId") int itemId,@RequestParam("userId") int userId, Model model) {

		// 商品IDに該当する商品情報を取得
		Item item = itemRepository.getById(itemId);

		ItemBean itemBean = new ItemBean();

		// Itemエンティティの各フィールドの値をItemBeanにコピー
		BeanUtils.copyProperties(item, itemBean);

		// 商品情報にカテゴリ名を設定
		itemBean.setCategoryName(item.getCategory().getName());

		// 商品情報をViewへ渡す
		model.addAttribute("item", itemBean);
		
		
		// エンティティ生成
		Love2 love2 = new Love2();
		LoveKey2 key2 = new LoveKey2();
				
		// フォームの情報をコピー
		love2.setItemId(itemId);
		love2.setUserId(userId);
		key2.setItemId(itemId);
		key2.setUserId(userId);
				
		// 商品がお気に入り登録されているか判定
		if(loveRepository2.existsById(key2) == false) {
					
			// 現在日時の取得
			LocalDateTime time = LocalDateTime.now();
			java.sql.Date sqlDate = java.sql.Date.valueOf(time.toLocalDate());
			love2.setInsertDate(sqlDate);
					
			// データベースに登録
			loveRepository2.save(love2);
			}else {	
				// データベースから削除
				loveRepository2.delete(love2);
			}

		return "item/detail/item_detail_user";
	}
	
	/**
	 * お気に入り登録処理-list
	 *
	 * @param id 商品ID
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "/item/love/regist" 商品情報 詳細画面へ
	 */
	@RequestMapping(path = "/item/list/regist", method = {RequestMethod.GET})
	public String registLovelist(@RequestParam("itemId") int itemId,@RequestParam("userId") int userId, Model model) {

		// 商品情報をViewへ渡す
		model.addAttribute("items", itemRepository.findByOrderByInsertDateDescIdDesc());
		model.addAttribute("url", "/item/item_list/");
		
		// エンティティ生成
		Love2 love2 = new Love2();
		LoveKey2 key2 = new LoveKey2();
		
		// フォームの情報をコピー
		love2.setItemId(itemId);
		love2.setUserId(userId);
		key2.setItemId(itemId);
		key2.setUserId(userId);
		
		// 商品がお気に入り登録されているか判定
		if(loveRepository2.existsById(key2) == false) {
			
			// 現在日時の取得
			LocalDateTime time = LocalDateTime.now();
			java.sql.Date sqlDate = java.sql.Date.valueOf(time.toLocalDate());
			love2.setInsertDate(sqlDate);
			
			// データベースに登録
			loveRepository2.save(love2);
		}else {
			
			// データベースから削除
			loveRepository2.delete(love2);
		}
		
		return "item/list/item_list";
<<<<<<< HEAD
=======
=======
		// エンティティ内の検索結果をJavaBeansにコピー
		List<ItemBean> itemBeanList = BeanCopy.copyEntityToItemBean(itemList);

		// 商品情報をViewへ渡す
		model.addAttribute("items", itemBeanList);
		model.addAttribute("url", "/item/list/item_list/");

		return "item/list/item_list";	
	}
	
	@RequestMapping(path = "love/result",method = {RequestMethod.POST})
	public String showResult(Model model, LoveForm form ) {
		System.out.println("form.getInputSingleCheck()");
		return "love/result";
>>>>>>> 7a001baae3d13bebedc7bf3e9b73228e724c0923
>>>>>>> 0ba33a51e603ec4a9f076ca025239847fd1537af
	}
	
}

