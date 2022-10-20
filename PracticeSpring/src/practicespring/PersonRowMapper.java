package practicespring;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;


public class PersonRowMapper implements RowMapper{
     @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException{
     PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    class PersonResultSetExtractor implements ResultSetExtractor{
    @Override
        public Object extractData(ResultSet rs) throws SQLException{
        Person person = new Person("Name","Lastname","Phone");
        person.setId(rs.getInt("id"));
        person.setname(rs.getString("name"));
        person.setlastname(rs.getString("lastname"));
        person.setphone(rs.getString("phone"));
        return person;
        }
    }
   
}
