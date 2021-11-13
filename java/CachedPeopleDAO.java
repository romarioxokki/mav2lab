import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CachedPeopleDAO implements Dao {
    private Map cachedPeople = new HashMap();


    @Override
    public void save(Person person) throws IOException {
         cachedPeople.put(person.getId(),person);
    }

    @Override
    public void update(Person person, Map params) throws IOException {
         cachedPeople.remove(person.getId());
         cachedPeople.put(person.getId(),person);

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Person  findById(int id,String perk) throws IOException {
        return (Person) cachedPeople.get(id);
    }
}