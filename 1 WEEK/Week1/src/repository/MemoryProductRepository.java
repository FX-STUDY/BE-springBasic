package repository;

import member.GradeType;
import member.Member;
import member.Product;


import java.util.concurrent.ConcurrentHashMap;

public class MemoryProductRepository implements ProductRepository{

    //memberId, product
    private static  ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
    private static long incrementId = 0L;



    //키 값으로 멤버의 고유 id 저장
    public Product save(Long memberId, Product product) {
        product.setId(++incrementId);


        store.put(memberId, product);
        return product;
    }


}
