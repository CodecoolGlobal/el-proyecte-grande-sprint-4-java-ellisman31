package com.codecool.forcedepartment.controller.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.security.JWT.JWTConfig;
import com.codecool.forcedepartment.security.JWT.RefreshTokenDto;
import com.codecool.forcedepartment.service.UserService;
import com.codecool.forcedepartment.service.WorkerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllUsersApi {

    private static final int PLUS_DAY_AFTER_ACCESS_TOKEN = 14;
    private UserService userService;
    private WorkerService workerService;
    private final SecretKey secretKey;
    private final JWTConfig jwtConfig;

    @Autowired
    public GetAllUsersApi(UserService userService, WorkerService workerService,
                          SecretKey secretKey, JWTConfig jwtConfig) {
        this.userService = userService;
        this.workerService = workerService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/api/getUser", method = RequestMethod.GET)
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = "/api/register/user", method = RequestMethod.POST)
    public String addUser(@RequestBody String userJson) throws JSONException, ParseException {
        JSONObject user = new JSONObject(userJson);

        String firstName = user.getString("firstName");
        String lastName = user.getString("lastName");
        String birthOfDate = user.getString("birthOfDate");
        String userType = user.getString("userType");
        String password = user.getString("password");
        String email = user.getString("email");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthOfDateParsed = formatter.parse(birthOfDate);

        User newUser = new User(firstName, lastName, birthOfDateParsed, email, password, userType);
        userService.addUserToDatabase(newUser);
        return "User created";
    }

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.GET)
    public List<Worker> getAllWorker() {
        return workerService.getAllWorkers();
    }

    @RequestMapping(value = "/api/register/worker", method = RequestMethod.POST)
    public String addWorker(@RequestBody String workerJson) throws JSONException {
        JSONObject workerData = new JSONObject(workerJson);

        String description = workerData.getString("description");
        String phoneNumber = workerData.getString("telephoneNumber");

        Worker worker = new Worker(userService.getTheLatestId(), phoneNumber, 0.0, description);

        workerService.addWorkerToDatabase(worker);
        return "Worker created";
    }

    @RequestMapping(value = "/api/token/refresh", method = RequestMethod.GET)
    public RefreshTokenDto getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            try {
                String refreshToken = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

                Algorithm algorithm = Algorithm.HMAC256(String.valueOf(secretKey).getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String email = decodedJWT.getSubject();
                Optional<User> user = userService.getUserByEmail(email);

                String accessToken = JWT.create()
                        .withSubject(user.get().getEmail())
                        .withExpiresAt(java.sql.Date.valueOf(
                                LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()+PLUS_DAY_AFTER_ACCESS_TOKEN)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", user.get().getRole())
                        .sign(algorithm);

                refreshTokenDto.setAccessToken(accessToken);
                refreshTokenDto.setRefreshToken(refreshToken);

            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.sendError(FORBIDDEN.value());
            }
        }
        return refreshTokenDto;
    }

}
