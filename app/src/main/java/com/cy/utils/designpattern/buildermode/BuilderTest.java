package com.cy.utils.designpattern.buildermode;

/**
 * created by cy on 2019/11/27 0027.
 * 建造者模式Test
 * <p>
 * a.应用场景
 * <p>
 * 创建一些复杂的对象时,对象内部的构建过程存在复杂变化。
 * 相同的构建过程，不同的执行顺序，产生不同结果时。
 * 不同配置的构建对象，产生不同结果时。
 * <p>
 * b.优点
 * <p>
 * 封装性良好，隐藏内部构建细节。
 * 易于解耦，将产品本身与产品创建过程进行解耦，可以使用相同的创建过程来得到不同的产品。也就说细节依赖抽象。
 * 易于扩展，具体的建造者类之间相互独立，增加新的具体建造者无需修改原有类库的代码。
 * 易于精确控制对象的创建，由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。
 * <p>
 * c.缺点
 * <p>
 * 产生多余的Build对象以及Dirextor类。
 * 建造者模式所创建的产品一般具有较多的共同点，其组成部分相似；如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
 * 如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大。
 * <p>
 * Android中的源码分析
 *
 * <p>
 * Android中的AlertDialog.Builder就是使用了Builder模式来构建AlertDialog的。
 */
public class BuilderTest {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();//创建建造者实例，（装机人员）
        Director direcror = new Director(builder);//创建指挥者实例，并分配相应的建造者，（老板分配任务）
        direcror.Construct("i7-6700", "三星DDR4", "希捷1T");//组装电脑
    }
}
