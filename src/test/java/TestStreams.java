import org.example.beans.Person;
import org.example.mock.MockData;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreams {

    @Test
    void streams(){

        Stream<String> namesStream = Stream.of("Eugene", "Baniaga", null);
        //System.out.println(namesStream.count());

        System.out.println(namesStream.anyMatch(Objects::isNull));
    }

   @Test
   void listPeople () throws IOException {
        //find people aged 21 and get the first 10
       List<Person> people = MockData.getPeople();

       //for debug mode, Add breakpoint, run on debug and trace current stream chain
       List<Person> underage = people.stream()
               .filter(person -> person.getAge() == 16)
               .limit(10).collect(Collectors.toList());
       System.out.println(underage.toString());

   }

}
