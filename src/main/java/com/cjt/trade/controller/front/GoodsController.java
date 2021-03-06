package com.cjt.trade.controller.front;

import com.cjt.trade.dto.PageInfoDto;
import com.cjt.trade.model.Goods;
import com.cjt.trade.model.Navigation;
import com.cjt.trade.model.PageInfo;
import com.cjt.trade.service.IGoodsService;
import com.cjt.trade.service.IPageInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caojiantao
 */
@Controller("goodsFrontCtl")
@RequestMapping("")
public class GoodsController extends BaseFrontController {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IPageInfoService pageInfoService;

    /**
     * 商品详情页
     */
    @RequestMapping(value = "/goods")
    public String goodsDetail(int id, Model model, HttpServletRequest request) {
        initFixModule(request, model);

        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goods", goods);

        int lastId = goodsService.getLastGoodsIdById(id);
        int nextId = goodsService.getNextGoodsIdById(id);
        model.addAttribute("lastId", lastId);
        model.addAttribute("nextId", nextId);

        PageInfoDto dto = new PageInfoDto();
        // 注意事项
        dto.setType(5);
        PageInfo attention = pageInfoService.getPageInfo(dto);
        model.addAttribute("attention", attention);
        // 返品返金
        dto.setType(6);
        PageInfo award = pageInfoService.getPageInfo(dto);
        model.addAttribute("award", award);
        // 营业时间
        dto.setType(7);
        PageInfo businessInfo = pageInfoService.getPageInfo(dto);
        model.addAttribute("businessInfo", businessInfo);
        // 配送时间
        dto.setType(8);
        PageInfo deliveryInfo = pageInfoService.getPageInfo(dto);
        model.addAttribute("deliveryInfo", deliveryInfo);
        // 相关商品
        List<Goods> similarGoods = goodsService.getSimilarGoodsById(goods);
        model.addAttribute("similarGoods", similarGoods);
        // 面包屑
        List<Navigation> navs = new ArrayList<Navigation>();
//        navs.add(new Navigation(goods.getTradeName(), "/list.action?tradeId=" + goods.getTradeId()));
//        navs.add(new Navigation(goods.getBrandName(), "/brand.action?id=" + goods.getBrandId()));
        navs.add(new Navigation(goods.getProductName(), "/product.action?id=" + goods.getProductId()));
        navs.add(new Navigation(goods.getName(), ""));
        model.addAttribute("navs", navs);
        return "front/build/detail";
    }
}
