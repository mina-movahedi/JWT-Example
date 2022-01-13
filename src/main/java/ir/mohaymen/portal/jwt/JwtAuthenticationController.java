package ir.mohaymen.portal.jwt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 3.
 * Expose a POST API /authenticate using the JwtAuthenticationController.
 * The POST API gets username and password in the body- Using Spring Authentication Manager
 * we authenticate the username and password. If the credentials are valid, a JWT token is created
 * using the JWTTokenUtil and provided to the client.
 */

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        String token = "";

        try {
            if (authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
                token = jwtTokenUtil.generateToken(userDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private boolean authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
            return authentication.isAuthenticated();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
