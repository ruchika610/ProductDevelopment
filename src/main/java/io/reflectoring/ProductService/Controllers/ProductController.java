package io.reflectoring.ProductService.Controllers;

import io.reflectoring.ProductService.Dto.ExceptionDto;
import io.reflectoring.ProductService.Dto.GenericProductDto;
import io.reflectoring.ProductService.Exceptions.NotFoundException;
import io.reflectoring.ProductService.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
// /api/v1/products/ or below one end point name
@RequestMapping("/products")
public class ProductController {

    //@Autowired // field injection ----not recommended
    private ProductService productService;
    //constructor injection  --- recommended
    public  ProductController (@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    //setter injection ----not recommended
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        ExceptionDto exceptionDto = new ExceptionDto("NOT_FOUND",
//                notFoundException.getMessage());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
        return new ResponseEntity(new ExceptionDto( HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping()
    public List<GenericProductDto> getAllProduct(){
        return productService.getAllProduct();
    }
    //localhost:8080/products/123 // this is the place where resources are located. to get that particular resource,
    // Configuration are ideally the part of request params
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id ){
        //set server status code by your own
        return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.OK
        );

    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){

        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id){
        return productService.updateProductById(id);
    }

}
