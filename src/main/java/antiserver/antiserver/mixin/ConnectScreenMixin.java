package antiserver.antiserver.mixin;

import antiserver.antiserver.client.AntiserverClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    @Inject(at = @At("RETURN"), method = "connect*")
    public void onConnect(){
        AntiserverClient.INSTANCE.onConnect();
    }
}
