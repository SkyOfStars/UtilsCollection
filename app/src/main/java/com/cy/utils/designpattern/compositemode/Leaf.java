package com.cy.utils.designpattern.compositemode;

/**
 * created by cy on 2020/1/17 0017.
 */
public class Leaf implements  Component{
    @Override
    public void doSomething() {
        System.out.println("Leaf doSomething");
    }
}
