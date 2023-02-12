package portal.repository.jdbcimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import portal.business.Article;
import portal.repository.ArticleRepository;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Article loadById(int id) {
        return 
        jdbcOperations.query("select a.aid,a.title,b.content from p8_article a join p8_reply b on a.aid=b.aid where a.aid=?",
        (rs)->{
            if(rs.next())
                return new Article(rs.getInt("aid"),rs.getString("title"),rs.getString("content"));
            else
                return null;},
        id);
    }
    
}
