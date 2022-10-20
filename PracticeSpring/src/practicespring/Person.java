package practicespring;

/**
 *
 * @author Миша Дунаев
 */
public class Person {
    
    int id;
    String name;
    String lastname;
    String phone;
    
    
    public Person(String Dunayev, String Mikhail, String Xiaomi){
        this.id = 0;
        this.name = "";
        this.lastname = "";
        this.phone = "";        
    }
    
    public int getId (){
        return id;
    }
    public String getname(){
    return name;
    }
    public String getlastname (){
    return lastname;
    }
    public String getphone(){
    return phone;
    }
    public void setId(int id){
    this.id = id;
    }
    public void setname(String name){
    this.name = name;
    }
    public void setlastname (String lastname){
    this.lastname = lastname;
    }
    public void setphone(String phone){
    this.phone = phone;
    }
    
    @Override
    public String toString (){
    return String.format("name=%s, lastname=%s, phone=%s", name , lastname, phone);
    }
}
