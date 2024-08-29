package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Создаем пользователей и автомобили
      User user3 = new User("oleg", "chel", "user1@mail.ru");
      Car car1 = new Car("Toyota", 2020, user3);
      user3.setCar(car1);
      car1.setUser(user3); // Устанавливаем связь

      userService.add(user3); // Убедитесь, что это после установки всех свойств

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Brand = "+user.getCar().getModel());
            System.out.println("Car Model = "+user.getCar().getSeries());
         }
         System.out.println();
      }

      context.close();
   }
}
