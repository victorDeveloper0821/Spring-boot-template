package idv.victor.sideproject.product.controller;

import idv.victor.sideproject.enums.AppConfig;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * Product 業務邏輯 api
 */
@RestController
@RequestMapping(value = AppConfig.ApiPrefix + "products")
public class ProductController {

    /**
     * 以 productCode 搜尋產品
     *
     * @param productCode 產品代碼
     */
    @ResponseBody
    @GetMapping(value = "/{productCode}")
    public void getProduct(@PathVariable String productCode) {

    }

    /**
     * 建立 product
     */
    @ResponseBody
    @PostMapping(value = "")
    public void createProduct(HashMap<String, Objects> product) {

    }

    /**
     * 以 productCode 搜尋產品
     *
     * @param productCode 產品代碼
     */
    @ResponseBody
    @PatchMapping(value = "/{productCode}")
    public void updateProduct(@PathVariable String productCode) {

    }

    /**
     * 以 productCode 搜尋產品
     *
     * @param productCode 產品代碼
     */
    @ResponseBody
    @DeleteMapping(value = "/{productCode}")
    public void deleteProduct(@PathVariable String productCode) {

    }
}
