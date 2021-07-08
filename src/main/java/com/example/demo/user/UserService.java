package com.example.demo.user;
import com.example.demo.SymmetricEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<String> createUser(User user){
        String password="";
        try {
            if (!userRepository.existsByEmail(user.getEmail())){
                user.setId(null == userRepository.findMaxId()? 0 : userRepository.findMaxId() + 1);
                String[] passString=  user.getPassword().split("@",-2);

                int pin[]=new int[passString.length];
                for(int x=0; x < pin.length;x++){
                    pin[x]=Integer.parseInt(passString[x]);
                }
                int encryptedPin[] = SymmetricEncryption.encryptPin(pin);

                for(int x=0; x < pin.length;x++){
                    password+=String.valueOf(encryptedPin[x]);
                    if(x < pin.length -1){
                        password+="@";

                    }
                }
                user.setPassword(password);
                userRepository.save(user);
                return  List.of("User record created successfully.");
            }else {
                return List.of("User already exists in the database.");
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public List<User> getUserByEmailAndPassword(String email,String password){


            String credentials=null;
            String pinString="";

            List<User> users= userRepository.findByEmail(email);

             if(!users.isEmpty() ){

                 String[] passString=  users.get(0).getPassword().split("@",-2);

                 int pin[]=new int[passString.length];
                 for(int x=0; x < pin.length;x++){
                     pin[x]=Integer.parseInt(passString[x]);
                 }
                 int decryptedPin[] = SymmetricEncryption.decryptPin(pin);

                 for(int y=0; y < decryptedPin.length;y++){
                     pinString+=String.valueOf(decryptedPin[y]);
                 }
                 if(password.equals(pinString)){

                     users.get(0).setPassword("");

                     return users;
                 }
                 return List.of(new User(
                         -1,"Wrong credentials","Wrong credentials","Wrong credentials","Wrong credentials"
                 ));

             }
             else{
                 return List.of(new User(
                         -1,"Wrong credentials","Wrong credentials","Wrong credentials","Wrong credentials"
                 ));
             }

    }

    @Transactional
    public List<String> updateStudent(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.stream().forEach(s -> {
                    User userToBeUpdate = userRepository.findById(s.getId()).get();
                    userToBeUpdate.setName(user.getName());
                    userToBeUpdate.setEmail(user.getEmail());
                    userRepository.save(userToBeUpdate);
                });
                return List.of("User record updated.");

            }catch (Exception e){
                throw e;
            }
        }else {
            return List.of("User does not exists in the database.");
        }
    }

    @Transactional
    public List<String> deleteUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.stream().forEach(s -> {
                    userRepository.delete(s);
                });
                return List.of("User record deleted successfully.");
            }catch (Exception e){
                throw e;
            }

        }else {
            return List.of("User does not exist");
        }
    }
}
