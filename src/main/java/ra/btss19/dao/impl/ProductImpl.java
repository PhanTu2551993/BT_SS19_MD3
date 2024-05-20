package ra.btss19.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.btss19.dao.IProductDao;
import ra.btss19.entity.Product;

import java.util.List;

@Repository
public class ProductImpl implements IProductDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> getProduct() {
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        try {
            products = session.createQuery("from Product ", Product.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public Product getProductById(Integer pro_Id) {
        Session session = sessionFactory.openSession();
        try {
            Product product = session.get(Product.class,pro_Id);
            return product;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertProduct(Product pro) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product pro) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(pro);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Integer pro_Id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getProductById(pro_Id));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Product> getProductByName(String name) {
        Session session = sessionFactory.openSession();
        try{
            if(name==null || name.length()==0)
                name = "%";
            else
                name = "%"+name+"%";
            List list = session.createQuery("from Product where proName like : proName")
                    .setParameter("proName",name)
                    .list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
