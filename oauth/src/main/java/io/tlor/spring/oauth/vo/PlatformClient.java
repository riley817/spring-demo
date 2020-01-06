package io.tlor.spring.oauth.vo;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

@NoArgsConstructor
public class PlatformClient implements ClientDetails {

    private String clientId;
    private Set<String> resourceIds;
    private String clientSecret;
    private Set<String> scope;
    private String authorizedGrantTypes;
    private String registeredRedirectUri;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private String authorities;
    private Map<String, Object> additionalInformation;

    public PlatformClient(String clientId, String resourceIds, String clientSecret, String scope,
                          String authorizedGrantTypes, String registeredRedirectUri,
                          Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,
                          String authorities, String additionalInformation) {

        this.clientId = clientId;
        this.resourceIds = _tokenizeStringAttribute(resourceIds);
        this.clientSecret = clientSecret;
        this.scope = _tokenizeStringAttribute(scope);
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUri = registeredRedirectUri;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.authorities = authorities;
        //this.additionalInformation = _tokenizeAuthority(additionalInformation);
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(this.authorizedGrantTypes, ",");
        while(st.hasMoreElements()) {
            result.add(st.nextToken());
        }
        return result;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(this.registeredRedirectUri, ",");
        while(st.hasMoreElements()) {
            result.add(st.nextToken());
        }
        return result;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {


        /*List<GrantedAuthority> authorities = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(this.authorities, ",");
        while(st.hasMoreElements()) {
            authorities.add(new SimpleGrantedAuthority(st.nextToken()));
        }*/
        return null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    @Override
    public String toString() {
        return "PlatformClient{" +
                "clientId='" + clientId + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", registeredRedirectUri='" + registeredRedirectUri + '\'' +
                ", accessTokenValiditySeconds=" + accessTokenValiditySeconds +
                ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds +
                ", authorities='" + authorities + '\'' +
                ", additionalInformation=" + additionalInformation +
                '}';
    }

    private Set<String> _tokenizeStringAttribute(String tokenStr) {

        if(StringUtils.isEmpty(tokenStr)) {
            return null;
        }

        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(tokenStr, ",");
        while(st.hasMoreElements()) {
            result.add(st.nextToken());
        }
        return result;
    }
}
