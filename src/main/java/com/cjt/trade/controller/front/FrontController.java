package com.cjt.trade.controller.front;

import com.cjt.trade.dto.GoodsDto;
import com.cjt.trade.dto.OrderDto;
import com.cjt.trade.model.*;
import com.cjt.trade.service.ICategoryService;
import com.cjt.trade.service.IDictionaryService;
import com.cjt.trade.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author caojiantao
 */
@Controller
@RequestMapping("")
public class FrontController extends BaseFrontController {

    @Resource
    private ICategoryService categoryService;
    @Resource
    private IDictionaryService dictionaryService;

    @Resource
    private IOrderService orderService;

    /**
     * 品牌类型
     */
    @RequestMapping(value = "/brand")
    public String brand(int id, Model model, HttpServletRequest request) {
        initFixModule(request, model);

        // 品牌信息
        Brand brand = categoryService.getBrandById(id);
        model.addAttribute("brand", brand);

        // 面包屑导航
        List<Navigation> navs = new ArrayList<Navigation>();
        navs.add(new Navigation(brand.getTradeName(), "/list.action?tradeId=" + brand.getTradeId()));
        navs.add(new Navigation(brand.getName(), ""));
        model.addAttribute("navs", navs);

        // 当前brand“品牌类型”所有product“产品类型”
        List<MapModel> maps = categoryService.listProductsOptByParentId(id);
        model.addAttribute("maps", maps);

        return "front/build/brand";
    }

    /**
     * 产品类型
     */
    @RequestMapping(value = "/product")
    public String product(int id, Model model, HttpServletRequest request) {
        initFixModule(request, model);

        // 产品信息
        Product product = categoryService.getProductById(id);
        model.addAttribute("product", product);

        List<Navigation> navs = new ArrayList<Navigation>();
        navs.add(new Navigation(product.getTradeName(), "/list.action?tradeId=" + product.getTradeId()));
        navs.add(new Navigation(product.getBrandName(), "/brand.action?id=" + product.getBrandId()));
        navs.add(new Navigation(product.getName(), ""));
        model.addAttribute("navs", navs);

        // 当前brand“品牌类型”所有product“产品类型”
        List<MapModel> maps = categoryService.listProductsOptByParentId(product.getBrandId());
        model.addAttribute("maps", maps);
        return "front/build/product";
    }

    /**
     * 商品列表
     */
    @RequestMapping(value = "/list")
    public String list(GoodsDto dto, Model model, HttpServletRequest request) {
        initFixModule(request, model);

        List<Navigation> navs = new ArrayList<Navigation>();
        if (dto.getTradeId() == 0) {
            navs.add(new Navigation("商品一览", ""));
        } else if (!StringUtils.isEmpty(dto.getKeyword())) {
            navs.add(new Navigation("检索", ""));
        } else {
            // 商品行业热卖 trade
            Trade trade = categoryService.getTradeById(dto.getTradeId());
            navs.add(new Navigation(trade.getName(), ""));
        }
        model.addAttribute("navs", navs);
        model.addAttribute("dto", dto);

        return "/front/build/list";
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, Model model) {
        initFixModule(request, model);
        return "/front/build/register";
    }

    @RequestMapping(value = "/user")
    public String user(HttpServletRequest request, Model model) {
        initFixModule(request, model);
        return "/front/build/user";
    }

    @RequestMapping(value = "/userMange")
    public String userManger(HttpServletRequest request, Model model){
        initFixModule(request, model);
        // 县郡
        Map<String, String> countiesMap = dictionaryService.getOptsBySetId(1);
        model.addAttribute("countiesMap", countiesMap);
        return "/front/build/userMange";
    }



    @RequestMapping(value = "/myOrder")
    public String myOrder(Model model, HttpSession session, OrderDto orderDto, HttpServletRequest request){
        Integer id = (Integer) session.getAttribute("id");

        if(orderDto.getRows() == null){
            orderDto.setRows(10);
        }
        if(orderDto.getPage() == null){
            orderDto.setPage(1);
        }
        orderDto.setUserId(String.valueOf(id));
        List<Order> orders = orderService.getMyOrders(orderDto);

        orderDto.setTotal(orderService.getMyOrdersCount(orderDto));
        model.addAttribute("page", orderDto);
        model.addAttribute("orders", orders);
        return "/front/build/myOrder";
    }
}
