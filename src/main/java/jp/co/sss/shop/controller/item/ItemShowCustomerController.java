package jp.co.sss.shop.controller.item;

import java.util.List;

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
import jp.co.sss.shop.form.LoveForm;
import jp.co.sss.shop.repository.ItemRepository;
import jp.co.sss.shop.util.BeanCopy;

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

	
	/**
	 * トップ画面 表示処理
	 *
	 * @param model    Viewとの値受渡し
	 * @return "/" トップ画面へ
	 */
	@RequestMapping(path = "/")
	public String index(Model model) {

		
		return "index";
	}
	
	/**
	 * 商品一覧画面  ナビゲーションバー 新着順
	 *
	 * @param model    Viewとの値受渡し
	 * @return "item/list/item_list" 商品一覧画面へ
	 */
	@RequestMapping("/item/list/1")
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
	}
	
}
