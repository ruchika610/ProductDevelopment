package io.reflectoring.ProductService.ThirdPartyClients;

import io.reflectoring.ProductService.Dto.GenericProductDto;
import io.reflectoring.ProductService.Exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProduct();

    GenericProductDto updateProductById(Long id);

    GenericProductDto deleteProductById(Long deleteProduct);
}
