package ra.btss19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.btss19.dao.ICategoryDao;
import ra.btss19.entity.Category;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public List<Category> getCategory() {
        return categoryDao.getCategory();
    }

    @Override
    public Category getCategoryById(Integer cat_Id) {
        return categoryDao.getCategoryById(cat_Id);
    }

    @Override
    public boolean insertCategory(Category cat) {
        return categoryDao.insertCategory(cat);
    }

    @Override
    public boolean updateCategory(Category cat) {
        return categoryDao.updateCategory(cat);
    }

    @Override
    public boolean deleteCategory(Integer cat_Id) {
        return categoryDao.deleteCategory(cat_Id);
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }
}
