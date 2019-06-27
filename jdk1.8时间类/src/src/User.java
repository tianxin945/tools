package src;

/**
 * @Author tianxin
 * @Date 2019/3/21
 * @Description:
 */
public class User {



    private String name;
    private int age;
    private int sex;

    public User(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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
}
