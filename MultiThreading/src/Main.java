import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        Student[] student = {new Student("Pinidu",20),new Student("Sunil",30),new Student("Saman",50)};
        for (Student students:student){
            students.displayInfo();
        }

        ArrayList<String> names  = (new ArrayList<>());
        names.add("Pinidu");
        names.add("Sunil");
        names.add("Kamal");

        Map<String,String> map = new HashMap<String,String>();
        map.put("Pinidu","A+");
        map.put("Sunil","A++");


    }

    public static void updateAge(Student[] students) {
        for (Student student : students){
            student.age += 1;
        }
    }



}
