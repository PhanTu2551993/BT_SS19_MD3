package ra.btss19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.btss19.dao.IProductDao;
import ra.btss19.entity.Product;

import java.util.List;
@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> getProduct() {
        return productDao.getProduct();
    }

    @Override
    public Product getProductById(Integer pro_Id) {
        return productDao.getProductById(pro_Id);
    }

    @Override
    public boolean insertProduct(Product pro) {
        return productDao.insertProduct(pro);
    }

    @Override
    public boolean updateProduct(Product pro) {
        return productDao.updateProduct(pro);
    }

    @Override
    public boolean deleteProduct(Integer pro_Id) {
        return productDao.deleteProduct(pro_Id);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productDao.getProductByName(name);
    }
}
