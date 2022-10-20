package practicespring;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

public class PersonDAO implements IPersonDAO{
    private DataSource dataSource;
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void insert(Person person){
     JdbcTemplate jt = new JdbcTemplate(dataSource);
     jt.update("INSERT INTO PERSON (NAME, LASTNAME, PHONE) VALUES(?,?,?)",
             new Object[]{person.getname(), person.getlastname(), person.getphone()});
    }
    @Override
    public void append(String name, String lastname, String phone) {
    JdbcTemplate jt = new JdbcTemplate(dataSource);
    jt.update("INSERT INTO PERSON (NAME, LASTNAME, PHONE) VALUES(?,?,?)",
            new Object[]{name, lastname, phone});
    }
    @Override
    public void deleteBylastname(String lastname) {
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM PERSON WHERE LASTNAME LIKE ?", new Object[]{'%' + lastname + '%'});
        
    }
         @Override
    public void deleteByname(String name) {
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM PERSON WHERE NAME LIKE ?", new Object[]{'%' + name + '%'});
    }
     @Override
    public void delete(final String name, final String lastname){
     TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback(){
        @Override
            public Object doInTransaction(TransactionStatus status){
            try{
            JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from PERSON where NAME= ? AND LASTNAME = ?", new Object[]{name, lastname});
            }catch (RuntimeException e){
            status.setRollbackOnly();
                    throw e;
            }catch (Exception e) {
            status.setRollbackOnly();
                    throw new RuntimeException(e);
            }
            return null;
            }
        });
    }
    @Override 
    public void deleteByphone(String phone){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM PERSON WHERE PHONE LIKE ?", new Object[]{'%' + phone + '%'});
    }
     @Override
    public void deleteAll(){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from PERSON");
    }
    @Override
    public void update(String oldlastname, String newlastname){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE PERSON SET LASTNAME = ? WHERE LASTNAME = ?", new Object[]{newlastname, oldlastname});
    }
    
    @Override
    public List<Person> findByname(String name){
     JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM PERSON WHERE NAME LIKE ?";
        List<Person> persons = jt.query(sql, new Object[]{'%' + name + '%'}, new PersonRowMapper());
        return persons;
    }
    @Override
    public List<Person> select(String name, String lastname){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from PERSON where NAME = ? AND LASTNAME= ?",
                new Object[]{name, lastname}, new PersonRowMapper());
    }
    

    
    
    @Override
    public List<Person> selectAll(){
     JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from PERSON", new PersonRowMapper());
    }
    
    @Override
    public List<Person> findByphone(String phone){
     JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM PERSON WHERE PHONE LIKE ?";
        List<Person> persons = jt.query(sql, new Object[]{'%' + phone + '%'}, new PersonRowMapper());
        return persons;
    }
    
    @Override
    public List<Person> findBylastname(String lastname){
     JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM PERSON WHERE LASTNAME LIKE ?";
        List<Person> persons = jt.query(sql, new Object[]{'%' + lastname + '%'}, new PersonRowMapper());
        return persons;
    }
    

   
}
