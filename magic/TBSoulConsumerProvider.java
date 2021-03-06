package ovh.corail.tombstone.api.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class TBSoulConsumerProvider implements ICapabilityProvider {
    @CapabilityInject(ISoulConsumer.class)
    public static final Capability<ISoulConsumer> CAP_SOUL_CONSUMER = null;
    private final ISoulConsumer defaultCap;

    public TBSoulConsumerProvider(ISoulConsumer soulConsumer) {
        this.defaultCap = soulConsumer;
    }

    @Override
    public boolean hasCapability(Capability<?> cap, @Nullable EnumFacing facing) {
        return cap == CAP_SOUL_CONSUMER;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> cap, @Nullable EnumFacing facing) {
        return cap == CAP_SOUL_CONSUMER ? CAP_SOUL_CONSUMER.cast(this.defaultCap) : null;
    }

    public static ISoulConsumer getDefault() {
        return new ISoulConsumer() {
            @Override
            public boolean isEnchanted(ItemStack stack) {
                return false;
            }

            @Override
            public boolean setEnchant(World world, BlockPos gravePos, EntityPlayer player, ItemStack stack) {
                return false;
            }

            @Override
            public ITextComponent getEnchantSuccessMessage(EntityPlayer player) {
                return new TextComponentTranslation("tombstone.message.enchant_item.success");
            }

            @Override
            public ITextComponent getEnchantFailedMessage(EntityPlayer player) {
                return new TextComponentTranslation("tombstone.message.enchant_item.failed");
            }
        };
    }
}
