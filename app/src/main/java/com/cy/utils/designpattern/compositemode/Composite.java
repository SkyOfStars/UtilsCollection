package com.cy.utils.designpattern.compositemode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2020/1/17 0017.
 */
public class Composite implements Component {
    List<Component> childs = new ArrayList<Component>();

    public void add(Component child) {
        this.childs.add(child);
    }

    public void remove(Component child) {
        this.childs.remove(child);
    }

    public Component getChild(int i) {
        return this.childs.get(i);
    }

    public void doSomething() {
        //这里还包含迭代的思想，如果child是Leaf，则直接调用Leaf的doSomething方法，如果是Component的child，则此时的doSomething方法是Component
        //中的方法，相当于迭代循环
        for (Component child : childs)
            child.doSomething();
    }
}

