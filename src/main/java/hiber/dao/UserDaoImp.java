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
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addCar(Car car){
      sessionFactory.getCurrentSession().save(car);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User findUserByModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "from User where car.model = :carModel and car.series = :carSeries");
      query.setParameter("carModel", model);
      query.setParameter("carSeries", series);
      return query.getSingleResult();

   }
}
