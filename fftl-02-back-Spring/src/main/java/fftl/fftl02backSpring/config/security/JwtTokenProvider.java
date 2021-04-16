package fftl.fftl02backSpring.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    //나만의 토큰 생성키
    @Value("${spring.jwt.secret")
    private String secretKey;

    //토큰 유효시간 설정
    private long tokenValidMilliSecond = 1000L * 60 * 120; //2시간
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() { secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());}

    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)  //claim 데이터
                .setIssuedAt(now)   //token 발행시간
                .setExpiration(new Date(now.getTime() + tokenValidMilliSecond)) //token 유효시간
                .signWith(SignatureAlgorithm.HS256, secretKey)  //암호화 알고리즘, secret 값을 사용해 암호화
                .compact();
    }

//    public Authentication getAuthentication(String token){
//    }
}
