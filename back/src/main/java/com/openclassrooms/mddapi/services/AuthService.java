package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;

public interface AuthService {
	User save(RegisterRequest registerRequest) throws BadRequestException;

}
