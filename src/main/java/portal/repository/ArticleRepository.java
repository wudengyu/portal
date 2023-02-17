package portal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.business.Article;

public interface ArticleRepository {
    Article loadById(int id);
    int countByColumnId(int columnid);
    List<Article> loadByColumnId(int columnid,int offset,int rows);
    Page<Article> loadByColumnId(int columnid,Pageable paging);
}
