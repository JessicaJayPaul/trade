package com.cjt.trade.service;

import java.util.List;

import com.cjt.trade.dto.PageInfoDto;
import com.cjt.trade.model.PageInfo;
import com.cjt.trade.vo.PageInfoVo;

public interface IPageInfoService {

	int insertPageInfo(PageInfo pageInfo);

	/**
	 * 获取信息单页，或者是文章内容(根据type和id判断)
	 */
	PageInfo getPageInfo(PageInfoDto dto);

	int deletePageInfo(int id);

	List<PageInfoVo> getAllPageInfos(PageInfoDto dto);
	int getAllPageInfosCount(PageInfoDto dto);

	int updatePageInfo(PageInfo pageInfo);
}
