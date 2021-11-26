package it.polimi.db2telco.services;

import it.polimi.db2telco.entities.User;
import it.polimi.db2telco.exceptions.CredentialsException;
import org.hibernate.service.spi.InjectService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext(unitName="Telco")
    private EntityManager em;

    public UserService() {  }

    public User checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
        List<User> uList = null;
        try {
            uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentals");
        }
        if (uList.isEmpty())
            return null;
        else if (uList.size() == 1)
            return uList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }


   /* public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User by username " + username + "was not found"));
    }

    public User checkCredentials(String username, String password){
        User user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
        if(user.getPassword().equals(password))
            return user;
        throw (new InvalidCredentialsException("The credentials for user " + username + " are incorrect"));
    }
*/

}
