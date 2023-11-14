package SEOB.SEOB.repository;


import SEOB.SEOB.domain.Product;

public interface ProductRepository {
    public Product save(Long memberId, Product product);


}
