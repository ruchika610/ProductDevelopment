package io.reflectoring.ProductService.ThirdPartyClients;

import io.reflectoring.ProductService.Dto.GenericProductDto;
import io.reflectoring.ProductService.Exceptions.NotFoundException;



import java.util.List;

public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient{

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        return List.of();
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long deleteProduct) {
        return null;
    }
}
