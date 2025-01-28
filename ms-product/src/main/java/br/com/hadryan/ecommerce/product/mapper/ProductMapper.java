package br.com.hadryan.ecommerce.product.mapper;

import br.com.hadryan.ecommerce.product.mapper.request.ProductRequest;
import br.com.hadryan.ecommerce.product.mapper.response.ProductPurchaseResponse;
import br.com.hadryan.ecommerce.product.mapper.response.ProductResponse;
import br.com.hadryan.ecommerce.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product requestToModel(ProductRequest request);

    @Mapping(target = "category", source = "category.name")
    ProductResponse modelToResponse(Product product);

    @Mapping(target = "productId", source = "product.id")
    ProductPurchaseResponse modelToPurchaseResponse(Product product, int quantity);

}
