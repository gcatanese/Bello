package com.perosa.bello.core.resource.session.provider.local;

import com.perosa.bello.core.resource.session.SessionInfo;
import com.perosa.bello.core.resource.session.provider.local.InMemSessionCache;
import com.perosa.bello.core.resource.session.provider.local.InMemSessionThread;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InMemSessionThreadTest {

    @Mock
    private InMemSessionCache cache;

    @Test
    void cleanUp() {

        when(cache.getMap()).thenReturn(getMap());
        new InMemSessionThread(cache).cleanUp();

        assertEquals(1, cache.getMap().size());
    }

    private Map<String, SessionInfo> getMap() {
        Map<String, SessionInfo> map = new HashMap<>();

        SessionInfo validSession = new SessionInfo();
        validSession.setId("1");
        validSession.setHost("host1.perosa.com");

        SessionInfo expiredSession = new SessionInfo();
        expiredSession.setId("2");
        expiredSession.setHost("host2.perosa.com");
        expiredSession.setDate(LocalDateTime.now().minusMinutes(45));

        map.put(validSession.getId(), validSession);
        map.put(expiredSession.getId(), expiredSession);

        return map;
    }
}