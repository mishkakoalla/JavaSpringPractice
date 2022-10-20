package practicespring;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;
/**
 *
 * @author Миша Дунаев
 */
 class PracticeSpring {


    public static void main(String[] args) {
        try{
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
             PersonDAO personDAO = (PersonDAO) context.getBean("customerDAO");
              personDAO.deleteAll();
              Person person = new Person("Dunayev", "Mikhail", "Xiaomi");
              personDAO.insert(person);
              personDAO.insert(new Person("Sergey", "Talin", "Iphone"));
              personDAO.insert(new Person("Pavel", "Borovoi", "Poco"));
              personDAO.deleteBylastname("bor");
               personDAO.delete("Sergey", "Talin");
                List<Person> persons = personDAO.findByname("ser");
                System.out.println(persons != null ? persons : "Нет данных");
                 personDAO.append("Lars", "Vogel", "Iphone");
                 personDAO.append("Kamran", "Aliyev", "Xiaomi");
                 personDAO.append("Jim", "Knopf", "Xiaomi");
                  personDAO.append("Lars", "Man", "Iphone");
                  personDAO.append("Spider", "Man", "SpiderPhone");
                   personDAO.update("Knopf", "Kim");
                    System.out.println("Danniye v tablice:");
                     List<Person> list = personDAO.selectAll();
            for (Person myPerson : list){
            System.out.println(myPerson.getname() + " " + myPerson.getlastname() + " " + myPerson.getphone());
            }
            System.out.println("Vivod zapisey s imenem Lars i familiey Vogel:");
list = personDAO.select("Lars", "Vogel");
            for (Person myPerson : list){
            System.out.println(myPerson.getname() + " " + myPerson.getlastname());
            }
            System.out.println("Vivod ludey s telefonami XIaomi");
            list = personDAO.findByphone("Xiaomi");
            for (Person myPerson : list){
            System.out.println(myPerson.getname() + " " + myPerson.getlastname() + " " + myPerson.getphone());
            }
        }catch (Exception e) {
        e.printStackTrace();
        System.out.println("Ошибка!");
    }
        
    }
    
}
