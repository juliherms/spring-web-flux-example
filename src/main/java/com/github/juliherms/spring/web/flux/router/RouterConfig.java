package com.github.juliherms.spring.web.flux.router;

import com.github.juliherms.spring.web.flux.dto.User;
import com.github.juliherms.spring.web.flux.handler.UserHandler;
import com.github.juliherms.spring.web.flux.handler.UserStreamHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private UserHandler handler;

    @Autowired
    private UserStreamHandler streamHandler;

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/router/users",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = UserHandler.class,
                            beanMethod = "loadUsers",
                            operation = @Operation(
                                    operationId = "loadUsers",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "successful operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = User.class
                                                    ))
                                            )
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/router/user/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = UserHandler.class,
                            beanMethod = "findUser",
                            operation = @Operation(
                                    operationId = "findUser",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "successful operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = User.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404",description = "user not found with given id")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH,name = "id")
                                    }

                            )

                    ),
                    @RouterOperation(
                            path = "/router/user/save",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.POST,
                            beanClass = UserHandler.class,
                            beanMethod = "saveUser",
                            operation = @Operation(
                                    operationId = "saveUser",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "successful operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = String.class
                                                    ))
                                            )
                                    },
                                    requestBody = @RequestBody(
                                            content = @Content(schema = @Schema(
                                                    implementation = User.class
                                            ))
                                    )

                            )


                    )
            }
    )
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/users",handler::loadUsers)
                .GET("/router/users/stream",streamHandler::getUsers)
                .GET("/router/users/{id}",handler::findUser)
                .POST("/router/user/save",handler::saveUser)
                .build();

    }

}
