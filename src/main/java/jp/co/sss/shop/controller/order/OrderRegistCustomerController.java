package jp.co.sss.shop.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.bean.BasketBean;
import jp.co.sss.shop.bean.OrderBean;
import jp.co.sss.shop.bean.OrderItemBean;
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.entity.Order;
import jp.co.sss.shop.entity.OrderItem;
import jp.co.sss.shop.entity.User;
import jp.co.sss.shop.form.OrderForm;
import jp.co.sss.shop.repository.ItemRepository;
import jp.co.sss.shop.repository.OrderItemRepository;
import jp.co.sss.shop.repository.OrderRepository;
import jp.co.sss.shop.repository.UserRepository;

/**
 * 注文管理 登録機能のコントローラクラス
 * @author 出井, 大手, 佐々木, 仲神
 */
@Controller
public class OrderRegistCustomerController {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 届け先入力処理
	 * @param backflg
	 * @param model
	 * @param form
	 * @return "order/regist/order_address_input"
	 */
	@RequestMapping(path = "/address/input", method = RequestMethod.POST)
	public String backFlg(boolean backFlg, Model model, @ModelAttribute OrderForm form) {
		//データベースの値を表示
		if(!backFlg) {
			
			//会員情報を取得
			UserBean userBean = (UserBean) session.getAttribute("user");
			User user = userRepository.getById(userBean.getId());
			
			// 会員情報をViewに渡す
			model.addAttribute("user", user);
			
		//入力値を表示	
		} else {
			
			OrderBean orderBean = new OrderBean();
			// 入力値を会員情報にコピー
			BeanUtils.copyProperties(form, orderBean);

			// 会員情報をViewに渡す
			model.addAttribute("user", orderBean);

		}
		return "order/regist/order_address_input";
	}	
	
	@RequestMapping(path = "/address/input", method = RequestMethod.GET)
	public String inputAddressRedirect(Model model, @ModelAttribute OrderForm form) {	
		//入力値を表示	

		OrderBean orderBean = new OrderBean();
		// 入力値を会員情報にコピー
		BeanUtils.copyProperties(form, orderBean);

		// 会員情報をViewに渡す
		model.addAttribute("user", orderBean);

		return "order/regist/order_address_input";
	}

	/**
	 * 支払い情報選択画面
	 *
	 * @param form 注文情報入力フォーム
	 * @return "/order/regist/order_complete" 注文情報 登録完了画面へ
	 */
	@RequestMapping(path = "/payment/input", method = RequestMethod.POST)
	public String inputPayment(boolean backFlg, Model model, @Valid @ModelAttribute OrderForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			// 入力値に誤りがあった場合
			return inputAddressRedirect(model,form);
		}
		//注文情報の生成
		OrderBean orderBean = new OrderBean();

		// 入力値を注文情報にコピー
		BeanUtils.copyProperties(form, orderBean);

		// 会員情報をViewに渡す
		model.addAttribute("user", orderBean);
		
		return "order/regist/order_payment_input";
	}
	
	/**
	 * 注文登録確認(在庫チェック、注文商品情報を生成)
	 * @param form
	 * @return "/order/regist/order_check" 注文登録確認画面へ
	 */
	@RequestMapping(path = "/order/check", method = RequestMethod.POST)
	public String checkOrder(Model model, @ModelAttribute OrderForm form) {
		
		//商品情報
		Item item = new Item();
		//買い物かご情報
		BasketBean basketBean = new BasketBean();
		//注文情報
		OrderBean orderBean = new OrderBean();
		
		//買い物かごリスト
		List<BasketBean> basketList = (List<BasketBean>) session.getAttribute("baskets");
		//注文商品情報リスト
		List<OrderItemBean> orderItemBeanList = new ArrayList<OrderItemBean>();

		//合計金額を算出
		int total = 0;
		//在庫の有無を判別するフラグを生成(true：在庫0, false：在庫あり)
		boolean orderFlg = false;

		//リストの要素数分の繰り返し
		for (int i = basketList.size()-1; i >= 0; i--) {
			//注文商品情報
			OrderItemBean orderItemBean = new OrderItemBean();
			//買い物かご情報を取得
			basketBean = basketList.get(i);
			//該当する商品情報を取得
			item = itemRepository.getById(basketBean.getId());
			
			//注文商品情報を変更
			orderItemBean.setId(item.getId());
			orderItemBean.setName(item.getName());
			orderItemBean.setImage(item.getImage());
			orderItemBean.setPrice(item.getPrice());
			orderItemBean.setOrderNum(basketBean.getOrderNum());
			
			//注文数が在庫数より多い場合
			if (item.getStock() < basketBean.getOrderNum()) {
				//該当商品の注文商品数を在庫数に変更
				orderItemBean.setOrderNum(item.getStock());
				//買い物かご情報の在庫数を更新
				basketBean.setStock(item.getStock());
				//買い物かごリストを更新
				basketList.set(i, basketBean);
				//セッションに買い物かごリストを保存
				session.setAttribute("baskets", basketList);
			}
			
			//在庫が0の場合
			if (item.getStock() == 0) {
				orderFlg = true;
			}

			//小計金額を算出
			orderItemBean.setSubtotal(item.getPrice() * basketBean.getOrderNum());
			//注文商品リストに追加
			orderItemBeanList.add(0, orderItemBean);
			
			//在庫が0の場合
			if (orderFlg) {
				//注文商品リストから削除
				orderItemBeanList.remove(0);
			}
			
			//注文商品リストに何か入っている場合
			if (!orderItemBeanList.isEmpty()) {
				//注文商品情報を取得
				orderItemBean = orderItemBeanList.get(0);
				
				//合計金額を算出
				total += orderItemBean.getSubtotal();
			}
		}
		//注文商品情報をセッションに保存
		session.setAttribute("orderItem", orderItemBeanList);
		
		//入力値を注文情報にコピー
		BeanUtils.copyProperties(form, orderBean);
		//注文情報をviewに渡す
		model.addAttribute("order", orderBean);
		
		//合計金額をviewに渡す
		model.addAttribute("total", total);
		
		return "order/regist/order_check";
	}
	
	/**
	 * 注文登録完了処理(注文情報、注文商品情報をDBに登録)
	 * @param form 注文情報
	 * @return "/order/regist/order_complete" 注文情報 登録完了画面へ
	 */
	@RequestMapping(path = "/order/complete", method = RequestMethod.POST)
	public String completeOrder(Model model, @ModelAttribute OrderForm form) {
		
		//注文情報
		Order order = new Order();
		//書品情報
		Item item = new Item();
		//入力値を注文情報にコピー
		BeanUtils.copyProperties(form, order);
		
		//会員情報を取得
		UserBean userBean = (UserBean) session.getAttribute("user");
		User user = userRepository.getById(userBean.getId());
		
		//注文情報を変更
		order.setUser(user);
		//注文情報をデータベースに登録
		orderRepository.save(order);
				
		//注文商品情報
		OrderItemBean orderItemBean = new OrderItemBean();
		//注文商品情報リストを生成
		List<OrderItemBean> orderItemList = 
			(List<OrderItemBean>) session.getAttribute("orderItem");
		
		//リストの要素数分の繰り返し
		for (int i = 0; i < orderItemList.size(); i++) {
			
			//注文商品情報
			OrderItem orderItem = new OrderItem();		
			//注文商品情報を取得
			orderItemBean = orderItemList.get(i);
			
			//商品情報を取得
			item = itemRepository.getById(orderItemBean.getId());
			//在庫数から注文数分減らす
			item.setStock(item.getStock()-orderItemBean.getOrderNum());
			
			//注文商品情報を変更
			orderItem.setQuantity(orderItemBean.getOrderNum());
			orderItem.setOrder(orderRepository.findTop1ByOrderByInsertDateDesc());
			orderItem.setItem(itemRepository.getById(orderItemBean.getId()));
			orderItem.setPrice(orderItemBean.getPrice());
			
			//注文商品情報をデータベースに登録
			orderItemRepository.save(orderItem);
			//商品情報をデータベースに登録
			itemRepository.save(item);
		}
		
		//セッションを破棄
		session.removeAttribute("baskets");
		session.removeAttribute("orderItem");
		
		return "redirect:/order/complete";
	}
	
	/**
	 * 注文登録完了画面表示
	 *
	 * @return "order/regist/order_complete" 注文登録完了画面へ
	 */
	@RequestMapping(path = "/order/complete", method = RequestMethod.GET)
	public String completeOrderRedirect() {
		return "order/regist/order_complete";
	}
}
