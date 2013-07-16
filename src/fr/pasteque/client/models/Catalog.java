/*
    Pasteque Android client
    Copyright (C) Pasteque contributors, see the COPYRIGHT file

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package fr.pasteque.client.models;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

/** A catalog with categories and matching products */
public class Catalog implements Serializable {

    /** The first level of the category tree */
    private List<Category> categories;
    private Map<Category, List<Product>> products;
    private Map<String, Product> database;
    private Category prepaidCategory;

    public Catalog() {
        this.categories = new ArrayList<Category>();
        this.products = new HashMap<Category, List<Product>>();
        this.database = new HashMap<String, Product>();
    }
    
    /** Add a root category and all its subcategories.
     * Warning: subcategories should not be added after, this would cause
     * the catalog to be out of sync.
     */
    public void addRootCategory(Category c) {
        this.categories.add(c);
        if (c.getId().equals("-1")) {
            this.prepaidCategory = c;
        }
        if (!this.products.containsKey(c)) {
            this.products.put(c, new ArrayList<Product>());
        }
        this.addSubcategories(c);
    }
    
    private void addSubcategories(Category c) {
        for (Category sub : c.getSubcategories()) {
            if (!this.products.containsKey(c)) {
                this.products.put(c, new ArrayList<Product>());
            }
            this.addSubcategories(sub);
        }
    }

    public void addProduct(Category c, Product p) {
        this.products.get(c).add(p);
        this.database.put(p.getId(), p);
    }

    public List<Category> getRootCategories() {
        return this.categories;
    }
    public Category getPrepaidCategory() {
        return this.prepaidCategory;
    }

    public List<Product> getProducts(Category c) {
        return this.products.get(c);
    }

    public Product getProduct(String id) {
        return this.database.get(id);
    }

    public int getProductCount() {
        return this.database.keySet().size();
    }

    public Catalog fromJSON(JSONArray array) throws JSONException {
        
        return null;
    }
}