package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	@ResponseBody
//	@RequestBody接收json数据拼接成orderInfo对象
	public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo) {
		try {
			TaotaoResult result = orderService.createOrder(orderInfo);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
