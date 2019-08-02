package 空值判断;

/**
 * @author:tx
 * @Date:2019/8/2
 * @Description:
 */
public class Person {
    private String name;
    private String sex;
    private String  type;

    public Person() {
    }

    public Person(String name, String sex, String type) {
        this.name = name;
        this.sex = sex;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
