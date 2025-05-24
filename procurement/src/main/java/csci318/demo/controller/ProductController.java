package csci318.demo.controller;

import csci318.demo.controller.dto.ProductDTO;
import csci318.demo.controller.dto.ProductDetailDTO;
import csci318.demo.model.Product;
import csci318.demo.model.ProductDetail;
import csci318.demo.service.ProductService;
import csci318.demo.model.productEvent.ProductDetailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        productService.createNewProduct(productDTO.getProductCategory(), productDTO.getName(), productDTO.getPrice(), productDTO.getStock());
        return new ResponseEntity<>("new product created --> category: " + productDTO.getProductCategory() + ", name: " + productDTO.getName() + ", price: " + productDTO.getPrice() + ", stock: " + productDTO.getStock(), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = productService.getById(id);
        if (product != null) {
            String oldName = product.getName();
            productService.updateProduct(id, productDTO.getProductCategory(), productDTO.getName(), productDTO.getPrice(), productDTO.getStock());
            return new ResponseEntity<>("updated product:" + oldName + " --> " + productDTO.getName() + "|" + "category: " + productDTO.getProductCategory() + ", stock: " + productDTO.getStock() + ", price: " + productDTO.getPrice(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    List<Product> allProductsAndDetails() {
        return productService.getAllProducts()
                .stream()
                .map(product -> {
                    Product product1 = new Product();
                    product1.setName(product.getName());
                    product1.setProductCategory(product.getProductCategory());
                    product1.setProductDetails(product.getProductDetails());
                    product1.setPrice(product.getPrice());
                    product1.setId(product.getId());
                    product1.setStock(product.getStock());
                    return product1;
                }).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    ResponseEntity getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return new ResponseEntity(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/stock")
    ResponseEntity<String> getProductStock(@PathVariable Long id) {
        Product product = productService.getById(id);
        productService.productStock(id);
        return new ResponseEntity<>("Product ID: " + product.getId() + ", Stock: " + product.getStock(), HttpStatus.OK);
    }

    @PostMapping("/product/{productId}/detail")
    ResponseEntity<String> createProductDetail(@PathVariable Long productId, @RequestBody ProductDetailDTO productDetailDTO) {
        Product product = productService.getById(productId);
        if (product.getProductDetails() != null) {
            return new ResponseEntity<>("Product " + product.getName() + "(" + product.getId() + ")" + " already has created details", HttpStatus.BAD_REQUEST);
        } else {
            ProductDetail productDetail = new ProductDetail(productId, productDetailDTO.getComment(), productDetailDTO.getDescription());
            product.setProductDetails(productDetail);
            productService.save(product);
            return new ResponseEntity<>("Product details created for " + product.getName() + "(" + product.getId() + ") | description: " + productDetailDTO.getDescription() + ", comment: " + productDetailDTO.getComment(), HttpStatus.OK);
        }
    }


    @PutMapping("/product/{productId}/detail")
    ResponseEntity<String> updateProductDetail(@PathVariable Long productId, @RequestBody ProductDetailDTO productDetailDTO) {
        Product product = productService.getById(productId);
        if (product.getProductDetails() != null) {
            productService.updateProductDetails(productId, productDetailDTO.getComment(), productDetailDTO.getDescription());
            return new ResponseEntity<>("Updated Product details --> description: " + productDetailDTO.getDescription() + ", comment: " + productDetailDTO.getComment(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product " + product.getName() + "(" + product.getId() + ")" + " does not have details to update", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{id}/detail")
    ResponseEntity getProductDetail(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product.getProductDetails() != null) {
            return new ResponseEntity(product, HttpStatus.OK);
        } else {
            return new ResponseEntity(product, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/sort")
    List<Product> sortByPriceEVENT() {
        ProductDetailEvent productDetailEvent = new ProductDetailEvent(this, "woogoo");
        List<Product> productList = allProductsAndDetails();
        return productDetailEvent.eventSortByPriceAsc(productList);
    }
}
