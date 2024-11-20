package io.reflectoring.ProductService.Services;

import io.reflectoring.ProductService.Dto.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto){
        return null;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        return List.of();
    }

    public GenericProductDto updateProductById(Long id){
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long deleteProduct) {
        return null;
    }
}
