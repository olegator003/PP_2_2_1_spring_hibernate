package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) {sessionFactory.getCurrentSession().save(car);}

   @Override
   public List<Car> listCar() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   public User findByCarModelAndSeries(String model, int series) {
      String hql = "SELECT c.user FROM Car c WHERE c.id = :carId AND c.model = :model AND c.series = :series";
      return sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }



}
