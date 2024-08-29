package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    @Transactional
    void addCar(Car car);

    @Transactional(readOnly = true)
    List<Car> listCar();

    User findByCarModelAndSeries(String model, int series);
}
