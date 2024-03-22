package homebook.springbootlesson3.entity;

import lombok.Data;

@Data
public class Book {
    private static long genId;

    private final long id;
    private  String name;

    public Book(String name) {
        id = genId++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
