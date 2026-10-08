package POJO;

public class PostRequest {

    String name;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getSubjects() {
        return languages;
    }

    public void setSubjects(String[] languages) {
        this.languages = languages;
    }

    String grade;
    int age;
    String[] languages;

}
