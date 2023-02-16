package portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.business.Article;

public interface ArticleRepository {
    Article loadById(int id);
    int countByColumnId(int columnid);
    Page<Article> loadByColumnId(int columnid,Pageable paging);
}
