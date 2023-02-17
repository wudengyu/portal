package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import portal.business.Column;
import portal.repository.ColumnRepository;
@Repository
public class JdbcColumnRepository implements ColumnRepository{
    
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public String loadColumnTitleById(int columnid) {
        return jdbcOperations.queryForObject("select name from p8_sort where fid=?",String.class,columnid);
    }

    @Override
    public List<Column> findAllLeaf() {
        return jdbcOperations.query("select fid,name from p8_sort where fid not in (select fup from p8_sort)",
                new RowMapper<Column>(){
                    public Column mapRow(ResultSet rs, int index) throws SQLException {
                        Column column = new Column(rs.getInt("fid"),rs.getString("name"));
                        return column;
                    }}
        );
    }
    
}
