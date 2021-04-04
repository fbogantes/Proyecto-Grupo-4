/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carousel;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Frey
 */
@Named
@ViewScoped
public class CarouselView implements Serializable {

    private List<Product> products;

    private Product selectedProduct;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(9);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
