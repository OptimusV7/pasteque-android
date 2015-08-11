/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pasteque.client.data;

import android.content.Context;
import fr.pasteque.client.models.Discount;
import fr.pasteque.client.utils.exception.NotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and Load discounts in FILENAME Data can be accessed anywhere, anytime.
 *
 * @author nsvir
 */
public class DiscountData extends AbstractDataSavable {

    private static final String FILENAME = "discount.data";

    private ArrayList<Discount> discounts = new ArrayList<>();

    @Override
    protected String getFileName() {
        return DiscountData.FILENAME;
    }

    @Override
    protected List<Object> getObjectList() {
        return new ArrayList<Object>(discounts);
    }

    @Override
    protected int getNumberOfObjects() {
        return 1;
    }

    @Override
    protected void saveObjects(List<Object> objs) {
        discounts = (ArrayList<Discount>) objs.get(0);
    }

    public void addDiscount(Discount disc) {
        discounts.add(disc);
    }

    public void setCollection(ArrayList<Discount> discounts) {
        discounts.clear();
        if (discounts != null)
            discounts.addAll(discounts);
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getADiscount() {
        if (discounts == null || discounts.isEmpty())
            return null;
        for (Discount disc : discounts)
            if (disc.isValid())
                return disc;
        return null;
    }

    public Discount findFromBarcode(String code) throws NotFoundException {
        if (discounts != null)
            for (Discount discount : discounts) {
                if (discount.getBarcode().getCode().equals(code))
                    return discount;
            }
        throw new NotFoundException("DiscountData");
    }

}
