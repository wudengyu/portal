package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
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

    @Override
    public int countByColumnId(int columnid) {
        Integer total=jdbcOperations.queryForObject("select count(*) from p8_article where fid=?",Integer.class,columnid);
        return total==null?0:total.intValue();
    }

    @Override
    public Page<Article> loadByColumnId(int columnid,Pageable paging) {
        List<Article> articles=jdbcOperations.query("select aid,title,author,from_unixtime(greatest(posttime,edittime)) as lastmodifiedtime from p8_article where fid=? limit ?,?",new ColumnRowMapper(),columnid,paging.getPageNumber()*paging.getPageSize(),paging.getPageSize());
        return new PageImpl<>(articles,paging,countByColumnId(columnid));
    }

    private static final class ColumnRowMapper implements RowMapper<Article>{

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Article(rs.getInt("aid"), rs.getString("title"),rs.getString("author"),rs.getDate("lastmodifiedtime"));
        }        
    }
    
}
