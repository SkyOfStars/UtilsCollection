package com.cy.utils.designpattern.intermediarymode;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 5. 应用场景
 * <p>
 * 在程序中，如果类的依赖关系过于复杂，呈现网状的结构，可以使用中介者模式对其进行解耦。
 * <p>
 * 6. 优点
 * <p>
 * 降低类的关系复杂度，将多对多转化成一对多，实现解耦。
 * 符合迪米特原则，依赖的类最少。
 * <p>
 * 7. 缺点
 * <p>
 * 同事类越多，中介者的逻辑就越复杂，会变得越难维护。
 * 如果本来类的依赖关系不复杂，但是使用了中介者会使原来不复杂的逻辑变得复杂。因此需要根据实际情况去考虑，不要滥用中介者。
 * <p>
 * 8. Android中的源码分析
 * <p>
 * Android中的锁屏功能就用到了中介者模式，KeyguardService（锁屏服务）通过KeyguardViewMediator（锁屏中介者）来协调各种Manager的状态以达到锁屏的功能。
 * 这里KeyguardService和各种Manager等等都充当了同事的角色。
 */
public class Test {
    public static void main(String[] args) {
        Lianjia houseMediator = new Lianjia();
        ConcretePerson.Purchaser purchaser = new ConcretePerson.Purchaser(houseMediator);
        ConcretePerson.Landlord landlord = new ConcretePerson.Landlord(houseMediator);
        houseMediator.setLandlord(landlord);
        houseMediator.setPurchaser(purchaser);

        landlord.send("出售一套别墅");
        System.out.println("------------------------");
        purchaser.send("求购一套学区房");
    }
}
