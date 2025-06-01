package jmeow.selectiveofflinemode.mixin;

import com.mojang.authlib.GameProfile;
import jmeow.selectiveofflinemode.NameExpiry;
import net.fabricmc.loader.impl.util.log.Log;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

@Mixin(ServerLoginNetworkHandler.class)
public class ServerMixin {
	@Shadow
	@Nullable
	GameProfile profile;

	@Shadow @Final
    static Logger LOGGER;

	@Unique
	private static final Map<String, Date> nameExpiry;

	static {
		nameExpiry = Map.ofEntries();
	}

	@Redirect(method = "onHello", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;isOnlineMode()Z"))
	private boolean isOnlineMode(MinecraftServer instance) {
        if (profile != null) {
			if(NameExpiry.names.containsKey(profile.getName()) && NameExpiry.names.get(profile.getName()).after(new Date())) {
				LOGGER.info("{} joined with permission", profile.getName());
				return false;
			}
        }
        return true;
	}
}