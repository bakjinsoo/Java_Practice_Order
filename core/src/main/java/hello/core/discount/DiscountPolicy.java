package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param meber
     * @param price
     * @return
     */
    int discount(Member meber, int price);
}
