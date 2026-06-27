package media.persistence;

import media.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository  {
    public void save(Category category) throws SQLException;
    Category findById(int categoryId) throws SQLException;
    List<Category> findAll() throws SQLException;
    public void updateName(int categoryId, String categoryName) throws SQLException;
    public void delete(int categoryId) throws SQLException;
 }
