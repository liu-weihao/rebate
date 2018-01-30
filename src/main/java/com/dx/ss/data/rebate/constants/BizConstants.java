package com.dx.ss.data.rebate.constants;

/**
 * <p class="detail">
 * 功能：业务相关的常量
 * </p>
 * @ClassName: BizConstants 
 * @version V1.0  
 * @date 2016年12月28日 
 * @author weihao.liu
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
public interface BizConstants {

	/**
	 * 默认运费，查询不到运费模板 || 查询不到目的地，使用此运费设置
	 */
	public static final double DEFAULT_EXPRESS_FEE = 100.00;
	
	/**
	 * 商品图片最大数量（含商品主图）
	 */
	public static final int MAX_GOODS_IMAGE_NUM = 5;
}
