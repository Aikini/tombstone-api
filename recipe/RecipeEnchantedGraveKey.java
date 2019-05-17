package ovh.corail.tombstone.api.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ObjectHolder;

import java.util.stream.IntStream;

@SuppressWarnings({ "WeakerAccess" })
public class RecipeEnchantedGraveKey extends ShapelessRecipe {
    @ObjectHolder("tombstone:grave_key")
    public static final Item GRAVE_KEY = Items.AIR;

    public RecipeEnchantedGraveKey(ResourceLocation rl) {
        // default recipe "tombstone:enchanted_grave_key" as example
        this(rl, getAdditionalIngredients());
    }

    private static NonNullList<Ingredient> getAdditionalIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL)));
        return ingredients;
    }

    public RecipeEnchantedGraveKey(ResourceLocation rl, NonNullList<Ingredient> ingredients) {
        super(rl, "enchanted_grave_key", setEnchant(new ItemStack(GRAVE_KEY), false), ingredients);
        getIngredients().add(Ingredient.fromStacks(new ItemStack(GRAVE_KEY)));
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        return GRAVE_KEY != Items.AIR && super.matches(inv, world);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return IntStream.range(0, inv.getSizeInventory()).mapToObj(inv::getStackInSlot).filter(stack -> stack.getItem() == GRAVE_KEY).findFirst().map(stack -> setEnchant(stack.copy(), true)).orElse(ItemStack.EMPTY);
    }

    /**
     * This method only exists to imitate what is done when a key is enchanted
     *
     * @param key a key not yet enchanted
     * @return the result of this recipe, an enchanted key
     */
    public static ItemStack setEnchant(ItemStack key, boolean checkCompound) {
        if (key.getItem() == GRAVE_KEY) {
            NBTTagCompound nbt = key.getOrCreateTag();
            if (checkCompound && nbt.contains("enchant", Constants.NBT.TAG_BYTE) && nbt.getBoolean("enchant")) {
                return ItemStack.EMPTY; // the key is already enchanted
            }
            nbt.putBoolean("enchant", true);
        }
        return key;
    }
}