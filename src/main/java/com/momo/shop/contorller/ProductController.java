
package com.momo.shop.contorller;

import com.momo.shop.model.ProductVO;
import com.momo.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product/main")
    public String product(Model model) {
        model.addAttribute("product",this.productService.productMain());
        return "/product/productMain";
    }


    //상품검색(카테고리)
    @ResponseBody
    @GetMapping("/product/searchProductAll")
    public List<ProductVO> searchProductAll(ProductVO productVO,
                                            @RequestParam("c_no[]") List c_no,
                                            Model model){
        log.debug(String.valueOf(c_no));
        List<ProductVO> product = this.productService.productSearchAll(c_no);

        return product;

    }
    //상품검색(세부)
    @ResponseBody
    @GetMapping("/product/searchProduct")
    public List<ProductVO> searchProduct(ProductVO productVO,
                                         @RequestParam("c_no") Integer c_no
                                   ){
        log.debug(String.valueOf(c_no));
        List<ProductVO> product = this.productService.productSearch(c_no);
        return product;
    }

    @PostMapping("/admin/productList")
    public String productlistPost(){
        return "admin/productList";
    }


    //카테고리별 상품 리스트 출력
    @ResponseBody
    @PostMapping("/admin/productList/{category_no}")
    public Map<String,Object> productList(Model model, @PathVariable(value="category_no") String category_no,  @RequestParam(value="currentPage") int currentPage){
        log.debug("******************************************");
        log.debug(String.valueOf(currentPage));

        List c_noList = Arrays.asList(category_no.split(","));
        log.debug(String.valueOf(c_noList));
//        model.addAllAttributes(this.productService.selectProductList(c_noList,currentPage));

//        List<ProductVO> List = this.productService.productSearchAll(c_noList);
        return this.productService.selectProductList(c_noList,currentPage);
    };

    //상품 상세정보 출력
    @ResponseBody
    @PostMapping("/admin/product/{p_id}")
    public Map<String, Object> SelectProduct(Model model, @PathVariable int p_id){
        Map<String, Object> map= this.productService.selectProduct(p_id);
        return map;
    }




    @GetMapping("/product/Detail/{p_id}")
    public String productDetail(Model model, @PathVariable Integer p_id){

        Map<String, Object> map = new HashMap<>();
        map = this.productService.productDetail(p_id);

        log.debug(String.valueOf(map));
        model.addAttribute("map",map);

        return "product/productDetail";

    }

    @ResponseBody
    @GetMapping("/product/Detail/poamount/{po_id}")
    public Map<String ,Object> poamount(Model model, @PathVariable Integer po_id){
        Map<String, Object> map = new HashMap<>();
        map = this.productService.poamount(po_id);
        log.debug(String.valueOf(map));
        return map;
    }

}
