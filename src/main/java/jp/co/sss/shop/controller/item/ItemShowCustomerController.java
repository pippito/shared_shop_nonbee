package jp.co.sss.shop.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.repository.ItemRepository;

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
	
//	++++++++++++++++++++以下改修++++++++++++++++++++	確認後に削除してください。
	
	/**
	 * 商品一覧画面
	 * テンプレートパス（/item/list）
	 * ファイル名（item_list.html）
	 * Naoto Shibata
	 */
	
	@GetMapping("item/list/item_list")
	public String showItemCustomer(Model model) {
		// 商品情報を全件検索
		List<Item> dat = itemRepository.findAll();

		// 商品情報（リクエスト）を渡す
		model.addAttribute("Items", dat);
		return "/";
	}
}
