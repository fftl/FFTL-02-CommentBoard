package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.repository.UserRepository;
import fftl.fftl02backSpring.request.SaveUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean saveUser(SaveUserDto saveUserDto){
        User user = userRepository.save(saveUserDto.toEntity());

        if(user == null){
            return false;
        }

        return true;
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
