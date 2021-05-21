package fftl.fftl02backSpring.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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

    //토큰을 이용한 유저 정보 가져오기
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰 암호화에 사용된 secretKey와 jwt함수를 이용해 username값을 가져옵니다.
    public String getUserName(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req){
        String myToken = req.getHeader("Authorization");
        System.out.println("point2 : " + myToken);

        if(myToken == null){
            return null;
        }

        System.out.println("point2.5 : " + ("Bearer ".length()));
        String realToken = myToken.substring("Bearer ".length());
        System.out.println("point3 : " + realToken);

        return realToken;
    }

    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
