package portal.repository;

import java.util.List;

import portal.business.Article;

public interface ArticleRepository {
    Article loadById(int id);
    List<Article> loadByColumnId(int columnid);
}
