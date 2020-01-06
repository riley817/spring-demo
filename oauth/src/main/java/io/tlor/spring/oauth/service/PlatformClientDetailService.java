package io.tlor.spring.oauth.service;

import io.tlor.spring.oauth.repository.OAuthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class PlatformClientDetailService implements ClientDetailsService {

    @Autowired
    private OAuthClientDetailsRepository oAuthClientDetailsRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails client = oAuthClientDetailsRepository.getOAuthClient(clientId);
        if(client == null) {
            throw new ClientRegistrationException("Unauthorized client");
        }
        return client;
    }
}
