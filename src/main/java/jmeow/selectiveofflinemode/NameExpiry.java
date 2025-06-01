package jmeow.selectiveofflinemode;

import net.minecraft.server.Whitelist;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class NameExpiry {
    public static Map<String, Date> names = new HashMap<>();

    public static void addName(String name) {

        names.put(name, Date.from(Instant.now().plus(Duration.ofMinutes(1))));
    }
}
