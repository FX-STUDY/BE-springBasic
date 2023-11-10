package repository;

import member.Product;

public interface ProductRepository {
    public Product save(Long memberId, Product product);


}
