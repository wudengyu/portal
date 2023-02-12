package portal.repository;

import portal.business.Article;

public interface ArticleRepository {
    Article loadById(int id);
}
