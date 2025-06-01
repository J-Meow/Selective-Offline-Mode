package jmeow.selectiveofflinemode.mixin;

import com.mojang.authlib.GameProfile;
import jmeow.selectiveofflinemode.NameExpiry;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.SocketAddress;
import java.util.Date;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @Inject(method = "checkCanJoin", at = @At(value = "RETURN"), cancellable = true)
    private void checkCanJoin(SocketAddress address, GameProfile profile, CallbackInfoReturnable<Text> cir) {
        if (NameExpiry.names.containsKey(profile.getName()) && NameExpiry.names.get(profile.getName()).after(new Date())) {
            cir.setReturnValue(null);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}