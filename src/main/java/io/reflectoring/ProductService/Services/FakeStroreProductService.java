package io.reflectoring.ProductService.Services;

import io.reflectoring.ProductService.Dto.FakeStroreProductDto;
import io.reflectoring.ProductService.Dto.GenericProductDto;
import io.reflectoring.ProductService.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Service("fakeStoreProductService")
public class FakeStroreProductService implements ProductService{

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;


    //to call a 3rd party api
    private RestTemplateBuilder restTemplateBuider ;
    private String productRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"{/id}";
    private String createProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;

    public FakeStroreProductService (RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuider = restTemplateBuilder;
    }
    private  GenericProductDto fakeStoreToGenericDto(FakeStroreProductDto fakeStroreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStroreProductDto.getId());
        product.setImage(fakeStroreProductDto.getImage());
        product.setDescription(fakeStroreProductDto.getDescription());
        product.setTitle(fakeStroreProductDto.getTitle());
        product.setPrice(fakeStroreProductDto.getPrice());
        product.setCategory(fakeStroreProductDto.getCategory());
        return product;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuider.build();
        //body is product
        ResponseEntity<GenericProductDto> response =
                restTemplate.postForEntity(createProductRequestUrl, product,GenericProductDto.class);
                return response.getBody();
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuider.build();
        ResponseEntity<FakeStroreProductDto> responseEntity =
                    restTemplate.getForEntity(productRequestUrl, FakeStroreProductDto.class, id);
        FakeStroreProductDto fakeStroreProductDto = responseEntity.getBody();
        if(fakeStroreProductDto == null){
            throw new NotFoundException("Product with id: "+id+" not found");
        }
        responseEntity.getStatusCode();
        return fakeStoreToGenericDto(fakeStroreProductDto);

    }
    @Override
    public List<GenericProductDto> getAllProduct(){
        RestTemplate restTemplate = restTemplateBuider.build();
        ResponseEntity<FakeStroreProductDto[]> response = restTemplate.getForEntity(createProductRequestUrl, FakeStroreProductDto[].class);

        List<GenericProductDto> answer = new ArrayList<>();
        for(FakeStroreProductDto fakeStroreProductDto : response.getBody()) {
            answer.add(fakeStoreToGenericDto(fakeStroreProductDto));
        }

        return answer;
    }
    @Override
    public GenericProductDto updateProductById(Long id){
        RestTemplate restTemplate = restTemplateBuider.build();
                //exchange or execute method --> for doing custom responses(code), request callback then you can use these

        //using excute method
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestUrl, FakeStroreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStroreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStroreProductDto.class);
        ResponseEntity<FakeStroreProductDto> response =
                restTemplate.execute(productRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStroreProductDto fakeStroreProductDto = response.getBody();
        return fakeStoreToGenericDto(fakeStroreProductDto);

    }
    //you can send status code as fakestrore sending data before deleting
    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuider.build();
        //you can also use execute method
        // Retrieve the product before deletion
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStroreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStroreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStroreProductDto.class);
        ResponseEntity<FakeStroreProductDto> response =
                restTemplate.execute(productRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStroreProductDto fakeStroreProductDto = response.getBody();
        return fakeStoreToGenericDto(fakeStroreProductDto);

         // Return the deleted product details); // Return 200 OK with the deleted product details
    }
}
