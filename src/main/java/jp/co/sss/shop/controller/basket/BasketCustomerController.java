package jp.co.sss.shop.controller.basket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.bean.BasketBean;
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.form.BasketForm;
import jp.co.sss.shop.repository.ItemRepository;

/**
 * ログイン機能のコントローラクラス
 *
 * @author 出井亮也、大手瑞稀、佐々木友希、仲神颯希
 */
@Controller
public class BasketCustomerController {

	/**
	 * 商品情報
	 */
	@Autowired
	ItemRepository itemRepository;

	/**
	 * セッション情報
	 */
	@Autowired
	HttpSession session;

	/**
	 * 買い物かご追加
	 *@param form 買い物かごのフォーム
	 *@return 
			非会員の場合 "login" ログイン画面へ
			一般会員の場合 "forward:/basket/list" 買い物かご一覧へ
	 *@author 出井亮也・大手瑞稀
	 */
	@RequestMapping(path = "/basket/add", method = RequestMethod.POST)
	public String addItem(Model model, @ModelAttribute BasketForm form){

		// 非会員であればログイン画面に遷移
		Integer authority = ((UserBean) session.getAttribute("user")).getAuthority();
		if (authority.intValue() != 2) {
			return "login";
		}

		//入れたい商品情報を取得
		Item item = itemRepository.getById(form.getId());	
		//買い物かご情報
		BasketBean basketBean= new BasketBean();
		//買い物かごリスト
		List<BasketBean> basketList;
		//入れたい商品の情報がリストにあるか確認するための変数(true：無い、false：ある)
		boolean basketFlag = false;
		//セッションの中身が無ければ
		if (session.getAttribute("baskets") == null) {

			//買い物かごリストを生成
			basketList = new ArrayList<>();
			//商品情報を買い物かご情報に入れる
			basketBean = new BasketBean(item.getId(),item.getName(),item.getStock());
			//買い物かごリストに入れたい商品を追加
			basketList.add(0, basketBean);
			//セッションに買い物かごリストを保存
			session.setAttribute("baskets", basketList);
		}else {
			//セッションに入ってる買い物かごリストを取得
			basketList = (List<BasketBean>) session.getAttribute("baskets");

			//リストの要素数分の繰り返し
			for (int i = 0; i < basketList.size(); i++) {

				//リストの中に入れたい商品があれば
				if (basketList.get(i).getId() == form.getId()) {
					//リストのインデックス番号がiの買い物かご情報を取得
					basketBean = basketList.get(i);
					//入れたい商品の情報がリストにある
					basketFlag = false;

					//注文数が在庫を超えない限り
					if (basketBean.getOrderNum() < basketBean.getStock()) {
						//注文数を1増やす
						basketBean.setOrderNum(basketBean.getOrderNum() + 1);
						//商品の注文数を更新
						basketList.set(i, basketBean);
					} else {
					//モデルに買い物かご情報を保存
					model.addAttribute("basket", basketBean);
					}
					break;
				}else {
					//入れたい商品の情報がリストに無い
					basketFlag = true;
				}
			}

			//もし入れたい商品と同じ商品が無ければ
			if (basketFlag){
				//買い物かごに入れたい商品を生成
				basketBean = new BasketBean(item.getId(),item.getName(),item.getStock());
				//リストに商品を追加
				basketList.add(0, basketBean);	
			}
		}
		//セッションに買い物かごリストを保存
		session.setAttribute("baskets", basketList);

		return "redirect:/basket/list";
	}


	/**
	 * 会員詳細表示処理
	 *
	 * @param form
	 * @return "/basket/shopping_basket" 商品一覧表示画面へ
	 * @author 仲神颯希
	 */
	@RequestMapping(path = "/basket/list", method = RequestMethod.POST)
	public String basketList(@ModelAttribute BasketForm form) {

		return "basket/shopping_basket";
	}

	/**
	 * 会員詳細表示処理
	 *
	 * @param form
	 * @return "/basket/shopping_basket" 商品一覧表示画面へ
	 * @author 仲神颯希
	 */
	@RequestMapping(path = "/basket/list", method = RequestMethod.GET)
	public String basketListGet() {

		//セッションが空の場合
		if (session.getAttribute("baskets") == null) {
			
			return "basket/shopping_basket";
		}
		
		//商品情報
		Item item = new Item();
		//買い物かご情報
		BasketBean basketBean = new BasketBean();

		//買い物かごリスト
		List<BasketBean> basketList = (List<BasketBean>) session.getAttribute("baskets");

		//リストの要素数分の繰り返し
		for (int i = basketList.size()-1; i >= 0; i--) {
			
			basketBean = basketList.get(i);
			//該当する商品情報を取得
			item = itemRepository.getById(basketBean.getId());

			//在庫が0の場合
			if (item.getStock() == 0) {
				//リストから該当商品を削除
				basketList.remove(i);
				
			//注文数が在庫数より多い場合
			} else if (item.getStock() < basketBean.getOrderNum()) {
				//該当商品の注文商品数を在庫数に変更
				basketBean.setOrderNum(item.getStock());
				//買い物かごリストを更新
				basketList.set(i, basketBean);
				
			}			
		}
		
		//セッションに買い物かごリストを保存
		session.setAttribute("baskets", basketList);
		
		return "basket/shopping_basket";
	}



	/**
	 * 買い物かごの商品を削除
	 * @param form 買い物かごのフォーム
	 * @return "/basket/shopping_basket"買い物かご一覧へ
	 * @author 佐々木友希
	 */
	@RequestMapping(path = "/basket/delete", method = RequestMethod.POST)
	public String basketDelete(@ModelAttribute BasketForm form) {
		//買い物かご情報を生成
		BasketBean basket = new BasketBean();
		//セッションに入ってる買い物かごリストを取得
		List<BasketBean> basketList = (List<BasketBean>) session.getAttribute("baskets");
		//リストの要素数分の繰り返し
		for (int i = 0; i < basketList.size(); i++) {
			//リストの中に該当商品があれば
			if (basketList.get(i).getId() == form.getId()) {
				//リストのインデックス番号がiの買い物かご情報を取得
				basket = basketList.get(i);

				//注文数が1のとき
				if (basket.getOrderNum()==1) {
					//リストから該当商品を削除
					basketList.remove(i);
					
					//買い物かごリストが空の場合
					if (basketList.isEmpty()) {
						//セッションを破棄
						session.removeAttribute("baskets");
						break;
					}
					
				}else {
					//選択した商品の注文数を1減らす
					basket.setOrderNum(basket.getOrderNum()-1);
					//買い物かごリストを更新
					basketList.set(i, basket);
				}
				//セッションに買い物かごリストを保存
				session.setAttribute("baskets", basketList);
			}
		}
		

		return "redirect:/basket/list";
	}


	/**
	 * 買い物かごを一括削除(買い物かごを空にするボタンが押されたとき)
	 * @return "/basket/shopping_basket"買い物かご一覧へ
	 */
	@RequestMapping(path = "/basket/allDelete", method = RequestMethod.POST)
	public String basketAllDelete() {

		//セッションを破棄
		session.removeAttribute("baskets");

		return "redirect:/basket/list";
	}

}
