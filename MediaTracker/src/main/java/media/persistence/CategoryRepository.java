package media.persistence;

import media.model.*;
import java.util.List;

public interface CategoryRepository {
    public void save(Category category);
    Category findById(int categoryId);
    List<Category> findAll();
    public void updateName(int categoryId, String categoryName);
    public void delete(int categoryId);
}
