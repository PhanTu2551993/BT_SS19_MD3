package ra.btss19.dao;

import ra.btss19.entity.Category;
import ra.btss19.entity.Product;

import java.util.List;

public interface IGenericProduct {
    List<Product> getProduct();
    public Product getProductById(Integer pro_Id);
    public boolean insertProduct(Product pro);
    public boolean updateProduct(Product pro);
    public boolean deleteProduct(Integer pro_Id);
    public List<Product> getProductByName(String name);
}
