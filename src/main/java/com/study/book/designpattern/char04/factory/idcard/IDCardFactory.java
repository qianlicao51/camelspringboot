package com.study.book.designpattern.char04.factory.idcard;

import com.study.book.designpattern.char04.factory.framework.Factory;
import com.study.book.designpattern.char04.factory.framework.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yd
 */
public class IDCardFactory extends Factory {
    private List owners = new ArrayList();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
}
