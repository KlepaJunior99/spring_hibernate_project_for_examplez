package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);


      User user1 = new User("Vladimir", "Klepikov", "klepikov@gmail.com");
      User user2 = new User("Ilya", "Smirnov", "smirnov@mail.ru");
      User user3 = new User("Aleksandra", "Romashkova", "romashkovaa@yandex.ru");
      User user4 = new User("Kristina", "Zavialova", "zavialovaK@gmail.com");

      Car car1 = new Car("ToyotaLandCruiser", 300);
      Car car2 = new Car("MersedesBrabus", 700);
      Car car3 = new Car("BMWX6", 150);
      Car car4 = new Car("RangeRoverSport", 500);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println("------------------------------------");
         System.out.println(user + " " + user.getCar());
         System.out.println("------------------------------------");
      }
      System.out.println("------------------------------------");
      System.out.println(userService.getUserWithCar("ToyotaLandCruiser", 300));
      System.out.println("------------------------------------");


      try {
         User notExistUser = userService.getUserWithCar("LadaGranta", 2190);
      } catch (Exception e) {
         System.out.println("------------------------------------");
         System.out.println("Пользователя с такой машиной не существует в данной таблице");
         System.out.println("------------------------------------");
      }
      context.close();
   }
}