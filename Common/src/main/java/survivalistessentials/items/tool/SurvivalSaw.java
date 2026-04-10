package survivalistessentials.items.tool;

import java.util.Objects;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import survivalistessentials.common.TagManager;
import survivalistessentials.items.RecipeRemainderTieredItem;
import survivalistessentials.items.SurvivalistEssentialsItems;

public class SurvivalSaw extends RecipeRemainderTieredItem {

    public String name;
    private final float speed;

    public SurvivalSaw(String name, ToolMaterial toolMaterial, float speed, float damage, Properties properties) {
        super(toolMaterial, speed, damage, properties);

        this.speed = speed;
        this.name = name;
    }

    @NonNull
    @Override
    public ItemStack getRemainingItem(@NonNull ItemStack stack) {
        ItemStack container = stack.copy();

        if (Objects.equals(this.name, "saw_handle")) {
            return ItemStack.EMPTY;
        }

        container.setDamageValue(container.getDamageValue() + 1);

        if (container.getDamageValue() < container.getMaxDamage()) {
            return container;
        }
        else {
            stack.shrink(1);

            return new ItemStack(SurvivalistEssentialsItems.SAW_HANDLE);
        }
    }

    @Override
    public float getDestroySpeed(@NonNull ItemStack pStack, BlockState pState) {
        return !pState.is(TagManager.Blocks.ALWAYS_BREAKABLE) ? this.speed : 1.0F;
    }

    @Override
    public boolean mineBlock(@NonNull ItemStack pStack, Level pLevel, @NonNull BlockState pState, @NonNull BlockPos pPos, @NonNull LivingEntity pEntityLiving) {
          if (!pLevel.isClientSide() && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
              Tool tool = (Tool)pStack.get(DataComponents.TOOL);
              if (tool == null) {
                  return false;
              } else if (tool.damagePerBlock() > 0) {
                  pStack.hurtAndBreak(tool.damagePerBlock(), pEntityLiving, EquipmentSlot.MAINHAND);

                  return true;
              }
          }

          return true;
    }

    @Override
    public @NonNull InteractionResult useOn(@NonNull UseOnContext pContext) {
        return InteractionResult.FAIL;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack pStack, @NonNull LivingEntity pTarget, @NonNull LivingEntity pAttacker) {}

}
