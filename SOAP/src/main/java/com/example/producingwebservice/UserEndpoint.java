package com.example.producingwebservice;

import com.example.producingwebservice.entities.User;
import com.example.producingwebservice.generated.*;
import com.example.producingwebservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private UserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		User user = userService.findByUsername(request.getUsername());
		if (user == null)
			return response;
		UserInfo userInfo = convertFromUserToUserInfo(user);
		response.setUser(userInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllRequest")
	@ResponsePayload
	public FindAllResponse findAll(@RequestPayload FindAllRequest request) {
		FindAllResponse response = new FindAllResponse();
		List<User> users = userService.findAll();
		List<UserInfo> usersInfo = users
				.stream()
				.map(u -> convertFromUserToUserInfo(u))
				.collect(Collectors.toList());
		response.setUsers(usersInfo);
		return response;
	}

	private UserInfo convertFromUserToUserInfo(User user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(user.getUsername());
		userInfo.setPassword(user.getPassword());
		userInfo.setRole(user.getRole());
		return userInfo;
	}

	private User convertFromUserInfoToUser(UserInfo userInfo) {
		return new User(
				userInfo.getUsername(),
				userInfo.getPassword(),
				userInfo.getRole()
		);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "postUserRequest")
	@ResponsePayload
	public PostUserResponse postUser(@RequestPayload PostUserRequest request) {
		PostUserResponse response = new PostUserResponse();

		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(request.getUsername());
		userInfo.setPassword(request.getPassword());
		userInfo.setRole(request.getRole());

		User user = convertFromUserInfoToUser(userInfo);
		userService.create(user);
		response.setUser(userInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "putUserRequest")
	@ResponsePayload
	public PutUserResponse putUser(@RequestPayload PutUserRequest request) {
		PutUserResponse response = new PutUserResponse();

		User user = new User(request.getUsername(),
							 request.getPassword(),
							 request.getRole()
							);
		UserInfo userInfo = convertFromUserToUserInfo(user);
		userService.update(user);
		response.setUser(userInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
		DeleteUserResponse response = new DeleteUserResponse();
		String username = request.getUsername();

		User user = userService.findByUsername(username);
		if (user == null)
			return response;
		UserInfo userInfo = convertFromUserToUserInfo(user);
		userService.delete(user);

		response.setUser(userInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginUserRequest")
	@ResponsePayload
	public LoginUserResponse loginUser(@RequestPayload LoginUserRequest request) {
		LoginUserResponse response = new LoginUserResponse();
		User user = userService.findByUsername(request.getUsername());
		if (user == null) {
			response.setMessage("LOGIN FAILED");
			return response;
		}

		String message = user.getPassword().equals(request.getPassword()) ? "LOGIN SUCCESSFUL" : "LOGIN FAILED";
		response.setMessage(message);
		return response;
	}
}