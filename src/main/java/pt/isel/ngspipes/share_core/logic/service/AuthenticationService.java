package pt.isel.ngspipes.share_core.logic.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private static final int MAX_FAILS = 3;
    private static final long RENEW_TIME = 1*1000*60*60;
    private static final Map<String, Integer> COUNTER = new HashMap<>();
    private static final Map<String, Long> TIMER = new HashMap<>();



    public void authenticationFailed(String ip) {
        synchronized (COUNTER) {
            initIfNeeded(ip);
            renewIfNeeded(ip);

            if(!isBlocked(ip))
                COUNTER.put(ip, COUNTER.get(ip)+1);
        }
    }

    public void authenticationSucceeded(String ip) {
        synchronized (COUNTER) {
            initIfNeeded(ip);
            renewIfNeeded(ip);

            COUNTER.put(ip, 0);
        }
    }

    public boolean isBlocked(String ip) {
        synchronized (COUNTER) {
            initIfNeeded(ip);
            renewIfNeeded(ip);

            return COUNTER.get(ip) >= MAX_FAILS;
        }
    }

    private void initIfNeeded(String ip) {
        COUNTER.putIfAbsent(ip, 0);
        TIMER.putIfAbsent(ip, System.currentTimeMillis());
    }

    private void renewIfNeeded(String ip) {
        if(TIMER.get(ip)+RENEW_TIME < System.currentTimeMillis())
            COUNTER.put(ip, 0);
    }
    
}
