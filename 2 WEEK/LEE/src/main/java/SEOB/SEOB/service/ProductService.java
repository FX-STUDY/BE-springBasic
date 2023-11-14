package SEOB.SEOB.service;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;
import SEOB.SEOB.domain.Product;
import SEOB.SEOB.repository.MemberRepository;
import SEOB.SEOB.repository.MemoryMemberRepository;
import SEOB.SEOB.repository.MemoryProductRepository;
import SEOB.SEOB.repository.ProductRepository;


public class ProductService {

    private static final double vipDiscount = 0.3; //vip할인율

    private final ProductRepository productRepository = new MemoryProductRepository();
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long order(Long memberId, Product product) {

        //store한 member에 접근
        Member member = memberRepository.findById(memberId);

        Long price = product.getPrice();
        Double discountedPrice = 0.0; //default


        if(member.getGrade().equals(GradeType.VIP)) {
            discountedPrice = price - (price * vipDiscount);
        } else { //GradeType.NORMAL
            discountedPrice = (double)price;
        }
        product.setDiscountedPrice(discountedPrice);

        productRepository.save(memberId, product);
        return product.getId();
    }


}
