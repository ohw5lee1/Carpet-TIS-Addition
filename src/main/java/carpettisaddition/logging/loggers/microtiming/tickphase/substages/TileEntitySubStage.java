package carpettisaddition.logging.loggers.microtiming.tickphase.substages;

import carpet.utils.Messenger;
import carpettisaddition.logging.loggers.microtiming.MicroTimingLoggerManager;
import carpettisaddition.utils.TextUtil;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.text.BaseText;
import net.minecraft.text.ClickEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntitySubStage extends AbstractSubStage
{
	private final World world;
	private final BlockPos pos;
	private final Block block;
	private final int order;

	public TileEntitySubStage(BlockEntity tileEntity, int order)
	{
		this.world = tileEntity.getWorld();
		this.pos = tileEntity.getPos().toImmutable();
		this.block = tileEntity.getCachedState().getBlock();
		this.order = order;
	}

	@Override
	public BaseText toText()
	{
		return Messenger.c(
				String.format("w %s: ", MicroTimingLoggerManager.tr("Block")),
				TextUtil.getBlockName(this.block),
				String.format("w \n%s: %d", MicroTimingLoggerManager.tr("Order"), this.order),
				String.format("w \n%s: %s", MicroTimingLoggerManager.tr("Position"), TextUtil.getCoordinateString(this.pos))
		);
	}

	@Override
	public ClickEvent getClickEvent()
	{
		return new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, TextUtil.getTeleportCommand(this.pos, this.world.getDimension().getType()));
	}
}
