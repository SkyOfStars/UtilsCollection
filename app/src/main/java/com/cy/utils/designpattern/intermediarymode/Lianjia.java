package com.cy.utils.designpattern.intermediarymode;

/**
 * created by cy on 2019/12/5 0005.
 */
public class Lianjia implements HouseMediator {//链家，实现HouseMediator
    ConcretePerson.Purchaser mPurchaser;
    ConcretePerson.Landlord mLandlord;

    public void setPurchaser(ConcretePerson.Purchaser purchaser) {//设置买房者
        mPurchaser = purchaser;
    }

    public void setLandlord(ConcretePerson.Landlord landlord) {//设置房东
        mLandlord = landlord;
    }


    @Override
    public void notice(Person person, String message) {//发送通知
        System.out.println("中介收到信息，并转发给相应的目标人群");
        if (person == mPurchaser) {
            mLandlord.getNotice(message);
        } else if (person == mLandlord) {
            mPurchaser.getNotice(message);
        }
    }
}
