package portal.repository;

import java.util.List;

import portal.business.Column;

public interface ColumnRepository {
    String loadColumnTitleById(int columnid);
    List<Column> findAllLeaf();
}
