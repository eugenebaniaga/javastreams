import org.example.beans.Car;
import org.example.beans.Person;
import org.example.beans.PersonDTO;
import org.example.mock.MockData;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
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
   void testFilter () throws IOException {
       List<Person> people = MockData.getPeople();
       //for debug mode, Add breakpoint, run on debug and trace current stream chain
       Predicate<Person> personPredicate = person -> person.getAge() <= 17;
       List<Person> underage = people.stream()
               .filter(personPredicate)
               .limit(20).collect(Collectors.toList());
       underage.forEach(System.out::println);


       List<Car> cars = MockData.getCars();
       Predicate<Car> carColor = car -> Objects.equals(car.getColor(), "Yellow");
       Predicate<Car> carPrice = car -> car.getPrice() < 20_000.00;
       List<Car> carFiltered = cars.stream()
              .filter(carPrice)
              .filter(carColor)
              .collect(Collectors.toList());

       carFiltered.forEach(System.out::println);
   }

   @Test
    void testFunctional () throws IOException {
       List<Person> people = MockData.getPeople();
       Function<Person, PersonDTO> personPersonDTOFunction = person ->
               new PersonDTO(person.getId(), person.getFirstName(), person.getAge());

       List<PersonDTO> dtos = people.stream()
               .filter(person -> person.getAge() > 20)
               .map(personPersonDTOFunction)
               .collect(Collectors.toList());

       dtos.forEach(System.out::println);
   }

}
