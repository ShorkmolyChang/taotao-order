package com.taotao.order.pojo;

import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder {

	// 订单中的商品，1对多
	private List<TbOrderItem> orderItems;
	// 订单物流
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
