package homebook.springbootlesson3.repository;



import homebook.springbootlesson3.entity.Reader;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private List<Reader> list = new ArrayList<>();

    public ReaderRepository() {
        list.add(new Reader("Костя"));
        list.add(new Reader("Василий"));
        list.add(new Reader("Семен"));

    }

    public Reader findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public String getDescription(long id) {
        StringBuilder description = new StringBuilder();
        list.forEach(reader -> description.append(reader.getName()).append(", "));
        return description.toString();
    }

    public void delete(long id) {
        list.removeIf(book -> book.getId() == id);
    }


    public Reader save(Reader reader) {
        list.add(reader);
        return reader;
    }

}
