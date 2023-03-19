package portal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.business.Article;

public interface ArticleRepository {
    Article loadById(int id);
    int countByColumnId(int columnid);
    int countByUsername(String username);
    List<Article> findByColumnId(int columnid,int offset,int rows);
    List<Article> findByUsername(String username,int offset,int rows);
    Page<Article> findByColumnId(int columnid,Pageable paging);
    Page<Article> findByUsername(String username,Pageable paging);
}
