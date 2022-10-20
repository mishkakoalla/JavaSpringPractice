package practicespring;
import javax.sql.DataSource;
import java.util.List;



public interface IPersonDAO {
    void setDataSource(DataSource ds);
    void insert(Person person);
    void append(String name,String lastname,String phone);
    void deleteByname(String name);
    void deleteBylastname(String lastname); 
    void delete(String name, String lastname);
    void deleteByphone(String phone);
    void deleteAll();
    void update(String oldlastname, String newlastname);
   
    
    List<Person> findByname(String name);
    List<Person> select(String name, String lastname);
    List<Person> findByphone(String phone);
    List<Person> selectAll();
    List<Person> findBylastname(String lastname);
    
    
        
}
