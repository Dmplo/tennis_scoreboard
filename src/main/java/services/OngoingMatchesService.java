package services;

import models.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<String, Match> matches = new ConcurrentHashMap<>();

    public static String createMatchAndUUID(Match match) {
        String uuid = generateKey();
        matches.put(uuid, match);
        return uuid;
    }

    private static String generateKey() {
        return UUID.randomUUID().toString();
    }

    public static Match getMatchByKey(String key) {
        Match match = null;
        if (matches.containsKey(key)) {
            match = matches.get(key);
        }
        return match;
    }

    public static void delMatchByKey(String key) {
        matches.remove(key);
    }

    public static Map<String, Match> getMatches() {
        return matches;
    }

}
