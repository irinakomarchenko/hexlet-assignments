package exercise.mapper;


import exercise.model.Product;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;

// BEGIN
public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getName());
        dto.setPrice(product.getCost());
        dto.setVendorCode(product.getBarcode());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static  Product toEntity(ProductCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setName(dto.getTitle());
        product.setCost(dto.getPrice());
        product.setBarcode(dto.getVendorCode());

        return product;
    }
        public static void updateEntity(ProductUpdateDTO dto, Product product) {
            if (dto == null || product == null) {
                return;
            }

            product.setCost(dto.getPrice());
        }

}
// END
