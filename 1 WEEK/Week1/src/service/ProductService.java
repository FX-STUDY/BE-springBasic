package service;

import member.GradeType;
import member.Member;
import member.Product;
import repository.MemoryMemberRepository;
import repository.MemoryProductRepository;
import repository.ProductRepository;

public class ProductService {

    private static double vipDiscount = 0.3; //vip할인율

    private static final MemoryProductRepository memoryProductRepository = new MemoryProductRepository();
    public Long order(Long memberId, Product product) {

        //store한 member에 접근
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        Member member = memoryMemberRepository.findById(memberId);

        Long price = product.getPrice();
        Double discountedPrice = 0.0; //default


        if(member.getGrade().equals(GradeType.VIP)) {
            discountedPrice = price - price * vipDiscount;
        } else { //GradeType.NORMAL
            discountedPrice = (double)price;
        }
        product.setDiscountedPrice(discountedPrice);

        memoryProductRepository.save(memberId, product);
        return product.getId();
    }


}
