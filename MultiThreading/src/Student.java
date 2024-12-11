public class Student {
    String name;
    int age;
    Student(){};

    Student(String name, int age){
        this.name= name;
        this.age = age;
    }

    public void displayInfo(){
        System.out.println("Student name is :"+name);
        System.out.println("Student age is :"+age);
    }
}

