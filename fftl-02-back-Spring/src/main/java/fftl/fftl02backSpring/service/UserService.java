package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.repository.UserRepository;
import fftl.fftl02backSpring.request.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveUser(SaveUserRequest saveUserRequest){
        return userRepository.save(saveUserRequest.toEntity());
    }

    public User findByUserId(Long user_id){
        User user = userRepository.findById(user_id).orElse(null);
        return user;
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username).orElse(null);
        return user;
    }

    public User findByNickname(String nickname){
        User user = userRepository.findByNickname(nickname).orElse(null);
        return user;
    }
}
