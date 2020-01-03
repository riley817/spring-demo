package io.tlor.spring.oauth.vo;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@NoArgsConstructor
public class PlatformClient implements ClientDetails {

    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String registeredRedirectUri;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private String authorities;
    private Map<String, Object> additionalInformation;

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if (this.resourceIds == null) return null;
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(this.resourceIds, ",");
        while(st.hasMoreElements()) {
            result.add(st.nextToken());
        }
        return result;
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
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(this.scope, ",");
        while(st.hasMoreElements()) {
            result.add(st.nextToken());
        }
        return result;
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
        List<GrantedAuthority> authorities = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(this.authorities, ",");
        while(st.hasMoreElements()) {
            authorities.add(new SimpleGrantedAuthority(st.nextToken()));
        }
        return authorities;
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
}
