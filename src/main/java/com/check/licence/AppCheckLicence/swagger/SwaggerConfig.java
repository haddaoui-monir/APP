package com.check.licence.AppCheckLicence.swagger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration
//Enable Swagger
//url swagger-2-documentation-for-spring-rest-api
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String CLIENT_ID="user";
    private String CLIENT_SECRET="12345";
    private String AUTH_SERVER="http://localhost:8080";
	     //Bean Docket
		// Swagger 2
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.any())
	    		  //basePackage("com.check.licence.AppCheckLicence"))
	      .paths(PathSelectors.any())
	      .build()
	       .securitySchemes(Lists.newArrayList(apiKey()))
          .securityContexts(Lists.newArrayList(securityContext()))
          //.apiInfo(generateApiInfo());
	      .apiInfo(apiInfo());
	     // .securitySchemes(Arrays.asList(securityScheme()))
         // .securityContexts(Arrays.asList(securityContext()));
	}
	List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Lists.newArrayList( new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
	    return new ApiKey("JWT", "Authorization", "header");
	}
	
	@Bean
	SecurityContext securityContext() {
	    return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.any())
	            .build();
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Licence APP", 
	      "DISCOVER MORE INFO ABOUT LICENCE RESOURCE.", 
	      "API CHECK LICENCE", 
	      "", 
	      new Contact("Mr x", "www.peaqock.com", "peaqockoffice@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
//	@Bean
//	public SecurityConfiguration security() {
//		return SecurityConfigurationBuilder.builder()
//			.clientId(CLIENT_ID)
//	        .clientSecret(CLIENT_SECRET)
//            .scopeSeparator(" ")
//            .useBasicAuthenticationWithAccessCodeGrant(true)
//            .build();
//	}		
//		private SecurityScheme securityScheme() {
//		    GrantType grantType = new AuthorizationCodeGrantBuilder()
//		        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
//		        .tokenRequestEndpoint(
//		          new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_SECRET))
//		        .build();
//		 
//		    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//		        .grantTypes(Arrays.asList(grantType))
//		        .scopes(Arrays.asList(scopes()))
//		        .build();
//		    return oauth;
//		}
		
//		private AuthorizationScope[] scopes() {
//		    AuthorizationScope[] scopes = { 
//		      new AuthorizationScope("read", "for read operations"), 
//		      new AuthorizationScope("write", "for write operations"), 
//		      new AuthorizationScope("foo", "Access foo API") };
//		    return scopes;
//		}
//		private SecurityContext securityContext() {
//		    return SecurityContext.builder()
//		      .securityReferences(
//		    		  Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//		      .forPaths(PathSelectors.regex("/foos.*"))
//		      .build();
//		}
    }

