package io.tlor.spring.oauth.repository;

import io.tlor.spring.oauth.vo.PlatformClient;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OAuthClientDetailsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ClientDetails getOAuthClient(String clientId) {

        String sql = "SELECT * FROM oauth_client_details WHERE client_id = :clientId";

        PlatformClient platformClient = null;
        Session session = entityManager.unwrap(Session.class);

        NativeQuery q = session.createNativeQuery(sql)
                .setParameter("clientId", clientId)
                .setMaxResults(1);

        List<Object[]> resultList = q.getResultList();
        if(resultList != null && resultList.size() > 0) {
            platformClient = resultList.stream().map( client -> new PlatformClient (
                    (String) client[0],
                    (String) client[1],
                    (String) client[3],
                    (String) client[4],
                    (String) client[5],
                    (String) client[6],
                    (Integer) client[7],
                    (Integer) client[8],
                    (String) client[9],
                    (String) client[10]
            )).collect(Collectors.toList()).get(0);
        }
        return platformClient;
    }
}
