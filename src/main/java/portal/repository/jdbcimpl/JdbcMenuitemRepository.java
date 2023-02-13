package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import portal.business.Menuitem;
import portal.repository.MenuitemRepository;

@Repository
public class JdbcMenuitemRepository implements MenuitemRepository{

    @Autowired
    private JdbcOperations jdbcOperations;


    @Override
    public List<Menuitem> findByMenuid(int menuid) {
        return jdbcOperations.query("select id,text,url from menu_item where menu_id=? and parent is null",
        new MenuitemRowMapper(),menuid);
    }

    @Override
    public List<Menuitem> findByParentid(int parentid) {
        return jdbcOperations.query("select id,text,url from menu_item where parent=?",
        new MenuitemRowMapper(),parentid);
    }

    private static final class MenuitemRowMapper implements RowMapper<Menuitem>{

        @Override
        public Menuitem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Menuitem(rs.getInt("id"), rs.getString("text"),rs.getString("url"));
        }
        
    }
    
}
