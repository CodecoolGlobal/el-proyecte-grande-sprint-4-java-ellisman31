package com.codecool.forcedepartment.controller.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.service.UserService;
import com.codecool.forcedepartment.service.WorkerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllUsersApi {

    private UserService userService;
    private WorkerService workerService;

    @Autowired
    public GetAllUsersApi(UserService userService, WorkerService workerService) {
        this.userService = userService;
        this.workerService = workerService;
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllUser", method = RequestMethod.POST)
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

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getAllWorker() {
        return workerService.getAllWorkers();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.POST)
    public String addWorker(@RequestBody String workerJson) throws JSONException {
        JSONObject workerData = new JSONObject(workerJson);

        String description = workerData.getString("description");
        String phoneNumber = workerData.getString("telephoneNumber");

        Worker worker = new Worker(userService.getTheLatestId(), phoneNumber, false, 0, description);

        workerService.addWorkerToDatabase(worker);
        return "Worker created";
    }

    @RequestMapping(value = "/api/token/refresh", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String email = decodedJWT.getSubject();
                User user = userService.getUserByEmail(email);

                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", user.getGroup_name())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch(Exception e) {
                response.setHeader("error", e.getMessage());
                response.sendError(FORBIDDEN.value());
            }
        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
