package in.strikes.oauthDemo.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    // ✅ No @Autowired here, just instantiate directly
    private final OidcUserService oidcUserService = new OidcUserService();

    private final UserService userService;

    // ✅ Constructor injection for UserService
    public CustomOidcUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public @Nullable OidcUser loadUser(OidcUserRequest userRequest)
            throws OAuth2AuthenticationException {
        // Let Spring’s default OIDC service handle user loading
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);

        // Extract provider (e.g., "google")
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // Save or update user in DB
        userService.registerOrUpdate(provider, oidcUser);

        return oidcUser;
    }
}
