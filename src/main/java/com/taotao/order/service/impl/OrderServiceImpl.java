package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.component.JedisClient;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ORDER_GEN_KEY}")
	private String REDIS_ORDER_GEN_KEY;

	@Value("${ORDER_ID_BEGIN}")
	private String ORDER_ID_BEGIN;

	@Value("${REDIS_ORDER_DETAIL_GEN_KEY}")
	private String REDIS_ORDER_DETAIL_GEN_KEY;

	@Override
	public TaotaoResult createOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		// 插入订单表
		// 生成订单号
		String id = jedisClient.get(REDIS_ORDER_GEN_KEY);
		if (StringUtils.isBlank(id)) {
			// 不存在，设置初始值
			jedisClient.set(REDIS_ORDER_GEN_KEY, ORDER_ID_BEGIN);
		}
		Long orderId = jedisClient.incr(REDIS_ORDER_GEN_KEY);
		// 一、生成订单表
		// 补全字段
		orderInfo.setOrderId(orderId.toString());
		// 设置订单状态 ： 1、未付款；2、已付款；3、未发货；4、已发货；5、交易成功；6、交易关闭
		orderInfo.setStatus(1);
		Date date = new Date();
		orderInfo.setCreateTime(date);
		orderInfo.setUpdateTime(date);
		orderMapper.insert(orderInfo);
		// 二、生成订单明细表
		List<TbOrderItem> itemList = orderInfo.getOrderItems();
		for (TbOrderItem item : itemList) {
			// 生成订单明细id，使用redis的incr命令
			Long detailId = jedisClient.incr(REDIS_ORDER_DETAIL_GEN_KEY);
			item.setId(detailId.toString());
			// 填入订单号
			item.setOrderId(orderId.toString());
			// 插入数据
			orderItemMapper.insert(item);
		}
		// 三、生成物流表
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId.toString());
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		// 插入数据
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

}
