package shopInventoryGUI.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Wojciech on 21.03.2017.
 */
@XmlRootElement(name="products")
public class ProductListWrapper {
    private List<Product> productList;

    @XmlElement(name = "product")
    public List<Product> getProductList(){
        return productList;
    }

    public void setProductList(List<Product> products){
        this.productList=products;
    }
}
